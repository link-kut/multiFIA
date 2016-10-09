package koreatech.transferring.server;

import koreatech.streaming.service.OrchidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/transferring")
public class TransferServerRestController {
    public static final String REST_SERVICE_URI = "http://localhost:8080/registrar";
    public static final String REST_REGISTRAR_URI = "http://localhost:8080/registrar";
    private OrchidService orchidService = new OrchidService();
    public String fileSeparator = System.getProperty("file.separator");
    TransferServer transferServer = null;

    @RequestMapping(value = "/init", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> init() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String hostName = "Jack's Desktop";
        String orchid = orchidService.getOrchidContentName(hostName);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/registration?contextId=" + OrchidService.contextIdForHostName
                                                                                                                        + "&name=" + hostName
                                                                                                                        + "&orchid=" + orchid
                                                                                                                        + "&locator=127.0.0.1:6666"
                                                                                                                        + "&scheme=ftp", String.class);
        String receivedOrchid = responseEntity.getBody();

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Successful registration (orchid): " + receivedOrchid);
        }

        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{registrar}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> update(@PathVariable("registrar") String registrar) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String hostName = "Jack's Desktop";
        String orchid = orchidService.getOrchidContentName(hostName);

        if (registrar.equals("identifier")) {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/update/" + registrar
                                                                                                + "?contextId=" + OrchidService.contextIdForContentName
                                                                                                + "&name=" + hostName
                                                                                                + "&orchid=" + orchid
                                                                                                + "&scheme=ftp", String.class);
            String receivedOrchid = responseEntity.getBody();
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Successful update (orchid): " + receivedOrchid);
            }
        } else if (registrar.equals("locator")) {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/update/" + registrar
                                                                                                + "?orchid=" + orchid
                                                                                                + "&locator=218.150.181.113:6666", String.class);
            String receivedOrchid = responseEntity.getBody();
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Successful update (orchid): " + receivedOrchid);
            }
        }

        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value = "/lookup/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> lookup(@PathVariable("id") String id) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String hostName = "Jack's Desktop";
        String orchid = orchidService.getOrchidContentName(hostName);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/lookup/" + orchid, String.class);
        String lookupResult = responseEntity.getBody();
        String[] result;
        result = lookupResult.split("#");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/start/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> start(@PathVariable("id") String id,
                                        @RequestParam(required=false, defaultValue = "ftp") String protocol,
                                        @RequestParam(required=false, defaultValue = "127.0.0.1") String targetAddress,
                                        @RequestParam(required=false, defaultValue = "5556") String targetPort) throws Exception{
        if (id == null) {
            System.out.println("Content id (" + id + ") is not found");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        String hostName = "Jack's Desktop";

        RestTemplate restTemplate = new RestTemplate();
        String orchid = orchidService.getOrchidContentName(hostName);

        ResponseEntity<String> lookupResponseEntity = restTemplate.getForEntity(REST_REGISTRAR_URI + "/lookup/" + orchid, String.class);
        String lookupResult = lookupResponseEntity.getBody();
        String[] result;
        result = lookupResult.split("#");
        String[] target;
        target = result[1].split(":");
        int port = Integer.parseInt(target[1]);

        // 관리중인 컨텐츠 ID인지 판단
        String compareId = orchidService.getOrchidContentName(hostName);
        try {
            if (compareId.equals(id)) {
                if (transferServer == null) {
                    transferServer = new TransferServer(target[0], port);
                    System.out.println(">>> START Transferring (ID: " + id + ")");
                    transferServer.run();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
