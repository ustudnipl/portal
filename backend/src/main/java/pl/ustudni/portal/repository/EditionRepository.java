package pl.ustudni.portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ustudni.portal.domain.Edition;

@Repository
public interface EditionRepository extends CrudRepository<Edition, Long> {
    Iterable<Edition> findAllByOrderByIdDesc();
}
