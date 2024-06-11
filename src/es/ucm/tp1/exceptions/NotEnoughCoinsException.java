package es.ucm.tp1.exceptions;

public class NotEnoughCoinsException extends CommandExecuteException{
	
	public NotEnoughCoinsException(String message){ super(message); }
	
	public NotEnoughCoinsException(String message, Throwable cause) { super(message, cause); }
	

}
