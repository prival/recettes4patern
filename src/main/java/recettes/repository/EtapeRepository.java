package recettes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recettes.model.Etape;

@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {

}