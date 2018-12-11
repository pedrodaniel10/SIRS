package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;
import pt.ulisboa.tecnico.sirs.api.dataobjects.MedicalRecord;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class MedicalRecordController {

    private static Logger log = Logger.getLogger(MedicalRecordController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/citizens/{citizenId}/medrec/{idMedRec}/view", method = RequestMethod.GET)
    public String getRequestMedicalRecord(HttpServletResponse response,
                                          Map<String, Object> model, @PathVariable String citizenId, @PathVariable String idMedRec,
                                          @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering getMedicalRecord function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        MedicalRecord record = service.getMedicalRecord(subject, citizenId, idMedRec);
        if (record == null)
            return "404";
        Citizen doctor = service.getCitizen(subject, record.getDoctorCitizenId());
        Institution institution = service.getInstitution(subject, record.getInstitutionId());
        model.put("record", record);
        model.put("doctor", doctor);
        model.put("institution", institution);
        return "medicalRecord";
    }

    @RequestMapping(value = "/citizens/{citizenId}/medrec/create", method = RequestMethod.GET)
    public String getRequestCreateMedicalRecord(HttpServletResponse response,
                                                Map<String, Object> model, @PathVariable String citizenId,
                                                @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering createMedicalRecord function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        Citizen doctor = service.getCitizen(subject, subject.getCitizenId());
        if (doctor != null) {
            boolean result = service.getAddMedicalRecordPage(subject, citizenId);
            if (result) {
                Citizen patient = service.getCitizen(subject, citizenId);
                Doctor doc = service.getDoctor(subject, citizenId);
                Institution institution = service.getInstitution(subject, doc.getInstitutionId());
                model.put("patient", patient);
                model.put("doctor", doctor);
                model.put("institution", institution);
            }
            return result ? "createMedicalRecord" : "404";
        }
        return null;
    }

    @RequestMapping(value = "/citizens/{citizenId}/medrec/create", method = RequestMethod.POST)
    public String postRequestCreateMedicalRecord(HttpServletResponse response,
                                                 Map<String, Object> model, @PathVariable String citizenId,
                                                 @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        return "medicalRecord";
    }

}
