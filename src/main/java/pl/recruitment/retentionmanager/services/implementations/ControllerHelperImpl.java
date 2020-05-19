package pl.recruitment.retentionmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.recruitment.retentionmanager.model.Terms;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.TermsServices;

import java.util.List;

@Component
public class ControllerHelperImpl implements ControllerHelper {
    @Autowired
    public ControllerHelperImpl(TermsServices terms) {
        this.terms = terms;
    }

    TermsServices terms;

    @Override
    public List<Terms> setProductsData(){
        System.out.println("Got into setProductsData with returning list of: " + terms.findAll());
        return terms.findAll();
    }
}