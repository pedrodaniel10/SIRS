package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CitizensController {

    private static Logger log = Logger.getLogger(CitizensController.class);

    @RequestMapping(value = "/citizens", method = RequestMethod.GET)
    public String getRequestCitizens(Map<String, Object> model) {
        return "citizens";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.GET)
    public String getRequestAddCitizen(Map<String, Object> model) {
        return "addCitizen";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.POST)
    public String postRequestAddCitizen(Map<String, Object> model) {
        return "citizens";
    }

    @RequestMapping(value = "/citizens/{citizenId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteCitizen(Map<String, Object> model, @PathVariable String citizenId) {
        return "citizens";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.GET)
    public String getRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "editCitizen";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.POST)
    public String postRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "redirect:/citizens";
    }

    @RequestMapping(value = "/citizens/{citizenId}/profile", method = RequestMethod.GET)
    public String getRequestPatientProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "profile";
    }
}
