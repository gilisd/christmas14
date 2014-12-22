package be.digan.learning.christmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/christmas")
public class ChristmasController {
    @Autowired
    private ChristmasBusiness presentBusiness;

    @RequestMapping(value = "/getPresentsFor/{name}", method= RequestMethod.GET)
    public @ResponseBody String getPresentsFor(@PathVariable(value="name") String name) {
        try {
            List<String> presents = presentBusiness.getPresentsFor(name);
            if (presents.size() == 0) {
                return "No presents for you";
            } else {
                String result = "";
                for (String present : presents) {
                    result += present + "\n";
                }
                return result;
            }
        } catch (UnknownUserException exc) {
            return "Sorry, I don't know you";
        }
    }

}
