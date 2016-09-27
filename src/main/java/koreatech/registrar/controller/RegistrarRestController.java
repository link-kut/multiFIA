package koreatech.registrar.controller;

import koreatech.streaming.service.OrchidService;
import koreatech.streaming.stream.server.StreamPlayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/registrar")
public class RegistrarRestController {
    private HashMap<String, List<String>> identifierRegistrar;
    private HashMap<String, String> locatorRegistrar;

    @RequestMapping(value="/registration", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> registration(@PathVariable("id") String id,
                                         @RequestParam(required=false, defaultValue = "rtp") String protocol,
                                         @RequestParam(required=false, defaultValue = "127.0.0.1") String targetAddress,
                                         @RequestParam(required=false, defaultValue = "5555") String targetPort) throws Exception{

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/update", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> update(@PathVariable("id") String id,
                                         @RequestParam(required=false, defaultValue = "127.0.0.1") String address,
                                         @RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/lookup/{id}/ssssss", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> lookup(@PathVariable("id") String id,
                                       @RequestParam(required=false, defaultValue = "127.0.0.1") String address,
                                       @RequestParam(required=false, defaultValue = "5555") String port) throws Exception {

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}