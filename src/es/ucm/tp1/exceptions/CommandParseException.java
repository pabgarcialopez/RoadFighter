package es.ucm.tp1.exceptions;

public class CommandParseException extends GameException{

	public CommandParseException(String message){ super(message); }
	
	public CommandParseException(String message, Throwable cause) { super(message, cause); }
}
