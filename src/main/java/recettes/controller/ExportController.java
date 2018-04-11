package recettes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import recettes.model.Categorie;
import recettes.model.Recette;
import recettes.service.CategorieService;
import recettes.service.RecetteService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller pour l'export des données.
 */
@Controller
public class ExportController {

    @Autowired
    RecetteService recetteService;

    @Autowired
    CategorieService categorieService;


    @PostMapping("export")
    public ResponseEntity<String> export(Model model, HttpServletResponse response) {

        StringBuffer stringBuffer = new StringBuffer();

        List<Categorie> categories = categorieService.getAllCategories();

        for (Categorie categorie : categories) {
            String requete = "insert into categorie values ("
                    + categorie.getId() + ", '" + categorie.getLibelle().replace("'", "''") + "', " + categorie.getOrdre() + ");";

            stringBuffer.append(requete + "<br>");
        }

        List<Recette> recettes = recetteService.getAllRecettes();

        for (Recette recette : recettes) {
            String requete = "insert into recette(id, libelle, ordre, ingredients, etapes, id_categorie) values ("
                    + recette.getId() + ", '" + recette.getLibelle().replace("'", "''") + "', " + recette.getOrdre() + ", '"
                    + recette.getIngredients().replace("'", "''").replace("\r\n", "<br>") + "', '" + recette.getEtapes().replace("'", "''").replace("\r\n", "<br>") + "', " + recette.getCategorie().getId() + ");";

            stringBuffer.append(requete + "<br>");
        }

        stringBuffer.append("<br>");

        return new ResponseEntity<String>(stringBuffer.toString(), HttpStatus.OK);
    }
}
