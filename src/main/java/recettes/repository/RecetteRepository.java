package recettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import recettes.model.Recette;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long> {
//    @Modifying
//    @Query("delete from Recette r where r.id = ?1")
//    void deleteById(Long entityId);
}