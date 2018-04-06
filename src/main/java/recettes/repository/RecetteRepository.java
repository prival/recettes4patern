package recettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import recettes.model.Recette;

import javax.transaction.Transactional;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long> {

    @Transactional
    @Modifying
    @Query("update Recette set ordre = ?1 where id = ?2")
    void updateRecette(int ordre, long id);
}