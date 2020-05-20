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

import javax.servlet.http.HttpSession;

@Controller
public class SiteController {
    @Autowired
    public SiteController(ControllerHelper helper) {
        this.helper = helper;
    }

    ControllerHelper helper;

    @GetMapping("/")
    public String mainSite(Model model) {
        model.addAttribute("message", "Hello");
        return "hello";
    }

    @GetMapping("/main")
    public String mainSiteFromRedirect(Model model) {
        model.addAttribute("message", "Hello");
        return "hello";
    }

    @GetMapping("/list")
    public String processList(HttpSession session) {
        return helper.setProductsData(session);
    }

    @GetMapping("/displaylist")
    public String displayList() {
        return "list";
    }

    @GetMapping("/retention_manager_war_exploded/")
    public String getSiteFromServer() {
        return "hello";
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

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

    @PostMapping("/delete/{idd}")
    public String delete(@PathVariable double idd, HttpSession session) {
        helper.delete(idd);
        return helper.setProductsData(session);
    }

}
