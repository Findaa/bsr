package pl.recruitment.retentionmanager.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.recruitment.retentionmanager.model.term.Term;

import java.util.List;

/**
 * This interface describes available crud operations on @Term object repository.
 */
@Service
@Primary
public interface TermsServices {
    List<Term> findAll();

    Term findAllById(Long id);

    void add(Term term);

    List<Term> fetchActive();

    void delete(double id);

    Term findAllBySystemName(String name);
}
