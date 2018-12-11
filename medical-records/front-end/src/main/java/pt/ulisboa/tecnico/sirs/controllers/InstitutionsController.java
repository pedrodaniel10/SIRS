package pt.ulisboa.tecnico.sirs.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
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
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;

import java.util.List;
import java.util.Map;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class InstitutionsController {

    private static Logger log = Logger.getLogger(InstitutionsController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/institutions", method = RequestMethod.GET)
    public String getRequestInstitutions(HttpServletResponse response,
                                         Map<String, Object> model,
                                         @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering getInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Institution> institutions = service.getInstitutions(subject);
        model.put("institutions", institutions);
        return (institutions != null)? "institutions": "404";
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.GET)
    public String getRequestAddInstitution(HttpServletResponse response,
                                           Map<String, Object> model,
                                           @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering addInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        boolean result = service.getAddInstitutionsPage(subject);
        return result? "addInstitution": "404";
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.POST)
    public String postRequestAddInstitution(HttpServletResponse response,
                                            Map<String, Object> model,
                                            @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering addInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Institution> institutions = service.addInstitution(subject, null);
        model.put("institutions", institutions);
        return (institutions != null)? "institutions": "404";
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.GET)
    public String getRequestEditInstitution(HttpServletResponse response,
                                            Map<String, Object> model, @PathVariable String institutionId,
                                            @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering editInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        Institution institutionToEdit = service.getEditInstitutionPage(subject, NumberUtils.toInt(institutionId));
        model.put("institutionToEdit", institutionToEdit);
        return (institutionToEdit != null)? "editInstitution": "404";
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.POST)
    public String postRequestEditInstitution(HttpServletResponse response,
                                             Map<String, Object> model, @PathVariable String institutionId,
                                             @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering editInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Institution> institutions = service.editInstitution(subject, null);
        model.put("institutions", institutions);
        return (institutions != null)? "redirect:/institutions" : "404";
    }
}
