package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppointmentsController {

    private static Logger log = Logger.getLogger(AppointmentsController.class);

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String getRequestAppointments(Map<String, Object> model) {
        return "appointments";
    }

    @RequestMapping(value = "/appointments/add", method = RequestMethod.GET)
    public String getRequestAddAppointment(Map<String, Object> model) {
        return "addAppointment";
    }

    @RequestMapping(value = "/appointments/add", method = RequestMethod.POST)
    public String postRequestAddAppointment(Map<String, Object> model) {
        return "appointments";
    }

    @RequestMapping(value = "/appointments/{appointmentId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteAppointment(Map<String, Object> model, @PathVariable String appointmentId) {
        return "appointments";
    }
}
