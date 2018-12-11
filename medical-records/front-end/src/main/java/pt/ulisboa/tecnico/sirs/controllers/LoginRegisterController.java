package pt.ulisboa.tecnico.sirs.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.utils.AuthenticationTokenUtils;

@Controller
public class LoginRegisterController {
	
    private static Logger log = Logger.getLogger(LoginRegisterController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getRequestLogin(HttpServletResponse response,
                                  Map<String, Object> model,
                                  @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
    	log.info("Entering getRequestLogin function");
        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        Citizen result = service.getLoginPage(subject);
        return result==null? "login": "404";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postRequestLogin(HttpServletResponse response,
                                   Map<String, Object> model,
                                   @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {

        MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

        authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
        response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

        Citizen subject = service.getSessionCitizen(authTokenCookie);

        Citizen result = service.postLoginPage(subject);
        return result==null? "login": "404";
    }

}