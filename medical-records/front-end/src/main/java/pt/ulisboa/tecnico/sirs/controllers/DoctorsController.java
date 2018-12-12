package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Doctor;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class DoctorsController {

    @Autowired
    private ApplicationContext context;

    private static Logger log = Logger.getLogger(DoctorsController.class);

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String getRequestDoctors(Map<String, Object> model,
                                    @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering getDoctors function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Doctor> doctors = service.getDoctors(subject);
        model.put("doctors", doctors);
        return (doctors != null)? "doctors": "404";
    }

    @RequestMapping(value = "/doctors/add", method = RequestMethod.GET)
    public String getRequestAddDoctor(Map<String, Object> model,
                                      @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering addDoctor function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Doctor> doctors = service.getAddDoctorPage(subject);
        model.put("doctors", doctors);
        return (doctors != null)? "addDoctor": "404";
    }

    @RequestMapping(value = "/doctors/{citizenId}/add", method = RequestMethod.GET)
    public String postRequestAddDoctor(Map<String, Object> model, @PathVariable String citizenId,
                                       @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering addDoctor function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Doctor> doctors = service.addDoctor(subject, citizenId);
        model.put("doctors", doctors);
        return (doctors != null)? "doctors": "404";
    }

    @RequestMapping(value = "/doctors/{citizenId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteDoctor(Map<String, Object> model, @PathVariable String citizenId,
                                         @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering deleteDoctors function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Doctor> doctors = service.deleteDoctor(subject, citizenId);
        model.put("doctors", doctors);
        return (doctors != null) ? "doctors" : "404";
    }
}