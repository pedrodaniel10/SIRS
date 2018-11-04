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

}