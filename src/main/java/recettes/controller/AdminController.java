package recettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import recettes.form.RecetteForm;
import recettes.model.Categorie;
import recettes.model.Etape;
import recettes.model.Ingredient;
import recettes.model.Recette;
import recettes.service.CategorieService;
import recettes.service.EtapeService;
import recettes.service.IngredientService;
import recettes.service.RecetteService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    CategorieService categorieService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    EtapeService etapeService;

    @Autowired
    RecetteService recetteService;

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("categories", categories);

        RecetteForm recetteForm = new RecetteForm();
        model.addAttribute("recetteForm", recetteForm);

        return "admin";
    }

    @RequestMapping(value = { "/addRecette" }, method = RequestMethod.POST)
    public String saveRecette(Model model, @ModelAttribute("recetteForm") RecetteForm recetteForm) {

        Categorie categorie = categorieService.getCategorieById(recetteForm.getCategorieId());

        List<Ingredient> libellesIngredients = recetteForm.getIngredients();
        List<String> libellesEtapes = recetteForm.getEtapes();

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        List<Etape> etapes = new ArrayList<Etape>();

        for (Ingredient ingredient : libellesIngredients) {
            ingredients.add(ingredient);
        }

        for (String libelle : libellesEtapes) {
            Etape etape = new Etape(libelle, 1);
            etapes.add(etape);
        }

        Recette recette = new Recette();

        recette.setLibelle(recetteForm.getLibelle());
        recette.setCategorie(categorie);
        recette.setOrdre(1);
        recette.setIngredients(ingredients);
        recette.setEtapes(etapes);

        recetteService.createRecette(recette);

        return "redirect:/plats";
    }
}
