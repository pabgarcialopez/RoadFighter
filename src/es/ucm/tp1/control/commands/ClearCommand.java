package es.ucm.tp1.control.commands;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class ClearCommand extends Command{
	
	private static final String NAME = "0";
	private static final String DETAILS = "Clear";
	private static final String SHORTCUT = "[0]";
	private static final String HELP = "Clears the road";
	
	public ClearCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		game.deleteAllObjects();
		return true;
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if("0".equals(words[0])) {
			if(words.length != 1) 
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			else return this;
		}
		else return null;		
	}
	
	@Override
	String getMSG() {
		return DETAILS + " " + SHORTCUT + ": " + HELP;
	}
}
