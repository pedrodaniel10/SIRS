package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientsController {

    private static Logger log = Logger.getLogger(PatientsController.class);

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String getRequestAppointments(Map<String, Object> model) {
        return "patients";
    }

}
