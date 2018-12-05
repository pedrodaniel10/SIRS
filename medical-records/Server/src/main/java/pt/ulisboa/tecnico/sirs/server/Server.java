package pt.ulisboa.tecnico.sirs.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;

@SpringBootApplication
public class Server {

    @Bean
    MedicalRecordsService medicalRecordsService() {
        return new MedicalRecordsServiceImpl();
    }

    @Bean
    RmiServiceExporter exporter(MedicalRecordsService implementation) {

        /* Expose a service via RMI. Remote obect URL is:
        * rmi://localhost:1099/MedicalRecordsService */
        Class<MedicalRecordsService> serviceInterface = MedicalRecordsService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}
