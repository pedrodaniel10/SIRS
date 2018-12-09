package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;

@Controller
public class CitizensController {

    private static Logger log = Logger.getLogger(CitizensController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/citizens", method = RequestMethod.GET)
    public String getRequestCitizens(Map<String, Object> model) {
        log.info("Entering getCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<Citizen> citizens = service.getCitizens(subject);
        model.put("citizens", citizens);
        return (citizens != null)? "citizens": "404";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.GET)
    public String getRequestAddCitizen(Map<String, Object> model) {
        log.info("Entering addCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        boolean result = service.getAddCitizensPage(subject);
        return result? "addCitizen": "404";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.POST)
    public String postRequestAddCitizen(Map<String, Object> model) {
        log.info("Entering addCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<Citizen> citizens = service.addCitizen(subject, null);
        return (citizens != null)? "citizens": "404";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.GET)
    public String getRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        log.info("Entering editCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        Citizen citizenToEdit = service.getEditCitizensPage(subject, citizenId);
        model.put("citizenToEdit", citizenToEdit);
        return (citizenToEdit != null)? "editCitizen": "404";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.POST)
    public String postRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        log.info("Entering editCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        List<Citizen> citizens = service.editCitizen(subject, null);
        return (citizens != null)? "redirect:/citizens": "404";
    }

    @RequestMapping(value = "/citizens/{citizenId}/profile", method = RequestMethod.GET)
    public String getRequestPatientProfile(Map<String, Object> model, @PathVariable String citizenId) {
        log.info("Entering profileCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        Citizen profile = service.getCitizen(subject, citizenId);
        if (subject == null)
            return "404";
        model.put("profile", profile);
        return "profile";
    }
}
