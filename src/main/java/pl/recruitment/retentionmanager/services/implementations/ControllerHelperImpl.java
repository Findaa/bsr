package pl.recruitment.retentionmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.model.term.AmountPeriod;
import pl.recruitment.retentionmanager.model.term.AmountType;
import pl.recruitment.retentionmanager.model.term.Term;
import pl.recruitment.retentionmanager.model.term.TermDto;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.PoiUtility;
import pl.recruitment.retentionmanager.services.SystemServices;
import pl.recruitment.retentionmanager.services.TermsServices;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;


@Component
public class ControllerHelperImpl implements ControllerHelper {
    @Autowired
    public ControllerHelperImpl(TermsServicesImpl terms, SystemServicesImpl systems, PoiUtilityImpl poi) {
        this.terms = terms;
        this.systems = systems;
        this.poi = poi;
    }

    TermsServices terms;
    SystemServices systems;
    PoiUtility poi;

    @Override
    public String setProductsData(HttpSession session) {
        session.setAttribute("list", terms.findAll());
        return "redirect:/displaylist";
    }

    @Override
    public String setSystemData(HttpSession session) {
        session.setAttribute("list", systems.findAll());
        return "systems";
    }

    @Override
    public String setActiveProductsData(HttpSession session) {
        session.setAttribute("list", terms.fetchActive());
        return "redirect:/displaylist";
    }

    //todo: add true/false button for net/brutton and month/year.
    @Override
    public void processEditTerms(Long id, HttpSession session, Model model) {
        Term term = terms.findAllById(id);
        session.setAttribute("term", term);
        session.setAttribute("id", id);
        model.addAttribute("newterm", new TermDto());
    }

    @Override
    public void processEditSystems(Long id, HttpSession session, Model model) {
        System system = systems.findAllById(id);
        session.setAttribute("sys", system);
        session.setAttribute("id", id);
        model.addAttribute("newsys", new System());
    }

    @Override
    public String afterEdit(TermDto newTerm, HttpSession session) {
        Term t = new Term();
        if (systems.findAllByName(newTerm.getSystem()) == null) {
            systems.save(new System(newTerm.getSystem()));
        }
        try {
            Long id = (long) session.getAttribute("id");
            t = terms.findAllById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            t.setActive(newTerm.isActive());
            t.setAmount(Double.parseDouble(newTerm.getAmount()));
            t.setAmountPeriod(AmountPeriod.valueOf(newTerm.getAmountPeriod()));
            t.setAmountType(AmountType.valueOf(newTerm.getAmountType()));
            t.setAuthorizationPercent(Integer.parseInt(newTerm.getAuthorizationPercent()));
            t.setFromDate(dateService(newTerm.getFromDate()));
            t.setToDate(dateService(newTerm.getToDate()));
            t.setOrderNumber(newTerm.getOrderNumber());
            t.setRequest(Integer.parseInt(newTerm.getRequest()));
            t.setSystemName(newTerm.getSystem());
            t.setSystem(systems.findAllByName(t.getSystemName()));
            terms.save(t);
        } catch (NullPointerException npe) {
            java.lang.System.out.println("Catched npe, probably in setSystem. Ensure system with called name is present in database.");
            npe.printStackTrace();
        }
        session.setAttribute("list", terms.findAll());
        return "list";
    }

    @Override
    public String afterEditSystem(System system, HttpSession session) {
        Long id = (long) session.getAttribute("id");
        System s = systems.findAllById(id);
        Term t = terms.findAllBySystemName(s.getName());

        s.setClient(system.getClient());
        s.setInfo(system.getInfo());
        s.setTechnologies(system.getTechnologies());
        s.setName(system.getName());
        t.setSystem(s);
        t.setSystemName(s.getName());
        systems.save(s);
        terms.save(t);

        session.setAttribute("list", systems.findAll());
        return "list";
    }

    @Override
    public void delete(double id) {
        terms.delete(id);
    }

    @Override
    public void sysdelete(double id) {
        systems.delete(id);
    }

    @Override
    public void createRecordsFromXlxs(String path) {
        poi.create(path);
    }

    private LocalDate dateService(String dateStr) {
        return LocalDate.parse(dateStr.substring(0, 10));
    }
}
