package recettes.controller;

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

        recetteService.createRecette(recette);

        return "redirect:/index";
    }


    @GetMapping("/recette/edit/{id}")
    public String editRecette(@PathVariable long id, Model model) {

        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        Recette recette = recetteService.getRecetteById(id);

        if (recette.getIngredients() == null || recette.getIngredients().isEmpty()) {
            recette.addIngredient(new Ingredient("", 1));
        }

        if (recette.getEtapes() == null || recette.getEtapes().isEmpty()) {
            recette.addEtape(new Etape("", 1));
        }

        model.addAttribute("recette", recette);

        return "recetteEdit";
    }


    @PostMapping("/recette/update/{id}")
    public String updateRecette(
            @ModelAttribute("recette") Recette recette,
            @RequestParam(value = "ingredientName") String[] ingredientsName,
            @RequestParam(value = "etapeName") String[] etapesName) {

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

//        recetteService.createRecette(recette);

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

}
