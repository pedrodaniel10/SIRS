package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;

@Controller
public class InstitutionsController {

    private static Logger log = Logger.getLogger(InstitutionsController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/institutions", method = RequestMethod.GET)
    public String getRequestInstitutions(Map<String, Object> model) {
        log.info("Entering getInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<Institution> institutions = service.getInstitutions(subject);
        model.put("institutions", institutions);
        return (institutions != null)? "institutions": "404";
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.GET)
    public String getRequestAddInstitution(Map<String, Object> model) {
        log.info("Entering addInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        boolean result = service.getAddInstitutionsPage(subject);
        return result? "addInstitution": "404";
    }

    @RequestMapping(value = "/institutions/add", method = RequestMethod.POST)
    public String postRequestAddInstitution(Map<String, Object> model) {
        log.info("Entering addInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        /* change to a service that does a post? */
        boolean authorization = service.getAddInstitutionsPage(subject);
        if (authorization) {
            List<Institution> institutions = service.getInstitutions(subject);
            model.put("institutions", institutions);
            return "institutions";
        }
        else return "404";
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.GET)
    public String getRequestEditInstitution(Map<String, Object> model, @PathVariable String institutionId) {
        log.info("Entering editInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        Institution institutionToEdit = service.editInstitution(subject, institutionId);
        model.put("institutionToEdit", institutionToEdit);
        return (institutionToEdit != null)? "editInstitution": "404";
    }

    @RequestMapping(value = "/institutions/{institutionId}/edit", method = RequestMethod.POST)
    public String postRequestEditInstitution(Map<String, Object> model, @PathVariable String institutionId) {
        log.info("Entering editInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        Institution institutionToEdit = service.editInstitution(subject, institutionId);
        model.put("institutionToEdit", institutionToEdit);
        if (institutionToEdit != null) {
            List<Institution> institutions = service.getInstitutions(subject);
            model.put("institutions", institutions);
            return "institutions";
        }
        else return "404";
    }

    @RequestMapping(value = "/institutions/{institutionId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteInstitution(Map<String, Object> model, @PathVariable String institutionId) {
        log.info("Entering editInstitutions function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<Institution> institutions = service.deleteInstitution(subject, institutionId);
        model.put("institutions", institutions);
        return (institutions != null) ? "institutions" : "404";
    }
}
