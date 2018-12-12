package pt.ulisboa.tecnico.sirs;

import org.apache.log4j.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {
    @Value("${server.http.port}")
    private int httpPort;
    @Value("${server.port}")
    private int httpsPort;
    
    private static Logger log = Logger.getLogger(SpringBootWebApplication.class);
    
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    @Bean
    RmiProxyFactoryBean server1() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(MedicalRecordsService.class);
        try {
            Naming.lookup("rmi://localhost:1099/MedicalRecordsService");
            bean.setServiceUrl("rmi://localhost:1099/MedicalRecordsService");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            log.error("Unable to connect to both servers");
            System.exit(-1);
        }
        return bean;
    }

    @Bean
    RmiProxyFactoryBean server2() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(MedicalRecordsService.class);
        try {
            Naming.lookup("rmi://localhost:1098/MedicalRecordsService");
            bean.setServiceUrl("rmi://localhost:1098/MedicalRecordsService");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            log.error("Unable to connect to both servers");
            System.exit(-1);
        }
        return bean;
    }

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(httpsPort);

        return connector;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

	public static void main(String[] args) throws Exception {
        log.info("Logger enabled: Entering main \n\n");
		SpringApplication.run(SpringBootWebApplication.class, args);
        log.info("Exiting main");
	}

}