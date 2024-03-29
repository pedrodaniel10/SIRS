package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;
import pt.ulisboa.tecnico.sirs.api.exceptions.AdminException;
import pt.ulisboa.tecnico.sirs.interfaces.DataObjectCreation;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class InstitutionsController {

    private static Logger log = Logger.getLogger(InstitutionsController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/institutions", method = RequestMethod.GET)
    public String getRequestInstitutions(Map<String, Object> model,
                                         @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering getInstitutions function");
        MedicalRecordsService service = (MedicalRecordsService) context.getBean("server1");
        while (true) {
            try{
                Citizen subject = service.getSessionCitizen(authTokenCookie);

                model.put("citizen", subject);
                List<Institution> institutions = service.getInstitutions(subject);
                model.put("institutions", institutions);
                return (institutions != null)? "institutions": "404";
            } catch (RuntimeException e) {
                service = (MedicalRecordsService) context.getBean("server2");
            }
        }
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.GET)
    public String getRequestAddInstitution(Map<String, Object> model,
                                           @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering addInstitutions function");
        MedicalRecordsService service = (MedicalRecordsService) context.getBean("server1");
        while (true) {
            try{
                Citizen subject = service.getSessionCitizen(authTokenCookie);

                model.put("citizen", subject);
                model.put("newInstitution", new Institution(true));
                boolean result = service.getAddInstitutionsPage(subject);
                return result? "addInstitution": "404";
            } catch (RuntimeException e) {
                service = (MedicalRecordsService) context.getBean("server2");
            }
        }
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.POST)
    public String postRequestAddInstitution(@ModelAttribute("newInstitution")Institution institution, Map<String, Object> model,
                                            @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering addInstitutions function");
        MedicalRecordsService service = (MedicalRecordsService) context.getBean("server1");
        while (true) {
            try{
                Citizen subject = service.getSessionCitizen(authTokenCookie);

                model.put("citizen", subject);
                Institution institutionToAdd = DataObjectCreation.createInstitution(institution, subject.getCitizenId());
                try {
                    List<Institution> institutions = service.addInstitution(subject, institutionToAdd);
                    model.put("institutions", institutions);
                    if (institutions != null) return "institutions";
                } catch (AdminException e) {
                    model.put("newInstitution", institutionToAdd);
                    model.put("error", e.getMessage());
                    return "addInstitution";
                }
                return "404";
            } catch (RuntimeException e) {
                service = (MedicalRecordsService) context.getBean("server2");
            }
        }
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.GET)
    public String getRequestEditInstitution(Map<String, Object> model, @PathVariable String institutionId,
                                            @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering editInstitutions function");
        MedicalRecordsService service = (MedicalRecordsService) context.getBean("server1");
        while (true) {
            try{
                Citizen subject = service.getSessionCitizen(authTokenCookie);

                model.put("citizen", subject);
                Institution institutionToEdit = service.getEditInstitutionPage(subject, NumberUtils.toInt(institutionId));
                model.put("institutionToEdit", institutionToEdit);
                return (institutionToEdit != null)? "editInstitution": "404";
            } catch (RuntimeException e) {
                service = (MedicalRecordsService) context.getBean("server2");
            }
        }
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.POST)
    public String postRequestEditInstitution(@ModelAttribute("newInstitution")Institution institution, Map<String, Object> model,
                                             @PathVariable String institutionId,
                                             @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
        log.info("Entering editInstitutions function");
        MedicalRecordsService service = (MedicalRecordsService) context.getBean("server1");
        while (true) {
            try{
                Citizen subject = service.getSessionCitizen(authTokenCookie);

                model.put("citizen", subject);
                List<Institution> institutions = service.editInstitution(subject, NumberUtils.toInt(institutionId), institution);
                model.put("institutions", institutions);
                return (institutions != null)? "redirect:/institutions" : "404";
            } catch (RuntimeException e) {
                service = (MedicalRecordsService) context.getBean("server2");
            }
        }
    }
}