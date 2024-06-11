package es.ucm.tp1.exceptions;


public class InputOutputRecordException extends CommandExecuteException{
	
	public InputOutputRecordException(String message){ super(message); }
	
	public InputOutputRecordException(String message, Throwable cause) { super(message, cause); }

}
