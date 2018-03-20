package recettes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import recettes.model.Etape;
import recettes.model.Recette;
import recettes.repository.EtapeRepository;
import recettes.repository.RecetteRepository;

import javax.validation.Valid;

@Service
public class EtapeService {

    @Autowired
    EtapeRepository etapeRepository;

    public Etape createEtape(@Valid @RequestBody Etape etape) {
        return etapeRepository.save(etape);
    }
}
