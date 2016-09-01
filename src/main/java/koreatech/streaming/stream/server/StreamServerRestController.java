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

    CommandExecuter commandExecuter = null;
    StreamPlayer streamPlayer = null;

    @RequestMapping(value="/start/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> start(@PathVariable("id") String id,
                                         @RequestParam(required=false, defaultValue = "rtp") String protocol,
                                         @RequestParam(required=false, defaultValue = "127.0.0.1") String targetAddress,
                                         @RequestParam(required=false, defaultValue = "5555") String targetPort) throws Exception{
        if (id == null) {
            System.out.println("Content id (" + id + ") is not found");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        // 관리중인 컨텐츠 ID인지 판단
        String compareId = orchidService.getOrchidContentName("Spiderman.mp4");
        if(compareId.equals(id)) {
            String mediaFolder = "C:\\Users\\asif";
            if (streamPlayer == null) {
                streamPlayer = new StreamPlayer(mediaFolder, fileSeparator, "Spiderman.mp4", protocol, targetAddress, targetPort);
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
        streamPlayer = null;

        String compareId = orchidService.getOrchidContentName("Spiderman.mp4");
        return new ResponseEntity<String>("Cotent (ID [" + id + "]) has been stopped!", HttpStatus.OK);

        /*
        String pid = null;
        Process p1 = Runtime.getRuntime().exec("lsof -t -i:" + port);
        BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
        pid = br.readLine();
        p1.waitFor();

        if (pid != null) {
            Process p2 = Runtime.getRuntime().exec("kill -9 " + pid);
            p2.waitFor();
            return new ResponseEntity<String>("Process " + pid + " id is killed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No streaming process exists", HttpStatus.OK);
        }
        */
    }

    @RequestMapping(value="/stop/all", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> stopall(@RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        String command = "jps";
        System.out.println(command);
        Process p = Runtime.getRuntime().exec(command);

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String pid = br.readLine();
        System.out.println(pid + " - !!!");
        int numKilledProcess = 0;
        while (pid != null) {
            Process p2 = Runtime.getRuntime().exec("kill -9 " + pid);
            p2.waitFor();
            numKilledProcess++;
            pid = br.readLine();
        }

        p.waitFor();

        return new ResponseEntity<String>(numKilledProcess + " process(es) are killed.", HttpStatus.OK);
    }
}