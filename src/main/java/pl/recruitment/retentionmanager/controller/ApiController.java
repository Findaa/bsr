package pl.recruitment.retentionmanager.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.recruitment.retentionmanager.services.TermsServices;

/**
 * API controller realised as bonus task with few example methods returning json.
 */
@RestController
//This is meant to allow react app to fetch data.
@CrossOrigin(origins = "http://localhost:3000")
//endpoints accessible after domain/api/...
@RequestMapping("/api")
public class ApiController {
    @Autowired
    TermsServices terms;

    @GetMapping("/terms")
    //Returns json
    @ResponseBody
    public String jsonTerms() {
        return new Gson().toJson(terms.findAll());
    }

    @GetMapping("/aterms")
    @ResponseBody
    public String jsonActiveTerms() {
        return new Gson().toJson(terms.fetchActive().toString());
    }
}
