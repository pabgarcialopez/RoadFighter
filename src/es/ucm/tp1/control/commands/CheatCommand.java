package es.ucm.tp1.control.commands;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class CheatCommand extends Command{
	
	private static final String NAME = "cheat";
	private static final String DETAILS = "Cheat";
	private static final String SHORTCUT = "[1..5]";
	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";
	private static final int NUM_MAX_OF_OBJECTS = 6;
	
	private int numberOfObject;
	
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game){
		// Borra ultima columna y aniade el objeto avanzado correspondiente.
		game.addForceAdvancedObject(numberOfObject);
		return true;
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException {
		try {
			numberOfObject = Integer.parseInt(words[0]);
		} catch (NumberFormatException nfe) {
			return null;
		}
		
		if(numberOfObject > 0 && numberOfObject < NUM_MAX_OF_OBJECTS) {
			if(words.length != 1) 
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME , INCORRECT_NUMBER_OF_ARGS_MSG));	
			else return this;
		}
		else return null;
	}
	@Override
	String getMSG() {
		return DETAILS + " " + SHORTCUT + ": " + HELP;
	}
}