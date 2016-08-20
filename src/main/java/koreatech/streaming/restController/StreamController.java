package koreatech.streaming.restController;

import com.sun.jna.NativeLibrary;
import koreatech.streaming.domain.Target;
import koreatech.streaming.httpStream.StreamHttp;
import koreatech.streaming.httpStream.StreamHttpThread;
import koreatech.streaming.service.OrchidService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/streaming")
public class StreamController {

    private OrchidService orchidService = new OrchidService();
    public static String fileSeparator = System.getProperty("file.separator");
    public static String mediaFolder = fileSeparator + "usr" + fileSeparator + "local" + fileSeparator + "share";

    @RequestMapping(value="/request/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> target(@PathVariable("id") String id, @RequestParam(required=false, defaultValue = "http") String pro,
                                              @RequestParam(required=false, defaultValue = "127.0.0.1") String addr,
                                              @RequestParam(required=false, defaultValue = "5555") String port) throws Exception{
        String[] info = new String[6];
        info[0] = pro;
        info[1] = addr;
        info[2] = port;

        // 관리중인 컨텐츠 ID인지 판단
        String compareId = orchidService.getOrchidContentName("Spiderman.mp4");
        if (id == null) {
            System.out.println("Content id (" + id + ") is not found");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        if(compareId.equals(id)) {
            System.setProperty("jna.library.path", "Applications/VLC.app/Contents/MacOS/lib");
            StreamHttpThread streamHttpThread = new StreamHttpThread(info, mediaFolder, fileSeparator);
            streamHttpThread.start();
            //streamHttpThread.join();
        }

        //Thread.start()
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/http", method = RequestMethod.POST)
    public ResponseEntity<Void> createTemperature(@RequestBody Target target,
                                                  UriComponentsBuilder ucBuilder) {

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                ucBuilder.path("/http/{id}").buildAndExpand(target.getTargetAddress()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}