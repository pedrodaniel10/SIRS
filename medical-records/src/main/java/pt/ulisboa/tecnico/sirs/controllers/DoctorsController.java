package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorsController {

    private static Logger log = Logger.getLogger(DoctorsController.class);

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String getRequestDoctors(Map<String, Object> model) {
        return "doctors";
    }

    @RequestMapping(value = "/doctors/add", method = RequestMethod.GET)
    public String getRequestAddDoctor(Map<String, Object> model) {
        return "addDoctor";
    }

    @RequestMapping(value = "/doctors/{citizenId}/add", method = RequestMethod.GET)
    public String postRequestAddDoctor(Map<String, Object> model) {
        return "doctors";
    }

    @RequestMapping(value = "/doctors/{citizenId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteDoctor(Map<String, Object> model, @PathVariable String citizenId) {
        return "doctors";
    }
}
