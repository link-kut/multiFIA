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

        //서버 등록
        RestTemplate restTemplate = new RestTemplate();
        String orchid1 = orchidService.getOrchidContentName("218.150.181.113");
        ResponseEntity<String> responseEntity1 = restTemplate.getForEntity("http://127.0.0.1:8100/registrar" + "/registration?contextId=" + OrchidService.contextIdForHostName
                + "&name=218.150.181.113"
                + "&orchid=" + orchid1
                + "&locator=218.150.181.113"
                + "&scheme=server", String.class);
        System.out.println(responseEntity1);

        //클라이언트
        String orchid2 = orchidService.getOrchidContentName("127.0.0.1");
        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity("http://127.0.0.1:8100/registrar" + "/registration?contextId=" + OrchidService.contextIdForHostName
                + "&name=127.0.0.1"
                + "&orchid=" + orchid2
                + "&locator=127.0.0.1"
                + "&scheme=server", String.class);
        System.out.println(responseEntity2);

        return "index";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String movie() throws Exception{
        return "movie";
    }

    @RequestMapping(value="/networkService", method = RequestMethod.GET)
    public String registration(@RequestParam int userId,
                               @RequestParam String type,
                               @RequestParam String quality,
                               @RequestParam String capacity,
                               @RequestParam String plan) {
        serviceMapper.insert(userId, type, quality, capacity, plan);
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
