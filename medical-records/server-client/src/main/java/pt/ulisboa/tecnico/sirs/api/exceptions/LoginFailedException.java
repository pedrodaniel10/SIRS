package pt.ulisboa.tecnico.sirs.api.exceptions;

public class LoginFailedException extends Exception{
    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
