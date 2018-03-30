package recettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import recettes.model.Categorie;
import recettes.service.CategorieService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategorieController {

    @Autowired
    CategorieService categorieService;


    @GetMapping("categorie")
    public String categorie(Model model) {
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        Categorie categorie = new Categorie("", 1);
        model.addAttribute("categorie", categorie);

        return "categorie";
    }

    @RequestMapping(value = { "/categorie/add" }, method = RequestMethod.POST)
    public String saveCategorie(
            @ModelAttribute("categorie") Categorie categorie) {

        categorieService.createCategorie(categorie);

        return "redirect:/admin";
    }

    @GetMapping("categorie/edit/{id}")
    public String editCategorie(@PathVariable long id, Model model) {
        Categorie categorie = categorieService.getCategorieById(id);
        model.addAttribute("categorie", categorie);

        return "categorieEdit";
    }


    @PostMapping("categorie/update")
    public String updateCategorie(
            HttpSession session, @ModelAttribute("categorie") Categorie categorie) {

        categorieService.createCategorie(categorie);

        session.setAttribute("categoriesMenu", categorieService.getAllCategories());

        return "redirect:/categorie";
    }


    @PostMapping("categorie/delete/{id}")
    public String deleteCategorie(
            HttpSession session, @PathVariable long id) {

        categorieService.deleteCategorie(id);

        session.setAttribute("categoriesMenu", categorieService.getAllCategories());

        return "redirect:/categorie";
    }

}
