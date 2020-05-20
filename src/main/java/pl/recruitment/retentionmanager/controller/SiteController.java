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

    @GetMapping("/retention_manager_war_exploded/")
    public String getSiteFromServer() {
        return "hello";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
}
