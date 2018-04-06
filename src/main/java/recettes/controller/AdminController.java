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

/**
 * Controller pour la partie Admin.
 */
@Controller
public class AdminController {

    @Autowired
    private User user;

    @Autowired
    CategorieService categorieService;

    @Autowired
    RecetteService recetteService;

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session) {

        session.setAttribute("User", user);

        List<Categorie> categories = categorieService.getAllCategories();

        // pour choisir une catégorie en créant une recette
        model.addAttribute("categories", categories);

        // on remet les catégories en session ....
        session.setAttribute("categoriesMenu", categories);

        // la recette à créer
        Recette recette = new Recette();
        model.addAttribute("recette", recette);

        return "admin";
    }

}
