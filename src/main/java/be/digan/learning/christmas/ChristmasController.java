package be.digan.learning.christmas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/christmas")
public class ChristmasController {
    @RequestMapping(value = "/getPresentsFor/{name}", method= RequestMethod.GET)
    public @ResponseBody String getPresentsFor(@PathVariable(value="name") String name) {
        return "You're to early, " + name + "! There are no presents yet.";
    }

}
