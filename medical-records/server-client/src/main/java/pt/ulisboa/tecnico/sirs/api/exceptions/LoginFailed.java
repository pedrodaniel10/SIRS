package pt.ulisboa.tecnico.sirs.api.exceptions;

public class LoginFailed extends Exception{
    private static final long serialVersionUID = 1L;

    public LoginFailed() {
    }

    public LoginFailed(String message) {
        super(message);
    }
}
