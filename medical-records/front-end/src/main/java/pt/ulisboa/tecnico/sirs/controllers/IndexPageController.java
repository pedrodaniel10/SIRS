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
public class IndexPageController {

	@Autowired
	private ApplicationContext context;

    private static Logger log = Logger.getLogger(IndexPageController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getRequest(HttpServletResponse response,
							 Map<String, Object> model,
							 @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME, defaultValue = "") String authTokenCookie) {
        log.info("Entering getRequest function");
		MedicalRecordsService service = context.getBean(MedicalRecordsService.class);

		authTokenCookie = AuthenticationTokenUtils.checkTokenString(authTokenCookie);
		response.addCookie(new Cookie(AuthenticationTokenUtils.AUTH_COOKIE_NAME, authTokenCookie));

		boolean result = service.getWelcomePage();
		return result? "welcome": "404";
	}
}