package recettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recettes.model.Recette;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long> {

}