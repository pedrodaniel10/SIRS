package pt.ulisboa.tecnico.sirs.api.exceptions;

public class AdminException extends Exception {
    private static final long serialVersionUID = 1L;

    public AdminException() {
    }

    public AdminException(String message) {
        super(message);
    }
}