package pl.recruitment.retentionmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.recruitment.retentionmanager.model.Terms;

import java.util.List;

@Repository
@Component
public interface TermsRepo extends JpaRepository<Terms, Long> {
    List<Terms> findAll();
    Terms findAllById(Long id);
}
