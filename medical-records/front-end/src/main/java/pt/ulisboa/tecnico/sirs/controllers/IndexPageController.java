package pt.ulisboa.tecnico.sirs.controllers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
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
	public String getRequest(Map<String, Object> model,
							 @CookieValue(value = AuthenticationTokenUtils.AUTH_COOKIE_NAME) String authTokenCookie) throws RemoteException, NotBoundException, MalformedURLException {
        log.info("Entering getRequest function");
		MedicalRecordsService service = (MedicalRecordsService) context.getBean("server1");
        while (true) {
        	try{
				Citizen subject = service.getSessionCitizen(authTokenCookie);

				Citizen result = service.getWelcomePage(subject);
				return result==null? "welcome": "redirect:/citizens/" + result.getCitizenId() + "/profile";
			} catch (RuntimeException e) {
				service = (MedicalRecordsService) context.getBean("server2");
			}
		}
	}
}