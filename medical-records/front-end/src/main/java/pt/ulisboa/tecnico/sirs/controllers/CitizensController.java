package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
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
import pt.ulisboa.tecnico.sirs.api.dataobjects.MedicalRecord;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class CitizensController {

    private static Logger log = Logger.getLogger(CitizensController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/citizens", method = RequestMethod.GET)
    public String getRequestCitizens(HttpServletResponse response,
                                     Map<String, Object> model,
                                     @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering getCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        List<Citizen> citizens = service.getCitizens(subject);
        model.put("citizen", subject);
        model.put("citizens", citizens);
        return (citizens != null)? "citizens": "404";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.GET)
    public String getRequestAddCitizen(HttpServletResponse response,
                                       Map<String, Object> model,
                                       @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering addCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        boolean result = service.getAddCitizensPage(subject);
        return result? "addCitizen": "404";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.POST)
    public String postRequestAddCitizen(HttpServletResponse response,
                                        Map<String, Object> model,
                                        @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering addCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Citizen> citizens = service.addCitizen(subject, null);
        model.put("citizens", citizens);
        return (citizens != null)? "citizens": "404";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.GET)
    public String getRequestEditProfile(HttpServletResponse response,
                                        Map<String, Object> model, @PathVariable String citizenId,
                                        @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering editCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        Citizen citizenToEdit = service.getEditCitizensPage(subject, citizenId);
        model.put("citizenToEdit", citizenToEdit);
        return (citizenToEdit != null)? "editCitizen": "404";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.POST)
    public String postRequestEditProfile(HttpServletResponse response,
                                         Map<String, Object> model, @PathVariable String citizenId,
                                         @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering editCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Citizen> citizens = service.editCitizen(subject, null);
        model.put("citizens", citizens);
        return (citizens != null)? "redirect:/citizens": "404";
    }

    @RequestMapping(value = "/citizens/{citizenId}/profile", method = RequestMethod.GET)
    public String getRequestPatientProfile(HttpServletResponse response,
                                           Map<String, Object> model, @PathVariable String citizenId,
                                           @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering profileCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        if (subject != null) {
            if(subject.getCitizenId().equals(citizenId)) {
                model.put("profile", subject);
                List<MedicalRecord> records = service.getMedicalRecordsByCitizenId(subject, subject.getCitizenId());
                model.put("records", records);
                return "profile";
            }
            Citizen profile = service.getCitizen(subject, citizenId);
            model.put("profile", profile);
            List<MedicalRecord> records = service.getMedicalRecordsByCitizenId(subject, profile.getCitizenId());
            model.put("records", records);
            return "profile";
        }
        return "404";
    }
}
