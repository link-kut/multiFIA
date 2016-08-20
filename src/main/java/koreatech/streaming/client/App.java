package koreatech.streaming.client;

import koreatech.streaming.domain.Target;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by Kyo on 2016. 8. 18..
 */
public class App {
    public static final String REST_SERVICE_URI = "http://localhost:8080/streaming";

    private static void createStreaming() {
        System.out.println("Testing POST METHOD----------");
        RestTemplate restTemplate = new RestTemplate();

        Target target = new Target();
        target.setMethod("http");
        target.setTargetAddress("127.0.0.1");
        target.setTargetPort("5555");

        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/http", target, Target.class);
            System.out.println(" : " + uri.toString());
        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode() + ": " + e.getStatusText());
        }
    }



}
