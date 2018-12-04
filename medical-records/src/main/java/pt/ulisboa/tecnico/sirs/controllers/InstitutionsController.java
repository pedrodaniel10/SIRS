package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InstitutionsController {

    private static Logger log = Logger.getLogger(InstitutionsController.class);

    @RequestMapping(value = "/institutions", method = RequestMethod.GET)
    public String getRequestInstitutions(Map<String, Object> model) {
        return "institutions";
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.GET)
    public String getRequestAddInstitution(Map<String, Object> model) {
        return "addInstitution";
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.POST)
    public String postRequestAddInstitution(Map<String, Object> model) {
        return "institutions";
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.GET)
    public String getRequestEditInstitution(Map<String, Object> model, @PathVariable String institutionId) {
        return "editInstitution";
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.POST)
    public String postRequestEditInstitution(Map<String, Object> model, @PathVariable String institutionId) {
        return "institutions";
    }

    @RequestMapping(value = "/institutions/{institutionId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteInstitution(Map<String, Object> model, @PathVariable String institutionId) {
        return "institutions";
    }
}
