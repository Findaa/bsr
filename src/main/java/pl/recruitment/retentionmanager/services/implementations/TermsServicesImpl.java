package pl.recruitment.retentionmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.recruitment.retentionmanager.model.term.Term;
import pl.recruitment.retentionmanager.repositories.TermsRepo;
import pl.recruitment.retentionmanager.services.TermsServices;

import java.util.List;

/**
 * Class implementing TermsServices methods, mostly CRUD operations.
 */
@Component
public class TermsServicesImpl implements TermsServices {
    @Autowired
    public TermsServicesImpl(TermsRepo repo) {
        this.repo = repo;
    }

    TermsRepo repo;

    @Override
    public List<Term> findAll() {
        return repo.findAll();
    }

    @Override
    public Term findAllById(Long id) {
        return repo.findAllById(id);
    }

    @Override
    public void add(Term term) {
        repo.save(term);
    }

    @Override
    public List<Term> fetchActive() {
        return repo.findAllByActive(true);
    }

    @Override
    public void delete(double id) {
        repo.delete(repo.findAllById((long) id));
    }

    @Override
    public Term findAllBySystemName(String name) {
        return repo.findAllBySystemName(name);
    }
}