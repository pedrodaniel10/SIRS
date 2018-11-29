package pt.ulisboa.tecnico.sirs.pdp;

import org.ow2.authzforce.core.pdp.api.value.StringValue;

import java.util.ArrayList;
import java.util.List;

public final class AuthzForceUtils {

    private AuthzForceUtils(){}

    public static List<StringValue> parseStringList(List<String> list) {
        ArrayList<StringValue> parsedList = new ArrayList<>();

        for (String element : list) {
            parsedList.add(new StringValue(element));
        }
        return parsedList;
    }
}
