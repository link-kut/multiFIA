package koreatech.streaming.stream.client;

import koreatech.streaming.service.OrchidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class StreamClientRestController {
    public static final String REST_SERVICE_URI = "http://localhost:8080/streaming";
    private OrchidService orchidService = new OrchidService();

    public void startStream(String contentName) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String orchid = orchidService.getOrchidContentName(contentName);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/start/" + orchid, String.class);
            String receivedOrchid = responseEntity.getBody();
            if (receivedOrchid.equals(orchid)) {
                System.out.println("Success");
            }
        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode() + ": " + e.getStatusText());
        }
    }

    public void stopStream(String contentName) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String orchid = orchidService.getOrchidContentName(contentName);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/stop/" + orchid, String.class);
            String receivedOrchid = responseEntity.getBody();
            if (receivedOrchid.equals(orchid)) {
                System.out.println("Success");
            }
        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode() + ": " + e.getStatusText());
        }
    }
}
