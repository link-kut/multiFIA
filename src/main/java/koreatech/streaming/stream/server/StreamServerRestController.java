package koreatech.streaming.stream.server;

import koreatech.streaming.service.OrchidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/streaming")
public class StreamServerRestController {
    public static final String REST_SERVICE_URI = "http://localhost:8100/registrar";
    private OrchidService orchidService = new OrchidService();
    public static String fileSeparator = System.getProperty("file.separator");
    StreamPlayer streamPlayer = null;

    @RequestMapping(value="/init", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> init(@RequestParam(required=false, defaultValue = "rtp") String content) throws Exception{
        //Registrar에게 Identifier - Context ID, Name, Scheme, Locator 정보를 POST 형식으로 등록
        //OrchidService.contextIdForContentName - 'multifia/spiderman.mp4' - 'RTP'
        //OrchidService.contextIdForHostName - 'Jack's Desktop' - 'FTP'

        RestTemplate restTemplate = new RestTemplate();
        String contentName = "multifia/Spiderman.mp4";
        String orchid = orchidService.getOrchidContentName(contentName);
        System.out.println(OrchidService.contextIdForContentName);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/registration?contextId=" + OrchidService.contextIdForContentName
                                                                                            + "&name=" + contentName
                                                                                            + "&orchid=" + orchid
                                                                                            + "&locator=127.0.0.1:8100"
                                                                                            + "&scheme=rtp", String.class);
        String receivedOrchid = responseEntity.getBody();

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Successful registration (orchid): " + receivedOrchid);
        }

        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value="/update/{registrar}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> update(@PathVariable("registrar") String registrar) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String contentName = "multifia/Spiderman.mp4";
        String orchid = orchidService.getOrchidContentName(contentName);

        if(registrar.equals("identifier")) {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/update/" + registrar
                                                                                                + "?contextId=" + OrchidService.contextIdForContentName
                                                                                                + "&name=" + contentName
                                                                                                + "&orchid=" + orchid
                                                                                                + "&scheme=rtp", String.class);
            String receivedOrchid = responseEntity.getBody();
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Successful update (orchid): " + receivedOrchid);
            }
        }
        else if(registrar.equals("locator")) {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/update/" + registrar
                                                                                                + "?orchid=" + orchid
                                                                                                + "&locator=218.150.181.113:8100", String.class);
            String receivedOrchid = responseEntity.getBody();
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Successful update (orchid): " + receivedOrchid);
            }
        }

        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value="/lookup/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> lookup(@PathVariable("id") String id) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String contentName = "multifia/Spiderman.mp4";
        String orchid = orchidService.getOrchidContentName(contentName);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/lookup/" + orchid, String.class);
        String lookupResult = responseEntity.getBody();
        String[] result;
        result = lookupResult.split("#");
        for(int i=0; i<result.length; i++) {
            System.out.println(result[i]);
        }

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/start/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> start(@PathVariable("id") String id,
                                        @RequestParam(required=false, defaultValue = "rtp") String protocol,
                                        @RequestParam(required=false, defaultValue = "127.0.0.1") String targetAddress,
                                        @RequestParam(required=false, defaultValue = "5555") String targetPort,
                                        @RequestParam(required=false, defaultValue = "160") String quality) throws Exception{
        if (id == null) {
            System.out.println("Content id (" + id + ") is not found");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        String contentName = "multifia/Spiderman.mp4";

        // 관리중인 컨텐츠 ID인지 판단
        String compareId = orchidService.getOrchidContentName(contentName);
        if(compareId.equals(id)) {
            String mediaFolder = "/usr/local/share";
            if (streamPlayer == null) {
                streamPlayer = new StreamPlayer(mediaFolder, fileSeparator, "Spiderman.mp4", protocol, targetAddress, targetPort, quality);
                System.out.println(">>> START Streaming " + streamPlayer.getContentName() + " (ID: " + id + ")");
                streamPlayer.play();
            }
        }

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/stop/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> stop(@PathVariable("id") String id,
                                       @RequestParam(required=false, defaultValue = "127.0.0.1") String address,
                                       @RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        streamPlayer.stopPlay();
        System.out.println(">>> STOP Streaming " + streamPlayer.getContentName() + " (ID: " + id + ")");
        streamPlayer = null;

        String compareId = orchidService.getOrchidContentName("multifia/Spiderman.mp4");
        return new ResponseEntity<String>("Cotent (ID [" + id + "]) has been stopped!", HttpStatus.OK);
    }

    @RequestMapping(value="/pause/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> pause(@PathVariable("id") String id,
                                        @RequestParam(required=false, defaultValue = "127.0.0.1") String address,
                                        @RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        streamPlayer.pausePlay();
        System.out.println(">>> PAUSE Streaming " + streamPlayer.getContentName() + " (ID: " + id + ")");

        String compareId = orchidService.getOrchidContentName("multifia/Spiderman.mp4");
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/resume/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> resume(@PathVariable("id") String id,
                                         @RequestParam(required=false, defaultValue = "127.0.0.1") String address,
                                         @RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        streamPlayer.resumeplay();
        System.out.println(">>> RESUME Streaming " + streamPlayer.getContentName() + " (ID: " + id + ")");

        String compareId = orchidService.getOrchidContentName("multifia/Spiderman.mp4");
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}