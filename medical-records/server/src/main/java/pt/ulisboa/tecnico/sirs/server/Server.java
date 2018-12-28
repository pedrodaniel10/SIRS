package pt.ulisboa.tecnico.sirs.server;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.database.DatabaseConnector;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.Populate;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class Server {

    static int rmiPort;

    private static Logger log = Logger.getLogger(Server.class);


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
        exporter.setRegistryPort(rmiPort);
        return exporter;
    }

    public static void main(String[] args) {
        rmiPort = Integer.parseInt(args[0]);
        try {
            (new DatabaseConnector()).setupTables();
            Populate.populate();
        } catch (IOException | SQLException | DatabaseConnectionException e) {
            log.error("Unable to connect to database." + e);
            System.exit(-1);
        }
        SpringApplication.run(Server.class, args);
    }

}
