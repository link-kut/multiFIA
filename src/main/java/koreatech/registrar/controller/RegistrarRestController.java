package koreatech.registrar.controller;

import koreatech.multifiaWeb.repository.RegistrarMapper;
import koreatech.streaming.service.OrchidService;
import koreatech.streaming.stream.server.StreamPlayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/registrar")
public class RegistrarRestController {

    @Inject
    private RegistrarMapper registrarMapper;

    @RequestMapping(value="/registration", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> registration(@RequestParam(required=false, defaultValue = "292D05A61D8C335FA3411EBB5BAABE77") String contextId,
                                               @RequestParam(required=false, defaultValue = "multifia/Spiderman.mp4") String name,
                                               @RequestParam(required=false, defaultValue = "2FD07DE569C3AD37A83480D3") String orchid,
                                               @RequestParam(required=false, defaultValue = "127.0.0.1") String locator,
                                               @RequestParam(required=false, defaultValue = "rtp") String scheme) throws Exception {

        System.out.println("111111111111");
        System.out.println(registrarMapper.findByOrchid(orchid));
        if(orchid.equals(registrarMapper.findByOrchid(orchid))) {
            registrarMapper.locatorDelete(orchid);
            registrarMapper.identifierDelete(orchid);
        }
        System.out.println("22222222222");
        registrarMapper.identifierInsert(orchid, contextId, name, scheme);  //identifier 레지스트라 등록
        registrarMapper.locatorInsert(orchid, locator);                     //locator 레지스트라 등록
        System.out.println("3333333333333");
        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value="/update/{registrar}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> update(@PathVariable("registrar") String registrar,
                                         @RequestParam(required=false, defaultValue = "292D05A61D8C335FA3411EBB5BAABE77") String contextId,
                                         @RequestParam(required=false, defaultValue = "multifia/Spiderman.mp4") String name,
                                         @RequestParam(required=false, defaultValue = "2FD07DE569C3AD37A83480D3") String orchid,
                                         @RequestParam(required=false, defaultValue = "127.0.0.1") String locator,
                                         @RequestParam(required=false, defaultValue = "rtp") String scheme) throws Exception {

        /*if(registrar.equals("identifier")) {
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
        }*/

        return new ResponseEntity<String>(orchid, HttpStatus.OK);
    }

    @RequestMapping(value="/lookup/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> lookup(@PathVariable("id") String id) throws Exception {

        StringBuffer lookupResult = new StringBuffer();
        lookupResult.append(registrarMapper.findByScheme(id)); // scheme
        lookupResult.append("#");
        lookupResult.append(registrarMapper.findByLocator(id)); // locator

        String result = lookupResult.toString();
        System.out.println(result);

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception {

        registrarMapper.identifierDelete(id);
        registrarMapper.locatorDelete(id);
        System.out.println(registrarMapper.findByAllIdentifier());
        System.out.println(registrarMapper.findByAllLocator());

        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}