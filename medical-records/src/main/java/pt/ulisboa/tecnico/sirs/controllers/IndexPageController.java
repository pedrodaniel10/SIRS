package pt.ulisboa.tecnico.sirs.controllers;

import org.apache.log4j.Logger;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexPageController {
	
    private static Logger log = Logger.getLogger(IndexPageController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getRequest(Map<String, Object> model) {
        log.info("Entering getRequest function");
		return "welcome";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getRequestProfile(Map<String, Object> model) {
		return "profile";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String getRequestEditProfile(Map<String, Object> model) {
		return "editProfile";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String postRequestEditProfile(Map<String, Object> model) {
		return "profile";
	}
}