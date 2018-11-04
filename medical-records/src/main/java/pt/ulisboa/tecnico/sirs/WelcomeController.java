package pt.ulisboa.tecnico.sirs;

import org.apache.log4j.Logger;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	//define the logger
    private static Logger log = Logger.getLogger(WelcomeController.class);
    
    @RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "welcome";
	}

}