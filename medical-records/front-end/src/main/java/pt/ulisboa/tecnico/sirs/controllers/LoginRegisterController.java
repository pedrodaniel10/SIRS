package pt.ulisboa.tecnico.sirs.controllers;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Login;
import pt.ulisboa.tecnico.sirs.api.exceptions.LoginFailedException;
import pt.ulisboa.tecnico.sirs.interfaces.DataObjectCreation;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class LoginRegisterController {
	
    private static Logger log = Logger.getLogger(LoginRegisterController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getRequestLogin(Map<String, Object> model,
                                  @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {
    	log.info("Entering getRequestLogin function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        model.put("login", new Login());
        Citizen result = service.getLoginPage(subject);
        return result==null? "login": "redirect:/citizens/" + result.getCitizenId() + "/profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postRequestLogin(Map<String, Object> model,
                                   @ModelAttribute("login") Login login,
                                   @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {

        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        Citizen subject = service.getSessionCitizen(authTokenCookie);
        Citizen result;
        try {
        	result = service.postLoginPage(authTokenCookie, DataObjectCreation.createLogin(login));
        } catch (LoginFailedException e) {
            model.put("error", e.getMessage());
        	return "login";
        }
        return result==null? "login": "redirect:/citizens/" + result.getCitizenId() + "/profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String postRequestLogout(Map<String, Object> model,
                                   @ModelAttribute("login") Login login,
                                   @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) {

        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        service.postLogoutPage(authTokenCookie);
        return "redirect:/";
    }

}
