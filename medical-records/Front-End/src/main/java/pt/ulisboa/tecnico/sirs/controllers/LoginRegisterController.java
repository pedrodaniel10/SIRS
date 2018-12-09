package pt.ulisboa.tecnico.sirs.controllers;

import org.apache.log4j.Logger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;

@Controller
public class LoginRegisterController {
	
    private static Logger log = Logger.getLogger(LoginRegisterController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getRequestLogin(Map<String, Object> model) {
    	log.info("Entering getRequestLogin function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        boolean result = service.getLoginRegisterPage();
        return result? "login": "404";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postRequestLogin(Map<String, Object> model) {
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        boolean result = service.postLoginRegisterPage();
        return result? "login": "404";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRequestRegister(Map<String, Object> model) {
    	log.info("Entering getRequestRegister function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        boolean result = service.getLoginRegisterPage();
        return result? "register": "404";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRequestRegister(Map<String, Object> model) {
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
        boolean result = service.postLoginRegisterPage();
        return result? "register": "404";
    }
}
