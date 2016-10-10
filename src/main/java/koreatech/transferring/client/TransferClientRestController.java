package koreatech.transferring.client;


import koreatech.streaming.service.OrchidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class TransferClientRestController {
    public static final String REST_SERVICE_URI = "http://218.150.181.113:8080/transferring";
    public static final String REST_REGISTRAR_URI = "http://localhost:8080/registrar";
    private OrchidService orchidService = new OrchidService();

    public void startTrnasfer(String hostName) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String orchid = orchidService.getOrchidContentName(hostName);

        ResponseEntity<String> lookupResponseEntity = restTemplate.getForEntity(REST_REGISTRAR_URI + "/lookup/" + orchid, String.class);
        String lookupResult = lookupResponseEntity.getBody();
        String[] result;
        result = lookupResult.split("#");
        String[] target;
        target = result[1].split(":");

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/start/" + orchid + "?targetAddress=" + target[0], String.class);
            String receivedOrchid = responseEntity.getBody();
            if (receivedOrchid.equals(orchid)) {
                System.out.println("Success");
            }
        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode() + ": " + e.getStatusText());
        }
    }
}
