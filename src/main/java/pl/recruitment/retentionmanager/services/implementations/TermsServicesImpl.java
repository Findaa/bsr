package pl.recruitment.retentionmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.recruitment.retentionmanager.model.Terms;

import pl.recruitment.retentionmanager.repositories.TermsRepo;
import pl.recruitment.retentionmanager.services.TermsServices;

import java.util.List;

@Component
public class TermsServicesImpl implements TermsServices {
    @Autowired
    public TermsServicesImpl(TermsRepo repo) {
        this.repo = repo;
    }

    TermsRepo repo;

    @Override
    public List<Terms> findAll(){
        return repo.findAll();
    }

    @Override
    public void save(Terms terms){
        repo.save(terms);
    }

    @Override
    public Terms findAllById(Long id){
        return repo.findAllById(id);
    }
}
