package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getRequestProfile(Map<String, Object> model) {
        return "profile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String getRequestEditProfile(Map<String, Object> model) {
        return "editProfile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String postRequestEditProfile(Map<String, Object> model) {
        return "profile";
    }

    @RequestMapping(value = "/patient/{idPatient}", method = RequestMethod.GET)
    public String getRequestPatientProfile(Map<String, Object> model, @PathVariable String idPatient) {
        return "profile";
    }
}
