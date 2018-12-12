package pt.ulisboa.tecnico.sirs.controllers;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.*;
import pt.ulisboa.tecnico.sirs.api.exceptions.AdminException;
import pt.ulisboa.tecnico.sirs.interfaces.DataObjectCreation;

@Controller
public class MedicalRecordController {

    private static Logger log = Logger.getLogger(MedicalRecordController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/citizens/{citizenId}/medrec/{idMedRec}/view", method = RequestMethod.GET)
    public String getRequestMedicalRecord(Map<String, Object> model, @PathVariable String citizenId, @PathVariable String idMedRec) {
        log.info("Entering getMedicalRecord function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        SignedMedicalRecord record = service.getMedicalRecord(subject, citizenId, NumberUtils.toInt(idMedRec));
        if (record == null)
            return "404";
        model.put("record", record);
        return "medicalRecord";
    }

    @RequestMapping(value = "/citizens/{citizenId}/medrec/create", method = RequestMethod.GET)
    public String getRequestCreateMedicalRecord(Map<String, Object> model, @PathVariable String citizenId) {
        log.info("Entering createMedicalRecord function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        boolean result = service.getAddMedicalRecordPage(subject, citizenId);
        if (result) {
            Citizen patient = service.getMedicalRecordCitizen(subject, citizenId);
            Institution institution = service.getMedicalRecordInstitution(subject, subject.getCitizenId());
            MedicalRecord record = new MedicalRecord(true);
            record.setDoctor(subject);
            record.setPatient(patient);
            record.setInstitution(institution);
            model.put("newRecord", record);
            model.put("newReportInfo", record.getReportInfo());
        }
        return result ? "createMedicalRecord" : "404";
    }

    @RequestMapping(value = "/citizens/{citizenId}/medrec/create", method = RequestMethod.POST)
    public String postRequestCreateMedicalRecord(@ModelAttribute("newReportInfo")ReportInfo info, Map<String, Object> model, @PathVariable String citizenId) {
        log.info("Entering createMedicalRecord function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        Citizen subject = service.getSessionCitizen();
        model.put("citizen", subject);
        Citizen patient = service.getMedicalRecordCitizen(subject, citizenId);
        Institution institution = service.getMedicalRecordInstitution(subject, subject.getCitizenId());
        MedicalRecord record = new MedicalRecord(true);
        record.setDoctor(subject);
        record.setPatient(patient);
        record.setInstitution(institution);
        record.setReportInfo(info);
        service.addMedicalRecord(subject, record);
        model.put("record", record);
        return "medicalRecord";
    }

}
