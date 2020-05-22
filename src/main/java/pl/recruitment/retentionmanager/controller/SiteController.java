package pl.recruitment.retentionmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.implementations.ControllerHelperImpl;

@Controller
public class SiteController {
    @Autowired
    public SiteController(ControllerHelperImpl helper) {
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
