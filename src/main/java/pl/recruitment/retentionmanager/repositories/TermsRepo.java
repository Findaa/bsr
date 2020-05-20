package pl.recruitment.retentionmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.recruitment.retentionmanager.model.Term;

import java.util.List;

@Repository
@Component
public interface TermsRepo extends JpaRepository<Term, Long> {
    List<Term> findAll();
    Term findAllById(Long id);
    List<Term> findAllByActive(boolean active);
}
