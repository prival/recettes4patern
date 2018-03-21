package recettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import recettes.model.Categorie;
@Repository

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}