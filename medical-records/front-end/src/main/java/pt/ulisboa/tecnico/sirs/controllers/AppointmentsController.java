package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.DocPatRelation;

@Controller
public class AppointmentsController {

    private static Logger log = Logger.getLogger(AppointmentsController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String getRequestAppointments(Map<String, Object> model) {
        log.info("Entering getAppointments function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<DocPatRelation> appointments = service.getAppointments(subject);
        model.put("appointments", appointments);
        return (appointments != null)? "appointments": "404";
    }

    @RequestMapping(value = "/appointments/add", method = RequestMethod.GET)
    public String getRequestAddAppointment(Map<String, Object> model) {
        log.info("Entering addAppointments function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        boolean result = service.getAddAppointmentsPage(subject);
        return result? "addAppointment": "404";
    }

    @RequestMapping(value = "/appointments/add", method = RequestMethod.POST)
    public String postRequestAddAppointment(Map<String, Object> model) {
        log.info("Entering addAppointments function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<DocPatRelation> appointments = service.addAppointment(subject, null);
        model.put("appointments", appointments);
        return (appointments != null)? "appointments": "404";
    }

    @RequestMapping(value = "/appointments/{appointmentId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteAppointment(Map<String, Object> model, @PathVariable String appointmentId) {
        log.info("Entering deleteAppointments function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<DocPatRelation> appointments = service.deleteAppointment(subject, NumberUtils.toInt(appointmentId));
        model.put("appointments", appointments);
        return (appointments != null)? "appointments": "404";
    }
}
