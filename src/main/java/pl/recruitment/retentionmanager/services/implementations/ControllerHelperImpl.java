package pl.recruitment.retentionmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.recruitment.retentionmanager.model.AmountPeriod;
import pl.recruitment.retentionmanager.model.AmountType;
import pl.recruitment.retentionmanager.model.Term;
import pl.recruitment.retentionmanager.model.TermDto;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.TermsServices;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ControllerHelperImpl implements ControllerHelper {
    @Autowired
    public ControllerHelperImpl(TermsServices terms) {
        this.terms = terms;
    }

    TermsServices terms;

    @Override
    public String setProductsData(HttpSession session){
        System.out.println("Got into setProductsData with returning list of: " + terms.findAll());
        session.setAttribute("list", terms.findAll());
        return "redirect:/displaylist";
    }

    @Override
    public String setActiveProductsData(HttpSession session){
        session.setAttribute("list", terms.fetchActive());
        return "redirect:/displaylist";
    }

    //todo: add true/false button for net/brutton and month/year.
    @Override
    public String processEditTerms(Long id, HttpSession session, Model model){
        Term term = terms.findAllById(id);
        session.setAttribute("term", term);
        session.setAttribute("id", id);
        System.out.println("ProcessEditTerms id: " + id);
        model.addAttribute("newterm", new TermDto());
        return "redirect:/editterm";
    }

    @Override
    public String afterEdit(TermDto newTerm, HttpSession session) {
        Term t = new Term();
        try {
            Long id = (long) session.getAttribute("id");
            t = terms.findAllById(id);

        } catch(Exception e) {
            e.printStackTrace();
        }

        t.setActive(newTerm.isActive());
        t.setAmount(Double.parseDouble(newTerm.getAmount()));
        t.setAmountPeriod(AmountPeriod.valueOf(newTerm.getAmountPeriod()));
        t.setAmountType(AmountType.valueOf(newTerm.getAmountType()));
        t.setAuthorizationPercent(Integer.parseInt(newTerm.getAuthorizationPercent()));
        t.setFromDate(dateService(newTerm.getFromDate()));
        t.setToDate(dateService(newTerm.getToDate()));
        t.setOrderNumber(newTerm.getOrderNumber());
        t.setRequest(Integer.parseInt(newTerm.getRequest()));
        t.setSystem(newTerm.getSystem());
        terms.save(t);
        session.setAttribute("list", terms.findAll());
        return "redirect:/displaylist";
    }

    @Override
    public void delete(double id){
        terms.delete(id);
    }

    private LocalDate dateService(String dateStr){
        System.out.println("Date string: " + dateStr);
        System.out.println("Substr: " + dateStr.substring(0, 10));
        return LocalDate.parse(dateStr.substring(0, 10));
    }
}
