package es.ucm.tp1.exceptions;

public class InvalidPositionException extends CommandExecuteException {
	
	public InvalidPositionException(String message){ super(message); }
	
	public InvalidPositionException(String message, Throwable cause) { super(message, cause); }
	
}
