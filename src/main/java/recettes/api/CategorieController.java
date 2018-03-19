package recettes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recettes.model.Categorie;
import recettes.exception.ResourceNotFoundException;
import recettes.repository.CategorieRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategorieController {

    @Autowired
    CategorieRepository categorieRepository;

    @GetMapping("/categories")
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @PostMapping("/categories")
    public Categorie createCategorie(@Valid @RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @GetMapping("/categories/{id}")
    public Categorie getCategorieById(@PathVariable(value = "id") Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie", "id", id));
    }
}
