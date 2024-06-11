package es.ucm.tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;
import es.ucm.tp1.view.GameSerializer;

public class SaveCommand extends Command{

	private static final String NAME = "save";
	private static final String DETAILS = "sa[v]e <filename>";
	private static final String SHORTCUT = "v";
	private static final String HELP = "Save the state of the game to a file.";
	
	private String fileName;
	
	public SaveCommand() {
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
		
		GameSerializer serializer = new GameSerializer(game);
		
		try (BufferedWriter outChars = new BufferedWriter(new FileWriter(fileName))){
			outChars.write("Super cars 3.0\n");
			outChars.write(StringUtils.LINE_SEPARATOR);
			outChars.write(serializer.toString());
			System.out.println("Game successfully saved to file " + fileName);
		} 
		
		catch(IOException e) {
			
			throw new CommandExecuteException("An error ocurred while saving the state of the game.");		
		}
		
		return false;
	}
	
}
