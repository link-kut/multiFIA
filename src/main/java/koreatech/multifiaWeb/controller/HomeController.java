package koreatech.multifiaWeb.controller;

import koreatech.multifiaWeb.domain.ServiceProvider;
import koreatech.multifiaWeb.repository.ServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/")
public class HomeController {

    @Inject
    private ServiceMapper serviceMapper;

    @ModelAttribute("name")
    private String getName() {
        return "IamHomeControllerModelAttribute";
    }

    @RequestMapping
    public String home(Model model) {
        ServiceProvider serviceProvider = new ServiceProvider();
        model.addAttribute("network", serviceProvider);
        model.addAttribute("maxId", serviceMapper.findMaxuserId());
        return "index";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String movie(@RequestParam String title, Model model) throws Exception{
        model.addAttribute("content", title);
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
