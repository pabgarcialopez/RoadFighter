package es.ucm.tp1.control.commands;

import es.ucm.tp1.SuperCars;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.InputOutputRecordException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;

public class ResetCommand extends Command{
	
	private static final String NAME = "reset";
	private static final String DETAILS = "[r]eset [<level> <seed>]";
	private static final String SHORTCUT = "r";
	private static final String HELP = "reset game";
	private static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	private static final String USAGE_MSG = "[ERROR]: Command " + SHORTCUT + ": ";
	
	private static Long seed;
	private static Level level;
	
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if(level == null) game.reset();
			else game.reset(level, seed);
		}
		catch(InputOutputRecordException e) {
			throw new CommandExecuteException(e.getMessage(), e);
		}
		return true;
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException {

		if (matchCommandName(words[0])) {
			
			if(words.length != 1 && words.length != 3) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", words[0] , INCORRECT_NUMBER_OF_ARGS_MSG));
			}
			
			else if(words.length == 3) {
				
				level = Level.valueOfIgnoreCase(words[1]);
				if(level == null) throw new CommandParseException(usageLevel());
					
				try {
					seed = Long.parseLong(words[2]);
				}
				catch(NumberFormatException nfe) {
					throw new CommandParseException(usageSeed(), nfe);
				}
				System.out.println("Level: " + level + StringUtils.LINE_SEPARATOR + SuperCars.SEED_INFO_MSG + seed);
			}
			return this;
		}
		else return null;
	}
	
	private static String usageLevel() {
		return (USAGE_MSG + SuperCars.LEVEL_INFO_MSG);
	}
	
	private static String usageSeed() {
		return (USAGE_MSG + "the seed is not a proper long number");
	}
}
