package recettes.controller;

import recettes.component.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import recettes.model.Categorie;
import recettes.model.Etape;
import recettes.model.Ingredient;
import recettes.model.Recette;
import recettes.service.CategorieService;
import recettes.service.RecetteService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private User user;

    @Autowired
    CategorieService categorieService;

    @Autowired
    RecetteService recetteService;

    @GetMapping("recettes/{id}")
    public String showRecette(@PathVariable long id, Model model) {
        Recette recette = recetteService.getRecetteById(id);
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        return "recette";
    }


    @GetMapping("categories/edit/{id}")
    public String editCategorie(@PathVariable long id, Model model) {
        Categorie categorie = categorieService.getCategorieById(id);
        model.addAttribute("categorie", categorie);

        return "categoriesEdit";
    }


    @PostMapping("categorie/update")
    public String updateCategorie(
            @ModelAttribute("categorie") Categorie categorie) {

        categorieService.createCategorie(categorie);

        return "redirect:/categorie";
    }


    @PostMapping("categorie/delete")
    public String deleteCategorie(
            @ModelAttribute("categorie") Categorie categorie) {

        categorieService.deleteCategorie(categorie);

        return "redirect:/categorie";
    }


    @GetMapping("recettes/edit/{id}")
    public String editRecette(@PathVariable long id, Model model) {

        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        Recette recette = recetteService.getRecetteById(id);
        model.addAttribute("recette", recette);

        return "editRecette";
    }


    @GetMapping("recettes/edit")
    public String updateRecette(
            @ModelAttribute("categorie") Categorie categorie) {

        categorieService.createCategorie(categorie);

        return "redirect:/recette/{id}";
    }


    @GetMapping("recettes/delete")
    public String deleteRecette(
            @ModelAttribute("recette") Recette recette) {

        recetteService.deleteRecette(recette);

        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session) {

        session.setAttribute("User", user);

        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        Recette recette = new Recette();
        model.addAttribute("recette", recette);

        return "admin";
    }


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

    @RequestMapping(value = { "/addRecette" }, method = RequestMethod.POST)
    public String saveRecette(
            @ModelAttribute("recette") Recette recette,
            @RequestParam(value = "ingredientName") String[] ingredientsName,
            @RequestParam(value = "etapeName") String[] etapesName) {

        recette.setIngredients(new ArrayList<Ingredient>());
        recette.setEtapes(new ArrayList<Etape>());

        if (ingredientsName != null) {
            for (int i = 0; i < ingredientsName.length; i++) {
                int ordre = i+1;
                Ingredient ingredient = new Ingredient(ingredientsName[i], ordre);
                recette.addIngredient(ingredient);
            }
        }

        if (etapesName != null) {
            for (int i = 0; i < etapesName.length; i++) {
                int ordre = i+1;
                Etape etape = new Etape(etapesName[i], ordre);
                recette.addEtape(etape);
            }
        }

        recette.setOrdre(1);

        recetteService.createRecette(recette);

        return "redirect:/admin";
    }
}
