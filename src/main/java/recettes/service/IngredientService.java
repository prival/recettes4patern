package recettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import recettes.model.Ingredient;
import recettes.model.Recette;
import recettes.repository.IngredientRepository;
import recettes.repository.RecetteRepository;

import javax.validation.Valid;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Ingredient createIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}
