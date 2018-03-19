package recettes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recettes.model.Categorie;
import recettes.service.CategorieService;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("/index")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/plats")
    public String plats(Model model) {
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);
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