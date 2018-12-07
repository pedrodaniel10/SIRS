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

@Controller
public class CitizensController {

    private static Logger log = Logger.getLogger(CitizensController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/citizens", method = RequestMethod.GET)
    public String getRequestCitizens(Map<String, Object> model) {
        log.info("Entering getCitizens function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        List<Citizen> citizens = service.getCitizens("12345"); //12345 is the subjectId (the one that makes the request)
        model.put("citizens", citizens);
        log.info(citizens);
        return (citizens != null)? "citizens": "404";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.GET)
    public String getRequestAddCitizen(Map<String, Object> model) {
        return "addCitizen";
    }

    @RequestMapping(value = "/citizens/add", method = RequestMethod.POST)
    public String postRequestAddCitizen(Map<String, Object> model) {
        return "citizens";
    }

    @RequestMapping(value = "/citizens/{citizenId}/delete", method = RequestMethod.GET)
    public String getRequestDeleteCitizen(Map<String, Object> model, @PathVariable String citizenId) {
        return "citizens";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.GET)
    public String getRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "editCitizen";
    }

    @RequestMapping(value = "/citizens/{citizenId}/edit", method = RequestMethod.POST)
    public String postRequestEditProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "redirect:/citizens";
    }

    @RequestMapping(value = "/citizens/{citizenId}/profile", method = RequestMethod.GET)
    public String getRequestPatientProfile(Map<String, Object> model, @PathVariable String citizenId) {
        return "profile";
    }
}
