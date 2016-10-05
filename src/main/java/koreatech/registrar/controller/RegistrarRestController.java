package koreatech.registrar.controller;

import koreatech.streaming.service.OrchidService;
import koreatech.streaming.stream.server.StreamPlayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/registrar")
public class RegistrarRestController {
    private HashMap<String, List<String>> identifierRegistrar = new HashMap<String, List<String>>();;
    private HashMap<String, List<String>> locatorRegistrar = new HashMap<String, List<String>>();;

    @RequestMapping(value="/registration", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> registration(@RequestParam(required=false, defaultValue = "292D05A61D8C335FA3411EBB5BAABE77") String contextId,
                                               @RequestParam(required=false, defaultValue = "multifia/Spiderman.mp4") String name,
                                               @RequestParam(required=false, defaultValue = "2FD07DE569C3AD37A83480D3") String orchid,
                                               @RequestParam(required=false, defaultValue = "127.0.0.1") String locator,
                                               @RequestParam(required=false, defaultValue = "rtp") String scheme) throws Exception {

        List<String> contentList = new ArrayList<String>();
        contentList.add(contextId);
        contentList.add(name);
        contentList.add(scheme);
        identifierRegistrar.put(orchid, contentList);
        System.out.println(identifierRegistrar.get(orchid));

        List<String> locatorList = new ArrayList<String>();
        locatorList.add(orchid);
        locatorList.add(locator);
        locatorRegistrar.put(orchid, locatorList);
        System.out.println(locatorRegistrar.get(orchid));

        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value="/update/{registrar}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> update(@PathVariable("registrar") String registrar,
                                         @RequestParam(required=false, defaultValue = "292D05A61D8C335FA3411EBB5BAABE77") String contextId,
                                         @RequestParam(required=false, defaultValue = "multifia/Spiderman.mp4") String name,
                                         @RequestParam(required=false, defaultValue = "2FD07DE569C3AD37A83480D3") String orchid,
                                         @RequestParam(required=false, defaultValue = "127.0.0.1") String locator,
                                         @RequestParam(required=false, defaultValue = "rtp") String scheme) throws Exception {

        if(registrar.equals("identifier")) {
            List<String> contentList = new ArrayList<String>();
            contentList.add(contextId);
            contentList.add(name);
            contentList.add(scheme);
            identifierRegistrar.replace(orchid, contentList);
            System.out.println(identifierRegistrar.values());
        }

        if(registrar.equals("locator")) {
            List<String> locatorList = new ArrayList<String>();
            locatorList.add(orchid);
            locatorList.add(locator);
            locatorRegistrar.replace(orchid, locatorList);
            System.out.println(locatorRegistrar.values());
        }

        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value="/lookup/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> lookup(@PathVariable("id") String id) throws Exception {

        StringBuffer lookupResult = new StringBuffer();
        lookupResult.append(locatorRegistrar.get(id).get(1).toString()); // locator
        lookupResult.append("#");
        lookupResult.append(identifierRegistrar.get(id).get(2).toString()); // scheme

        String result = lookupResult.toString();
        System.out.println(result);

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}