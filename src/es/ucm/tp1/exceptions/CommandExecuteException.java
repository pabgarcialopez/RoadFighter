package es.ucm.tp1.exceptions;

public class CommandExecuteException extends GameException {
	
	public CommandExecuteException(String message){ super(message); }
	
	public CommandExecuteException(String message, Throwable cause) { super(message, cause); }
}
