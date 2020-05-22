package pl.recruitment.retentionmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.implementations.ControllerHelperImpl;

import javax.servlet.http.HttpSession;

/**
 * Controller used mostly to operate on @System object and it's views.
 * @author: Michal Cop
 */
@Controller
public class SystemsController {
    @Autowired
    public SystemsController(ControllerHelperImpl helper) {
        this.helper = helper;
    }

    ControllerHelper helper;

    @GetMapping("/systems")
    public String processList(HttpSession session) {
        return helper.setSystemData(session);
    }

    @GetMapping("/addsys")
    public String addTerm(Model model) {
        model.addAttribute("newsystem", new System());
        return "redirect:/editsystem";
    }

    @GetMapping("/editsystem/{idd}")
    public String editTerms(@PathVariable double idd, HttpSession session, Model model) {
        Long id = (long) idd;
        helper.processEditSystems(id, session, model);
        return "redirect:/editsystem";
    }

    @PostMapping("/updatesystem")
    public String afterEdit(@ModelAttribute("newsys") System system, HttpSession session) {
        return helper.afterEditSystem(system, session);
    }

    @GetMapping("/editsystem")
    public String editor(HttpSession session) {
        session.setAttribute("sys", session.getAttribute("sys"));
        return "editsystem";
    }

    @PostMapping("/sysdelete/{idd}")
    public String delete(@PathVariable double idd) {
        helper.sysdelete(idd);
        return "redirect:/systems";
    }
}
