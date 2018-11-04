package pt.ulisboa.tecnico.sirs.exception;

public class DatabaseConnectionException extends Exception {
	private static final long serialVersionUID = 1L;

	public DatabaseConnectionException() {
	}

	public DatabaseConnectionException(String message) {
		super(message);
	}
}
