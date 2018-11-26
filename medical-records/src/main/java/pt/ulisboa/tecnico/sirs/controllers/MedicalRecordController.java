package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MedicalRecordController {
    @RequestMapping(value = "/citizens/{citizenId}/medrec/{idMedRec}/view", method = RequestMethod.GET)
    public String getRequestMedicalRecord(Map<String, Object> model, @PathVariable String citizenId, @PathVariable String idMedRec) {
        return "medicalRecord";
    }

    @RequestMapping(value = "/patient/{citizenId}/medrec/{idMedRec}/edit", method = RequestMethod.GET)
    public String getRequestEditMedicalRecord(Map<String, Object> model, @PathVariable String citizenId, @PathVariable String idMedRec) {
        return "editMedicalRecord";
    }

    @RequestMapping(value = "/patient/{citizenId}/medrec/{idMedRec}/edit", method = RequestMethod.POST)
    public String postRequestEditMedicalRecord(Map<String, Object> model, @PathVariable String citizenId, @PathVariable String idMedRec) {
        return "medicalRecord";
    }
}
