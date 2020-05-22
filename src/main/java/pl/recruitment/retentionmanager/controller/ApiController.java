package pl.recruitment.retentionmanager.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.model.term.TermDto;
import pl.recruitment.retentionmanager.services.ControllerHelper;
import pl.recruitment.retentionmanager.services.SystemServices;
import pl.recruitment.retentionmanager.services.TermsServices;
import pl.recruitment.retentionmanager.services.implementations.ControllerHelperImpl;
import pl.recruitment.retentionmanager.services.implementations.SystemServicesImpl;
import pl.recruitment.retentionmanager.services.implementations.TermsServicesImpl;

import javax.servlet.http.HttpSession;

/**
 * API controller realised as bonus task with few example methods returning json.
 * @author: Michal Cop
 */
@RestController
//This is meant to allow react app to fetch data.
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ApiController {
    @Autowired
    public ApiController(
            TermsServicesImpl terms, SystemServicesImpl systems, ControllerHelperImpl helper) {
        this.terms = terms;
        this.systems = systems;
        this.helper = helper;
    }

    TermsServices terms;
    SystemServices systems;
    ControllerHelper helper;

    @GetMapping("/terms")
    @ResponseBody
    public String jsonTerms() {
        return new Gson().toJson(terms.findAll());
    }

    @GetMapping("/aterms")
    @ResponseBody
    public String jsonActiveTerms() {
        return new Gson().toJson(terms.fetchActive().toString());
    }

    @PostMapping("/addterms")
    public String addTerms(@RequestBody TermDto term){
       helper.afterEdit(term, null);
       return "redirect:http://localhost:3000";
    }

    @PostMapping("/addsystem")
    public String addSystem(@RequestBody System system, HttpSession session){
        helper.afterEditSystem(system, session);
        return "redirect:http://localhost:3000";
    }
}
