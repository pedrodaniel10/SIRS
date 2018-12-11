package pt.ulisboa.tecnico.sirs.pdp;

import org.ow2.authzforce.core.pdp.api.value.StringValue;

import java.util.ArrayList;
import java.util.List;

public final class AuthzForceUtils {

    private AuthzForceUtils(){}

    /**
     * This method transforms a list of strings in a list of string values.
     * Used in addRoleAttribute in PolicyEnforcementPoint.
     * @param list list of strings
     * @return list of string values
     */
    public static List<StringValue> parseStringList(List<String> list) {
        ArrayList<StringValue> parsedList = new ArrayList<>();

        for (String element : list) {
            parsedList.add(new StringValue(element));
        }
        return parsedList;
    }
}
