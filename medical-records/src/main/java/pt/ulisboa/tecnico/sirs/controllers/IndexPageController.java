package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexPageController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getRequest(Map<String, Object> model) {
		return "welcome";
	}

}