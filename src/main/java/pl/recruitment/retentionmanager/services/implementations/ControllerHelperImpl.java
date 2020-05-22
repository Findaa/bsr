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

/**
 * Implementation of @ControllerHelper interface.
 */
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

    /**
     * Method used to set data in @list.jsp view terms table.
     * @param session @HttpSession passed to helper.
     * @return redirect to a component and send data with @HttpSession.
     */
    @Override
    public String setProductsData(HttpSession session) {
        session.setAttribute("list", terms.findAll());
        return "redirect:/displaylist";
    }

    /**
     * Method used to set data in @systems.jsp view table
     * @param session @HttpSession passed to helper.
     * @return @systems.jsp component with data provided.
     */
    @Override
    public String setSystemData(HttpSession session) {
        session.setAttribute("list", systems.findAll());
        return "systems";
    }

    /**
     * Method used to set data in @list.jsp view terms table with active terms only
     * @param session @HttpSession passed to helper.
     * @return redirect to a component and send data with @HttpSession.
     */
    @Override
    public String setActiveProductsData(HttpSession session) {
        session.setAttribute("list", terms.fetchActive());
        return "redirect:/displaylist";
    }

    /**
     * Method prepares @editterm.jsp view data to allow edition/addition of @Term.
     * @param id Term id (if null, new is created).
     * @param session @HttpSession used to pass edition data.
     * @param model Assigned as variable table for form inside @editterm.jsp
     */
    //todo: add true/false button for net/brutton and month/year.
    @Override
    public void processEditTerms(Long id, HttpSession session, Model model) {
        Term term = terms.findAllById(id);
        session.setAttribute("term", term);
        session.setAttribute("id", id);
        model.addAttribute("newterm", new TermDto());
    }

    /**
     * Method prepares @editsystem.jsp view data to allow edition/addition of @System.
     * @param id System id (if null, new is created).
     * @param session @HttpSession used to pass edition data.
     * @param model Assigned as variable table for form inside @editsystem.jsp
     */
    @Override
    public void processEditSystems(Long id, HttpSession session, Model model) {
        System system = systems.findAllById(id);
        session.setAttribute("sys", system);
        session.setAttribute("id", id);
        model.addAttribute("newsys", new System());
    }

    /**
     * Logic executed after @Term edition form is submitted.
     * Includes crud operations and fills @list.jsp view with data.
     * @param newTerm New @TermDto object passed from a form.
     * @param session @HttpSession object that keeps data through process.
     * @return @list component filled with new data.
     */
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

    /**
     * Logic executed after @System edition form is submitted.
     * Includes crud operations and fills @systems.jsp view with data.
     * Also updates system and system name fields in @Term object that includes edited @System.
     * @param system New @System object passed from a form.
     * @param session @HttpSession object that keeps data through process.
     * @return @systems.jsp component filled with new data.
     */
    @Override
    public String afterEditSystem(System system, HttpSession session) {
        Long id = (long) session.getAttribute("id");
        java.lang.System.out.println("Id: " + id);
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
        return "systems";
    }

    /**
     * Delete endpoint handler.
     * @param id @Term to be deleted.
     */
    @Override
    public void delete(double id) {
        terms.delete(id);
    }

    /**
     * Delete endpoint handler.
     * @param id @System to be deleted.
     */
    @Override
    public void sysdelete(double id) {
        systems.delete(id);
    }

    /**
     * Import xlsx data to a @Term object.
     * @param path Path to a file provided by a user form.
     */
    @Override
    public void createRecordsFromXlxs(String path) {
        poi.create(path);
    }

    private LocalDate dateService(String dateStr) {
        return LocalDate.parse(dateStr.substring(0, 10));
    }
}
