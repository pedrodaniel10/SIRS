package pt.ulisboa.tecnico.sirs.api.exceptions;

public class CitizenException extends Exception {
	private static final long serialVersionUID = 1L;

	public CitizenException() {
	}

	public CitizenException(String message) {
		super(message);
	}
}
