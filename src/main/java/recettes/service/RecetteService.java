package recettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import recettes.model.Categorie;
import recettes.model.Recette;
import recettes.repository.CategorieRepository;
import recettes.repository.RecetteRepository;

import javax.validation.Valid;

@Service
public class RecetteService {

    @Autowired
    RecetteRepository recetteRepository;

    public Recette createRecette(@Valid @RequestBody Recette recette) {
        return recetteRepository.save(recette);
    }
}
