package pl.recruitment.retentionmanager.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.recruitment.retentionmanager.model.term.Term;

import java.util.List;

@Service
@Primary
public interface TermsServices {
    List<Term> findAll();
    void save(Term term);
    Term findAllById(Long id);
    void add(Term term);
    List<Term> fetchActive();
    void delete(double id);
}
