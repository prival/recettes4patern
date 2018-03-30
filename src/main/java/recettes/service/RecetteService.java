package recettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import recettes.exception.ResourceNotFoundException;
import recettes.model.Categorie;
import recettes.model.Recette;
import recettes.repository.CategorieRepository;
import recettes.repository.RecetteRepository;

import javax.validation.Valid;

@Service
public class RecetteService {

    @Autowired
    RecetteRepository recetteRepository;

    public Recette getRecetteById(@PathVariable(value = "id") Long id) {
        return recetteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recette", "id", id));
    }

    public Recette createRecette(@Valid @RequestBody Recette recette) {
        return recetteRepository.save(recette);
    }

//    public void deleteRecette(@Valid @RequestBody Recette recette) {
    public void deleteRecette(@Valid @RequestBody long id) {
//        recetteRepository.delete(recette);
        recetteRepository.deleteById(id);
    }

}
