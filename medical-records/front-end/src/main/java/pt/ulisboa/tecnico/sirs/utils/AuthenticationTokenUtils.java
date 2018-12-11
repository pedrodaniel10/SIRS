package pt.ulisboa.tecnico.sirs.utils;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public class AuthenticationTokenUtils {
    private AuthenticationTokenUtils(){}

    public static final String AUTH_COOKIE_NAME = "authToken";

    public static String checkTokenString(String authToken){
        if (StringUtils.isBlank(authToken)) {
            authToken = UUID.randomUUID().toString();
        }
        return authToken;
    }
}
