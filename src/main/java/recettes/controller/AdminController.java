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
import recettes.service.EtapeService;
import recettes.service.IngredientService;
import recettes.service.RecetteService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    CategorieService categorieService;

    @Autowired
    RecetteService recetteService;

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        Recette recette = new Recette();
        model.addAttribute("recette", recette);

        return "admin";
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

        return "redirect:/plats";
    }
}
