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

        Citizen superUser2 = new Citizen("11", "Magda Pedrosa", Citizen.Gender.FEMALE, LocalDate.of(1981, 8, 2),
                "magda.pedrosa@gmail.com", "supersecret", "/img/superuser2.png", null, roleSuperUser);

        Institution i1 = new Institution(1, "Hospital Santa Maria", "Av. Prof. Egas Moniz, 1649-035 Lisboa", "/img/hospital1.png", "1");
        Institution i2 = new Institution(2, "CUF Descobertas", "R. Mario Botas, 1998-018 Lisboa", "/img/hospital2.png", "1");
        Institution i3 = new Institution(3, "Hospital da Luz", "Av. Lusíada 100, 1500-650 Lisboa", "/img/hospital3.png", "1");
        Institution i4 = new Institution(4, "Centro de Saúde Alameda", "R. Carvalho Araújo 103, 1900-181 Lisboa", "/img/hospital4.png", "11");

        ArrayList<Citizen.Role> roleAdmin = new ArrayList<>();
        roleAdmin.add(Citizen.Role.ADMIN);
        roleAdmin.add(Citizen.Role.PATIENT);
        Citizen admin1 = new Citizen("2", "Margarida Marques", Citizen.Gender.FEMALE, LocalDate.of(1989, 8, 3),
                "margarida.marques@gmail.com", "marquinhes", "/img/admin1.png", "1", roleAdmin);
        Citizen admin2 = new Citizen("3", "Pedro Lopes", Citizen.Gender.MALE, LocalDate.of(1990, 11, 4),
                "pedro.lopes@gmail.com", "pedrinho", "/img/admin2.png", "1", roleAdmin);
        Citizen admin3 = new Citizen("12", "Joana Marques", Citizen.Gender.FEMALE, LocalDate.of(1989, 8, 3),
                "joana.marques@gmail.com", "joaninha", "/img/admin3.png", "11", roleAdmin);
        Citizen admin4 = new Citizen("13", "Marco Lopes", Citizen.Gender.MALE, LocalDate.of(1990, 11, 4),
                "marco.lopes@gmail.com", "marquinho", "/img/admin4.png", "11", roleAdmin);

        ArrayList<Citizen.Role> roleDoctor = new ArrayList<>();
        roleDoctor.add(Citizen.Role.DOCTOR);
        roleDoctor.add(Citizen.Role.PATIENT);
        Citizen doctor1 = new Citizen("4", "Joao Manuel Passos", Citizen.Gender.MALE, LocalDate.of(1955, 5, 3),
                "joao.manuel@gmail.com", "passinhos", "/img/doctor1.png", "1", roleDoctor);
        Citizen doctor2 = new Citizen("5", "Maria Eduarda", Citizen.Gender.FEMALE, LocalDate.of(1979, 12, 4),
                "maria.eduarda@gmail.com", "eduardinha", "/img/doctor2.png", "1", roleDoctor);
        Citizen doctor3 = new Citizen("6", "Miguel Matos", Citizen.Gender.MALE, LocalDate.of(1985, 6, 25),
                "miguel.matos@gmail.com", "matinhos", "/img/doctor3.png", "1", roleDoctor);
        Citizen doctor4 = new Citizen("14", "Manuel Maria", Citizen.Gender.MALE, LocalDate.of(1955, 5, 3),
                "manuel.maria@gmail.com", "manuelinho", "/img/doctor4.png", "1", roleDoctor);
        Citizen doctor5 = new Citizen("15", "Marta Ramos", Citizen.Gender.FEMALE, LocalDate.of(1979, 12, 4),
                "marta.ramos@gmail.com", "martinha", "/img/doctor5.png", "11", roleDoctor);
        Citizen doctor6 = new Citizen("16", "Joao Martins", Citizen.Gender.MALE, LocalDate.of(1985, 6, 25),
                "joao.martins@gmail.com", "martinhos", "/img/doctor6.png", "11", roleDoctor);

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
        /*DocPatRelation dpr5 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor2.getCitizenId(), doctor1.getCitizenId(), admin2.getCitizenId());
        DocPatRelation dpr6 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor2.getCitizenId(), superUser.getCitizenId(), admin2.getCitizenId());
        DocPatRelation dpr7 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor4.getCitizenId(), patient4.getCitizenId(), admin3.getCitizenId());
        DocPatRelation dpr8 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor4.getCitizenId(), patient4.getCitizenId(), admin3.getCitizenId());
        DocPatRelation dpr9 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor4.getCitizenId(), patient4.getCitizenId(), admin3.getCitizenId());
        DocPatRelation dpr10 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor5.getCitizenId(), patient4.getCitizenId(), admin4.getCitizenId());
        DocPatRelation dpr11 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor5.getCitizenId(), patient4.getCitizenId(), admin4.getCitizenId());
        DocPatRelation dpr12 = new DocPatRelation(4, Date.valueOf("2018-12-12"), Date.valueOf("2018-12-14"), doctor5.getCitizenId(), patient4.getCitizenId(), admin4.getCitizenId());
*/

        DatabaseUtils.addCitizen(connection, superUser);
        DatabaseUtils.addCitizen(connection, superUser2);

        DatabaseUtils.addInstitution(connection, i1);
        DatabaseUtils.addInstitution(connection, i2);
        DatabaseUtils.addInstitution(connection, i3);
        DatabaseUtils.addInstitution(connection, i4);

        DatabaseUtils.addCitizen(connection, admin1);
        DatabaseUtils.setAdminInstitutionId(connection, admin1.getCitizenId(), 1);
        DatabaseUtils.addCitizen(connection, admin2);
        DatabaseUtils.setAdminInstitutionId(connection, admin2.getCitizenId(), 2);
        DatabaseUtils.addCitizen(connection, admin3);
        DatabaseUtils.setAdminInstitutionId(connection, admin3.getCitizenId(), 3);
        DatabaseUtils.addCitizen(connection, admin4);
        DatabaseUtils.setAdminInstitutionId(connection, admin4.getCitizenId(), 4);

        DatabaseUtils.addCitizen(connection, doctor1);
        DatabaseUtils.setDoctorInstitutionId(connection, doctor1.getCitizenId(), 1, admin2.getCitizenId());
        DatabaseUtils.addCitizen(connection, doctor2);
        DatabaseUtils.setDoctorInstitutionId(connection, doctor2.getCitizenId(), 2, admin1.getCitizenId());
        DatabaseUtils.addCitizen(connection, doctor3);
        DatabaseUtils.addCitizen(connection, doctor4);
        DatabaseUtils.setDoctorInstitutionId(connection, doctor4.getCitizenId(), 3, admin3.getCitizenId());
        DatabaseUtils.addCitizen(connection, doctor5);
        DatabaseUtils.setDoctorInstitutionId(connection, doctor5.getCitizenId(), 3, admin3.getCitizenId());
        DatabaseUtils.addCitizen(connection, doctor6);
        DatabaseUtils.addCitizen(connection, patient1);
        DatabaseUtils.addDocPatRelation(connection, dpr1);
        DatabaseUtils.addCitizen(connection, patient2);
        DatabaseUtils.addDocPatRelation(connection, dpr2);
        DatabaseUtils.addCitizen(connection, patient3);
        DatabaseUtils.addDocPatRelation(connection, dpr3);
        DatabaseUtils.addCitizen(connection, patient4);
        DatabaseUtils.addDocPatRelation(connection, dpr4);

        /*DatabaseUtils.addDocPatRelation(connection, dpr5);
        DatabaseUtils.addDocPatRelation(connection, dpr6);
        DatabaseUtils.addDocPatRelation(connection, dpr7);
        DatabaseUtils.addDocPatRelation(connection, dpr8);
        DatabaseUtils.addDocPatRelation(connection, dpr9);
        DatabaseUtils.addDocPatRelation(connection, dpr10);
        DatabaseUtils.addDocPatRelation(connection, dpr11);
        DatabaseUtils.addDocPatRelation(connection, dpr12);*/

        //Add Medical Records
        ReportInfo report1 = new ReportInfo(50,40,10,10,"Blood Test","The results of the first" +
                "tests were inconclusive. Requested more blood tests.");
        MedicalRecord record1 = new MedicalRecord(0, doctor1.getCitizenId(), patient1.getCitizenId(), 1, report1);
        MedicalRecord record2 = new MedicalRecord(0, doctor1.getCitizenId(), patient2.getCitizenId(), 1, report1);
        MedicalRecord record3 = new MedicalRecord(0, doctor1.getCitizenId(), patient1.getCitizenId(), 1, report1);

        try {
            DatabaseUtils.addMedicalRecord(connection, record1);
            DatabaseUtils.addMedicalRecord(connection, record2);
            DatabaseUtils.addMedicalRecord(connection, record3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}