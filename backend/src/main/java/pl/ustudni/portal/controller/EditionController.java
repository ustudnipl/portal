package pl.ustudni.portal.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ustudni.portal.domain.Edition;
import pl.ustudni.portal.repository.EditionRepository;

@RestController()
public class EditionController {

    private final EditionRepository editionRepository;

    public EditionController(EditionRepository editionRepository) {
        this.editionRepository = editionRepository;
    }

    @GetMapping("/edition")
    public Iterable<Edition> findAll() {
        return editionRepository.findAllByOrderByIdDesc();
    }

    @PostMapping("/edition")
    @Secured("ADMIN")
    public void saveOrUpdate(@RequestBody Edition edition) {
        editionRepository.save(edition);
    }
}
