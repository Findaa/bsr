package pl.recruitment.retentionmanager.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.recruitment.retentionmanager.services.TermsServices;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping("/api")
public class ApiController {

    @Autowired
    TermsServices terms;

    @GetMapping("/terms")
    @ResponseBody
    public String jsonTerms(){
        return new Gson().toJson(terms.findAll());
    }

    @GetMapping("/aterms")
    @ResponseBody
    public String jsonActiveTerms(){
        return new Gson().toJson(terms.fetchActive().toString());
    }
}
