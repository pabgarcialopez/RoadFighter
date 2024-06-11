package es.ucm.tp1.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class DumpCommand extends Command{
	
	private static final String NAME = "dump";
	private static final String DETAILS = "[d]ump <filename>";
	private static final String SHORTCUT = "d";
	private static final String HELP = "Shows the content of a saved file";
	
	private String fileName;
	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException {
		
		if (matchCommandName(words[0])) {
			if (words.length != 2) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME , INCORRECT_NUMBER_OF_ARGS_MSG));
			}
			else {
				fileName = words[1];
				return this;
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		fileName += ".txt";
		
		try (BufferedReader inChars = new BufferedReader(new FileReader(fileName))){
			String l = inChars.readLine();
			while(l != null) {
				System.out.println(l);
				l = inChars.readLine();
			}
		} 
		
		catch(IOException e) {
			throw new CommandExecuteException("An error ocurred on reading a file", e);		
		}
		return false;
	}
}
