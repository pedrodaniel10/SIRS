package pt.ulisboa.tecnico.sirs.database.utils;

import org.bouncycastle.operator.OperatorCreationException;
import pt.ulisboa.tecnico.sirs.api.dataobjects.*;
import pt.ulisboa.tecnico.sirs.database.DatabaseConnector;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public class Populate {

    private Populate() {}

    public static void populate() throws DatabaseConnectionException, SQLException {
        Connection connection = (new DatabaseConnector()).getConnection();

        ArrayList<Citizen.Role> roleSuperUser = new ArrayList<>();
        roleSuperUser.add(Citizen.Role.SUPERUSER);
        roleSuperUser.add(Citizen.Role.PATIENT);

        Citizen superUser = new Citizen("1", "Antonio Filipe", Citizen.Gender.MALE, LocalDate.of(1975, 7, 30),
                "antonio.filipe@gmail.com", "supersecret", "/img/superuser.png", null, roleSuperUser);

        Institution i1 = new Institution(1, "Hospital Santa Maria", "Av. Prof. Egas Moniz, 1649-035 Lisboa", "/img/hospital1.png", "1");
        Institution i2 = new Institution(2, "CUF Descobertas", "R. Mario Botas, 1998-018 Lisboa", "/img/hospital2.png", "1");


        ArrayList<Citizen.Role> roleAdmin = new ArrayList<>();
        roleAdmin.add(Citizen.Role.ADMIN);
        roleAdmin.add(Citizen.Role.PATIENT);
        Citizen admin1 = new Citizen("2", "Margarida Marques", Citizen.Gender.FEMALE, LocalDate.of(1989, 8, 3),
                "margarida.marques@gmail.com", "marquinhes", "/img/admin1.png", "1", roleAdmin);
        Citizen admin2 = new Citizen("3", "Pedro Lopes", Citizen.Gender.MALE, LocalDate.of(1990, 11, 4),
                "pedro.lopes@gmail.com", "pedrinho", "/img/admin2.png", "1", roleAdmin);

        ArrayList<Citizen.Role> roleDoctor = new ArrayList<>();
        roleDoctor.add(Citizen.Role.DOCTOR);
        roleDoctor.add(Citizen.Role.PATIENT);
        Citizen doctor1 = new Citizen("4", "Joao Manuel Passos", Citizen.Gender.MALE, LocalDate.of(1955, 5, 3),
                "joao.manuel@gmail.com", "passinhos", "/img/doctor1.png", "1", roleDoctor);
        Citizen doctor2 = new Citizen("5", "Maria Eduarda", Citizen.Gender.FEMALE, LocalDate.of(1979, 12, 4),
                "maria.eduarda@gmail.com", "eduardinha", "/img/doctor2.png", "1", roleDoctor);
        Citizen doctor3 = new Citizen("6", "Miguel Matos", Citizen.Gender.MALE, LocalDate.of(1985, 6, 25),
                "miguel.matos@gmail.com", "matinhos", "/img/doctor3.png", "1", roleDoctor);

        ArrayList<Citizen.Role> rolePatient = new ArrayList<>();
        rolePatient.add(Citizen.Role.PATIENT);
        Citizen patient1 = new Citizen("7", "Joaquina Santo", Citizen.Gender.FEMALE, LocalDate.of(1936, 12, 4),
                "joaquina.santo@gmail.com", "santinho", "/img/patient1.png", "1", rolePatient);
        Citizen patient2 = new Citizen("8", "Martim Reis", Citizen.Gender.MALE, LocalDate.of(2005, 6, 14),
                "martim.reis@gmail.com", "eduardinha", "/img/patient2.png", "1", rolePatient);
        Citizen patient3 = new Citizen("9", "Salvador Sobral", Citizen.Gender.MALE, LocalDate.of(1985, 7, 21),
                "salvador.sobral@gmail.com", "sobralinho", "/img/patient3.png", "1", rolePatient);
        Citizen patient4 = new Citizen("10", "Arato Andras", Citizen.Gender.MALE, LocalDate.of(1944, 8, 22),
                "aratos.andras@gmail.com", "aratos", "/img/patient4.png", "1", rolePatient);

        DocPatRelation dpr1 = new DocPatRelation(1, Date.valueOf("2018-12-10"), Date.valueOf("2018-12-15"), doctor1.getCitizenId(), patient1.getCitizenId(), admin1.getCitizenId());
        DocPatRelation dpr2 = new DocPatRelation(2, Date.valueOf("2018-12-5"), Date.valueOf("2018-12-6"), doctor1.getCitizenId(), patient2.getCitizenId(), admin1.getCitizenId());
        DocPatRelation dpr3 = new DocPatRelation(3, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor2.getCitizenId(), patient3.getCitizenId(), admin2.getCitizenId());
        DocPatRelation dpr4 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor2.getCitizenId(), patient4.getCitizenId(), admin2.getCitizenId());

        DatabaseUtils.addCitizen(connection, superUser);
        DatabaseUtils.addInstitution(connection, i1);
        DatabaseUtils.addInstitution(connection, i2);
        DatabaseUtils.addCitizen(connection, admin1);
        DatabaseUtils.setAdminInstitutionId(connection, admin1.getCitizenId(), 1);
        DatabaseUtils.addCitizen(connection, admin2);
        DatabaseUtils.setAdminInstitutionId(connection, admin2.getCitizenId(), 2);
        DatabaseUtils.addCitizen(connection, doctor1);
        DatabaseUtils.setDoctorInstitutionId(connection, doctor1.getCitizenId(), 1, admin2.getCitizenId());
        DatabaseUtils.addCitizen(connection, doctor2);
        DatabaseUtils.setDoctorInstitutionId(connection, doctor2.getCitizenId(), 2, admin1.getCitizenId());
        DatabaseUtils.addCitizen(connection, doctor3);
        DatabaseUtils.addCitizen(connection, patient1);
        DatabaseUtils.addDocPatRelation(connection, dpr1);
        DatabaseUtils.addCitizen(connection, patient2);
        DatabaseUtils.addDocPatRelation(connection, dpr2);
        DatabaseUtils.addCitizen(connection, patient3);
        DatabaseUtils.addDocPatRelation(connection, dpr3);
        DatabaseUtils.addCitizen(connection, patient4);
        DatabaseUtils.addDocPatRelation(connection, dpr4);

        //Add Medical Records
        ReportInfo report1 = new ReportInfo(50,40,10,10,"Blood Test","The results of the first" +
                "tests were inconclusive. Requested more blood tests.");
        ReportInfo report2 = new ReportInfo(50,40,10,10,"X-Ray","Back Xray. Patient complained" +
                "about back pain. Found some nodules. Requested X-Ray for more detailed information.");
        ReportInfo report3 = new ReportInfo(50,40,10,10,"Surgery","Carotid endarterectomy. " +
                "Removal of blockage from carotid arteries. Went well");
        ReportInfo report4 = new ReportInfo(50,40,10,10,"Appointment","Check-up.");
        MedicalRecord record1 = new MedicalRecord(0, doctor1.getCitizenId(), patient4.getCitizenId(), 1, report1);
        MedicalRecord record2 = new MedicalRecord(0, doctor1.getCitizenId(), patient4.getCitizenId(), 1, report2);
        MedicalRecord record3 = new MedicalRecord(0, doctor1.getCitizenId(), patient4.getCitizenId(), 1, report3);
        MedicalRecord record4 = new MedicalRecord(0, doctor2.getCitizenId(), patient4.getCitizenId(), 1, report4);
        MedicalRecord record11 = new MedicalRecord(0, doctor1.getCitizenId(), superUser.getCitizenId(), 1, report1);
        MedicalRecord record12 = new MedicalRecord(0, doctor1.getCitizenId(), superUser.getCitizenId(), 1, report2);
        MedicalRecord record13 = new MedicalRecord(0, doctor1.getCitizenId(), superUser.getCitizenId(), 1, report3);
        MedicalRecord record14 = new MedicalRecord(0, doctor2.getCitizenId(), superUser.getCitizenId(), 1, report4);
        MedicalRecord record21 = new MedicalRecord(0, doctor1.getCitizenId(), admin2.getCitizenId(), 1, report1);
        MedicalRecord record22 = new MedicalRecord(0, doctor1.getCitizenId(), admin2.getCitizenId(), 1, report2);
        MedicalRecord record23 = new MedicalRecord(0, doctor1.getCitizenId(), admin2.getCitizenId(), 1, report3);
        MedicalRecord record24 = new MedicalRecord(0, doctor2.getCitizenId(), admin2.getCitizenId(), 1, report4);

        try {
            DatabaseUtils.addMedicalRecord(connection, record1);
            DatabaseUtils.addMedicalRecord(connection, record2);
            DatabaseUtils.addMedicalRecord(connection, record3);
            DatabaseUtils.addMedicalRecord(connection, record4);
            DatabaseUtils.addMedicalRecord(connection, record11);
            DatabaseUtils.addMedicalRecord(connection, record12);
            DatabaseUtils.addMedicalRecord(connection, record13);
            DatabaseUtils.addMedicalRecord(connection, record14);
            DatabaseUtils.addMedicalRecord(connection, record21);
            DatabaseUtils.addMedicalRecord(connection, record22);
            DatabaseUtils.addMedicalRecord(connection, record23);
            DatabaseUtils.addMedicalRecord(connection, record24);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
