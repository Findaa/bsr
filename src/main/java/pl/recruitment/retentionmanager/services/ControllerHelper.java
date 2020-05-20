package pl.recruitment.retentionmanager.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.recruitment.retentionmanager.model.term.TermDto;

import javax.servlet.http.HttpSession;

@Service
public interface ControllerHelper {
    String setProductsData(HttpSession session);
    void processEditTerms(Long id, HttpSession session, Model model);
    String afterEdit(TermDto newTerm, HttpSession session);
    String setActiveProductsData(HttpSession session);
    void delete(double id);
}
