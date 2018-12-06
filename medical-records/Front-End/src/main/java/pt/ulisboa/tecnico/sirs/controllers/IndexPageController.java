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
public class IndexPageController {

	@Autowired
	private ApplicationContext context;

    private static Logger log = Logger.getLogger(IndexPageController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getRequest(Map<String, Object> model) {
        log.info("Entering getRequest function");
		MedicalRecordsService service = context.getBean(MedicalRecordsService.class);
		return "welcome";
	}

}