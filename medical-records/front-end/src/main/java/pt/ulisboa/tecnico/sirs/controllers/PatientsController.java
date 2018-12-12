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
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class PatientsController {

    private static Logger log = Logger.getLogger(PatientsController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String getRequestAppointments(Map<String, Object> model,
                                         @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering getPatients function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("citizen", subject);
        List<Citizen> citizens = service.getPatients(subject);
        model.put("citizens", citizens);
        return (citizens != null)? "patients": "404";
    }

}
