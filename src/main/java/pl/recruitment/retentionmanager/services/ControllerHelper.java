package pl.recruitment.retentionmanager.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.model.term.TermDto;

import javax.servlet.http.HttpSession;

@Service
@Primary
public interface ControllerHelper {
    String setProductsData(HttpSession session);

    String setSystemData(HttpSession session);

    void processEditTerms(Long id, HttpSession session, Model model);

    void processEditSystems(Long id, HttpSession session, Model model);

    String afterEdit(TermDto newTerm, HttpSession session);

    String setActiveProductsData(HttpSession session);

    void delete(double id);

    String afterEditSystem(System system, HttpSession session);

    void sysdelete(double id);

    void createRecordsFromXlxs(String path);
}
