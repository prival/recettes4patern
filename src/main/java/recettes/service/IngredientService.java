package recettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import recettes.model.Categorie;
import recettes.model.Ingredient;
import recettes.model.Recette;
import recettes.repository.IngredientRepository;
import recettes.repository.RecetteRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    private Sort sortByOrdreAsc() {
        return new Sort(Sort.Direction.ASC, "ordre");
    }

    public Ingredient createIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}
