package pl.recruitment.retentionmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.recruitment.retentionmanager.model.term.Term;

import java.util.List;

/**
 * Java persistence repository. Extended interface allows for crud operations on @Entity object.
 * Methods described in implementations.
 * @author: Michal Cop
 */
@Repository
@Component
public interface TermsRepo extends JpaRepository<Term, Long> {
    List<Term> findAll();
    Term findAllById(Long id);
    List<Term> findAllByActive(boolean active);
    Term findAllBySystemName(String name);

}