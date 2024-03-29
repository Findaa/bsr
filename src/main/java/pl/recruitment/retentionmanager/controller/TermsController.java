package pl.recruitment.retentionmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.recruitment.retentionmanager.model.term.TermDto;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.implementations.ControllerHelperImpl;

import javax.servlet.http.HttpSession;

/**
 * Controller used mostly to operate on @Term object and it's views.
 * @author: Michal Cop
 */
@Controller
public class TermsController {
    @Autowired
    public TermsController(ControllerHelperImpl helper) {
        this.helper = helper;
    }

    ControllerHelper helper;

    @GetMapping("/list")
    public String processList(HttpSession session) {
        return helper.setProductsData(session);
    }

    @GetMapping("/displaylist")
    public String displayList() {
        return "list";
    }

    @PostMapping("/delete/{idd}")
    public String delete(@PathVariable double idd, HttpSession session) {
        helper.delete(idd);
        return helper.setProductsData(session);
    }

    @GetMapping("/add")
    public String addTerm(Model model) {
        model.addAttribute("newterm", new TermDto());
        return "redirect:/editterm";
    }

    @GetMapping("/edit/{idd}")
    public String editTerms(@PathVariable double idd, HttpSession session, Model model) {
        Long id = (long) idd;
        helper.processEditTerms(id, session, model);
        return "redirect:/editterm";
    }

    @GetMapping("/editterm")
    public String editor(HttpSession session) {
        session.setAttribute("term", session.getAttribute("term"));
        return "editterm";
    }

    @PostMapping("/updateterm")
    public String afterEdit(@ModelAttribute("newterm") TermDto newTerm, HttpSession session) {
        return helper.afterEdit(newTerm, session);
    }

    @GetMapping("/alist")
    public String showActive(HttpSession session) {
        return helper.setActiveProductsData(session);
    }

    @GetMapping("/loadcsv")
    public String loadCsv(Model model) {
        return "csvloader";
    }

    @PostMapping("/processLoadCsv")
    public String loadCsv(@ModelAttribute("path") String path, HttpSession session) {
        helper.createRecordsFromXlxs(path);
        return helper.setProductsData(session);
    }
}