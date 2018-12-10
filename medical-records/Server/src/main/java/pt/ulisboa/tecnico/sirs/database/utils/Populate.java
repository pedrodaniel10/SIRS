package pt.ulisboa.tecnico.sirs.database.utils;

import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;
import pt.ulisboa.tecnico.sirs.database.DatabaseConnector;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
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
        Institution i2 = new Institution(1, "CUF Descobertas", "R. Mário Botas, 1998-018 Lisboa", "/img/hospital2.png", "1");


        ArrayList<Citizen.Role> roleAdmin = new ArrayList<>();
        roleAdmin.add(Citizen.Role.ADMIN);
        roleAdmin.add(Citizen.Role.PATIENT);
        Citizen admin1 = new Citizen("2", "Margarida Marques", Citizen.Gender.MALE, LocalDate.of(1989, 8, 3),
                "margarida.marques@gmail.com", "marquinhes", "/img/admin1.png", "1", roleAdmin);
        Citizen admin2 = new Citizen("3", "Pedro Lopes", Citizen.Gender.FEMALE, LocalDate.of(1990, 11, 4),
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

        DatabaseUtils.addCitizen(connection, superUser);
        DatabaseUtils.addInstitution(connection, i1);
        DatabaseUtils.addInstitution(connection, i2);
        DatabaseUtils.addCitizen(connection, admin1);
        DatabaseUtils.addCitizen(connection, admin2);
        DatabaseUtils.addCitizen(connection, doctor1);
        DatabaseUtils.addCitizen(connection, doctor2);
        DatabaseUtils.addCitizen(connection, doctor3);
    }
}
