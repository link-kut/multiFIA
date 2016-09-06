package koreatech.streaming.stream.server;

import koreatech.streaming.service.OrchidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/streaming")
public class StreamServerRestController {

    private OrchidService orchidService = new OrchidService();
    public static String fileSeparator = System.getProperty("file.separator");
    StreamPlayer streamPlayer = null;

    @RequestMapping(value="/start/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> start(@PathVariable("id") String id,
                                        @RequestParam(required=false, defaultValue = "rtp") String protocol,
                                        @RequestParam(required=false, defaultValue = "127.0.0.1") String targetAddress,
                                        @RequestParam(required=false, defaultValue = "5555") String targetPort,
                                        @RequestParam(required=false, defaultValue = "normal") String quality) throws Exception{
        if (id == null) {
            System.out.println("Content id (" + id + ") is not found");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        // 관리중인 컨텐츠 ID인지 판단
        String compareId = orchidService.getOrchidContentName("Spiderman.mp4");
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

        String compareId = orchidService.getOrchidContentName("Spiderman.mp4");
        return new ResponseEntity<String>("Cotent (ID [" + id + "]) has been stopped!", HttpStatus.OK);
    }

    @RequestMapping(value="/pause/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> pause(@PathVariable("id") String id,
                                        @RequestParam(required=false, defaultValue = "127.0.0.1") String address,
                                        @RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        streamPlayer.pausePlay();
        System.out.println(">>> PAUSE Streaming " + streamPlayer.getContentName() + " (ID: " + id + ")");

        String compareId = orchidService.getOrchidContentName("Spiderman.mp4");
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/resume/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> resume(@PathVariable("id") String id,
                                         @RequestParam(required=false, defaultValue = "127.0.0.1") String address,
                                         @RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        streamPlayer.resumeplay();
        System.out.println(">>> RESUME Streaming " + streamPlayer.getContentName() + " (ID: " + id + ")");

        String compareId = orchidService.getOrchidContentName("Spiderman.mp4");
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}