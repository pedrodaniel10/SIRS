package pt.ulisboa.tecnico.sirs.api.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtils {

    private ServiceUtils(){}

    /**
     * This method transforms a list of Roles in a list of strings.
     * Used in MedicalRecordsServiceImpl.
     * @param list list of roles
     * @return list of strings
     */
    public static List<String> parseRoleList(List<Citizen.Role> list) {
        ArrayList<String> parsedList = new ArrayList<>();

        for (Citizen.Role element : list) {
            parsedList.add(element.name());
        }
        return parsedList;
    }
}
