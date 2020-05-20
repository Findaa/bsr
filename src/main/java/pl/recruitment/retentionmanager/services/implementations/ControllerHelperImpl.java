package pl.recruitment.retentionmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.model.system.SystemDto;
import pl.recruitment.retentionmanager.model.term.AmountPeriod;
import pl.recruitment.retentionmanager.model.term.AmountType;
import pl.recruitment.retentionmanager.model.term.Term;
import pl.recruitment.retentionmanager.model.term.TermDto;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.SystemServices;
import pl.recruitment.retentionmanager.services.TermsServices;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ControllerHelperImpl implements ControllerHelper {
    @Autowired
    public ControllerHelperImpl(TermsServices terms, SystemServices systems) {
        this.terms = terms;
        this.systems = systems;
    }

    TermsServices terms;
    SystemServices systems;

    @Override
    public String setProductsData(HttpSession session) {
        java.lang.System.out.println("Got into setProductsData with returning list of: " + terms.findAll());
        session.setAttribute("list", terms.findAll());
        return "redirect:/displaylist";
    }

    @Override
    public String setSystemData(HttpSession session) {
        List<System> l = systems.findAll();
        List<SystemDto> ld = new ArrayList<>();
        l.forEach(system -> {
            SystemDto dto = new SystemDto(
                    (double) system.getId(), system.getName(), system.getInfo());
            ld.add(dto);
        });
        java.lang.System.out.println("Got into setSystemData with returning list of: " + ld);
        session.setAttribute("list", ld);
        return "redirect:/displaysystems";
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
        java.lang.System.out.println("ProcessEditTerms id: " + id);
        model.addAttribute("newterm", new TermDto());
    }

    @Override
    public String afterEdit(TermDto newTerm, HttpSession session) {
        Term t = new Term();
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
        return "redirect:/displaylist";
    }

    @Override
    public void delete(double id) {
        terms.delete(id);
    }

    private LocalDate dateService(String dateStr) {
        java.lang.System.out.println("Date string: " + dateStr);
        java.lang.System.out.println("Substr: " + dateStr.substring(0, 10));
        return LocalDate.parse(dateStr.substring(0, 10));
    }
}
