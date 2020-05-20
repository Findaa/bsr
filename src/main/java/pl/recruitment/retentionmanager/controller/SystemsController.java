package pl.recruitment.retentionmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.recruitment.retentionmanager.services.ControllerHelper;

import javax.servlet.http.HttpSession;

@Controller
public class SystemsController {
    @Autowired
    public SystemsController(ControllerHelper helper) {
        this.helper = helper;
    }
    ControllerHelper helper;

    @GetMapping("/systems")
    public String processList(HttpSession session) {
        return helper.setSystemData(session);
    }

    @GetMapping("/displaysystems")
    public String displayList() {
        return "systems";
    }
}
