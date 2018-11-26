package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.GET)
    public String getRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "editProfile";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.POST)
    public String postRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "profile";
    }

    @RequestMapping(value = "/citizens/{citizenId}/profile", method = RequestMethod.GET)
    public String getRequestPatientProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "profile";
    }
}
