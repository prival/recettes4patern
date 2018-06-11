package recettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import recettes.model.Categorie;
import recettes.model.Recette;
import recettes.service.CategorieService;
import recettes.service.RecetteService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller pour les recettes.
 */
@Controller
public class RecetteController {


    @Autowired
    RecetteService recetteService;

    @Autowired
    CategorieService categorieService;


    @GetMapping("/recette/{id}")
    public String showRecette(@PathVariable long id, Model model) {
        Recette recette = recetteService.getRecetteById(id);
        List<Categorie> categories = categorieService.getAllCategories();

        model.addAttribute("recette", recette);
        model.addAttribute("categories", categories);

        return "recette";
    }


    @RequestMapping(value = { "/addRecette" }, method = RequestMethod.POST)
    public String saveRecette(
            HttpSession session,
            @ModelAttribute("recette") Recette recette) {

        recetteService.createRecette(recette);

        return "redirect:/index";
    }


    @GetMapping("/recette/edit/{id}")
    public String editRecette(@PathVariable long id, Model model) {

        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        Recette recette = recetteService.getRecetteById(id);

        model.addAttribute("recette", recette);

        return "recetteEdit";
    }


    @PostMapping("/recette/update/{id}")
    public String updateRecette(
            @ModelAttribute("recette") Recette recette) {

        recetteService.createRecette(recette);

        return "redirect:/recette/{id}";
    }


    @PostMapping("/recette/delete/{id}")
    public String deleteRecette(
            HttpSession session,
            @PathVariable  long id) {

        // TODO : faire par recette pour si besoin enlever la recette de la session

        recetteService.deleteRecette(id);

        return "redirect:/admin";
    }


    @PostMapping("modifierOrdreRecettes")
    public void modifierOrdreRecette(
            @Valid @RequestBody List<Recette> recettes,
            HttpSession session
    ) {
        recetteService.updateRecette(recettes.get(0).getOrdre(), recettes.get(0).getId());
        recetteService.updateRecette(recettes.get(1).getOrdre(), recettes.get(1).getId());
//        categorieService.createCategorie(id);

        session.setAttribute("categoriesMenu", categorieService.getAllCategories());

//        return ResponseEntity.ok();
//        return "redirect:/categorie";
    }

    @GetMapping("affichageRecettes")
    public String affichageRecettes(Model model) {
//        List<Recette> recettes = recetteService.getAllRecettes();
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        Categorie categorie = new Categorie("", 1);
        model.addAttribute("categorie", categorie);

        return "categories";
    }
}
