package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static Logger log = Logger.getLogger(ErrorController.class);

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(Map<String, Object> model) {
        return "401";
    }

    public String getErrorPath() {
        return "/error";
    }
}
