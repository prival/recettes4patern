package recettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import recettes.exception.ResourceNotFoundException;
import recettes.model.Categorie;
import recettes.repository.CategorieRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class CategorieService {


    @Autowired
    CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(@PathVariable(value = "id") Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie", "id", id));
    }

    public Categorie createCategorie(@Valid @RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(@Valid @RequestBody Categorie categorie) {
        categorieRepository.delete(categorie);
    }
}
