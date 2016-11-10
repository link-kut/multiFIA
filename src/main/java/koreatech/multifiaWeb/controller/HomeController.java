package koreatech.multifiaWeb.controller;

import koreatech.multifiaWeb.domain.ServiceProvider;
import koreatech.multifiaWeb.repository.ServiceMapper;
import koreatech.streaming.service.OrchidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.net.InetAddress;

@Controller
@RequestMapping("/")
public class HomeController {

    @Inject
    private ServiceMapper serviceMapper;

    private OrchidService orchidService = new OrchidService();

    @ModelAttribute("name")
    private String getName() {
        return "IamHomeControllerModelAttribute";
    }

    @RequestMapping
    public String home(Model model) throws Exception{
        ServiceProvider serviceProvider = new ServiceProvider();
        model.addAttribute("network", serviceProvider);
        model.addAttribute("maxId", serviceMapper.findMaxuserId());

        return "index";
    }

    @RequestMapping(value = "/regit", method = RequestMethod.GET)
    public String regit(Model model) throws Exception {
        String orchid = orchidService.getOrchidIPv4Addr("10.1.7.2");
        model.addAttribute("client_orchid", orchid);
        return "regit";
    }

    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    public String stream(Model model) throws Exception{
        InetAddress local = InetAddress.getLocalHost();
        String ip = local.getHostAddress();
        model.addAttribute("latest_quality", serviceMapper.findLatestQuality());
        model.addAttribute("ip", ip);
        return "stream";
    }

    @RequestMapping(value="/networkService", method = RequestMethod.GET)
    public String registration(@RequestParam int userId,
                               @RequestParam String type,
                               @RequestParam String quality,
                               @RequestParam String capacity,
                               @RequestParam String plan,
                               @RequestParam String contextId) {
        serviceMapper.insert(userId, type, quality, capacity, plan, contextId);
        return "redirect:/";
    }

    @RequestMapping(value="/request/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> temperature(@PathVariable("id") String id) {
        // 관리중인 컨텐츠 ID인지 판단
        //System.out.println("!@!2121212121s");
        String content = id;
        if (content == null) {
           System.out.println("Content id (" + id + ") is not found");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        //Thread.start()
        return new ResponseEntity<String>(content, HttpStatus.OK);
    }
}
