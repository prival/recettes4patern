package recettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import recettes.model.Categorie;
import recettes.model.Etape;
import recettes.model.Ingredient;
import recettes.model.Recette;
import recettes.service.CategorieService;
import recettes.service.EtapeService;
import recettes.service.IngredientService;
import recettes.service.RecetteService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller pour l'export des données.
 */
@Controller
public class ExportController {

    @Autowired
    IngredientService ingredientService;

    @Autowired
    EtapeService etapeService;

    @Autowired
    RecetteService recetteService;

    @Autowired
    CategorieService categorieService;


    @PostMapping("export")
    public ResponseEntity<String> export(Model model, HttpServletResponse response) {

        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        StringBuffer stringBuffer = new StringBuffer();

        for (Ingredient ingredient : ingredients) {
            String requete = "insert into ingredients values ("
                    + ingredient.getId() + ", " + ingredient.getLibelle() + ", " + ingredient.getOrdre() + ", " + ingredient.getRecette().getId() + ");";

            stringBuffer.append(requete + "<br>");
        }

        stringBuffer.append("<br>");

        List<Etape> etapes = etapeService.getAllEtapes();

        for (Etape etape : etapes) {
            String requete = "insert into etapes values ("
                    + etape.getId() + ", " + etape.getLibelle() + ", " + etape.getOrdre() + ", " + etape.getRecette().getId() + ");";

            stringBuffer.append(requete + "<br>");
        }

        stringBuffer.append("<br>");

        List<Recette> recettes = recetteService.getAllRecettes();

        for (Recette recette : recettes) {
            String requete = "insert into recettes values ("
                    + recette.getId() + ", " + recette.getLibelle() + ", " + recette.getOrdre() + ", " + recette.getCategorie().getId() + ");";

            stringBuffer.append(requete + "<br>");
        }

        stringBuffer.append("<br>");

        List<Categorie> categories = categorieService.getAllCategories();

        for (Categorie categorie : categories) {
            String requete = "insert into categories values ("
                    + categorie.getId() + ", " + categorie.getLibelle() + ", " + categorie.getOrdre() + ");";

            stringBuffer.append(requete + "<br>");
        }

        return new ResponseEntity<String>(stringBuffer.toString(), HttpStatus.OK);
    }
}
