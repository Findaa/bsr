package pl.recruitment.retentionmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import pl.recruitment.retentionmanager.services.TermsServices;

@ControllerAdvice
public class ErrorController {
    @Autowired
    TermsServices terms;

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView handle404(HttpClientErrorException.NotFound e) {
        ModelAndView model = new ModelAndView();
        model.setViewName("list");
        return model;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ModelAndView handle500(HttpClientErrorException.NotFound e) {
        ModelAndView model = new ModelAndView();
        model.addObject("list", terms.findAll());
        model.setViewName("list");
        return model;
    }
}