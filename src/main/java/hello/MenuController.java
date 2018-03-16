package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

    @GetMapping("/index")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/plats")
    public String plats() {
        return "plats";
    }

    @GetMapping("/desserts")
    public String desserts() {
        return "desserts";
    }

    @GetMapping("/pate_gyozas")
    public String pate_gyozas() {
        return "pate_gyozas";
    }

    @GetMapping("/brocoli_oignons")
    public String brocoli_oignons() {
        return "brocoli_oignons";
    }

    @GetMapping("/tarte_pommes")
    public String tarte_pommes() {
        return "tarte_pommes";
    }

    @GetMapping("/mousse_chocolat")
    public String mousse_chocolat() {
        return "mousse_chocolat";
    }

}