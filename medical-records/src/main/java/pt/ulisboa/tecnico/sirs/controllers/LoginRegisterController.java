package pt.ulisboa.tecnico.sirs.controllers;

import org.apache.log4j.Logger;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginRegisterController {
	
    private static Logger log = Logger.getLogger(LoginRegisterController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getRequestLogin(Map<String, Object> model) {
    	log.info("Entering getRequestLogin function");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postRequestLogin(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRequestRegister(Map<String, Object> model) {
    	log.info("Entering getRequestRegister function");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRequestRegister(Map<String, Object> model) {
        return "register";
    }
}
