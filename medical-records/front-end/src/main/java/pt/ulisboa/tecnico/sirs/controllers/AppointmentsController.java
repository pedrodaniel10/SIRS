package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.DocPatRelation;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class AppointmentsController {

    private static Logger log = Logger.getLogger(AppointmentsController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String getRequestAppointments(Map<String, Object> model,
                                         @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering getAppointments function");

        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen(authTokenCookie);
        model.put("citizen", subject);
        List<DocPatRelation> appointments = service.getAppointments(subject);
        model.put("appointments", appointments);
        return (appointments != null)? "appointments": "404";
    }

    @RequestMapping(value = "/appointments/add", method = RequestMethod.GET)
    public String getRequestAddAppointment(Map<String, Object> model,
                                           @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering addAppointments function");

        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen(authTokenCookie);
        model.put("citizen", subject);
        DocPatRelation appointment = new DocPatRelation(true);
        appointment.setPatient(new Citizen());
        appointment.setDoctor(new Citizen());
        model.put("newAppointment", appointment);
        boolean result = service.getAddAppointmentsPage(subject);
        return result? "addAppointment": "404";
    }

    @RequestMapping(value = "/appointments/add", method = RequestMethod.POST)
    public String postRequestAddAppointment(@ModelAttribute("newAppointment")DocPatRelation appointment, Map<String, Object> model,
                                            @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering addAppointments function");

        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen(authTokenCookie);
        model.put("citizen", subject);
        List<DocPatRelation> appointments = service.addAppointment(subject, appointment);
        model.put("appointments", appointments);
        return (appointments != null)? "appointments": "404";
    }

    @RequestMapping(value = "/appointments/{appointmentId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteAppointment(Map<String, Object> model, @PathVariable String appointmentId,
                                              @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering deleteAppointments function");

        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen(authTokenCookie);
        model.put("citizen", subject);
        List<DocPatRelation> appointments = service.deleteAppointment(subject, NumberUtils.toInt(appointmentId));
        model.put("appointments", appointments);
        return (appointments != null)? "appointments": "404";
    }
}
