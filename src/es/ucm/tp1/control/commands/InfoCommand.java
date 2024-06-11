package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;

public class InfoCommand extends Command {

	private static final String NAME = "info";
	private static final String DETAILS = "[i]nfo";
	private static final String SHORTCUT = "i";
	private static final String HELP = "prints gameobject info";
	private static final String AVAILABLE_OBJ ="Available objects:";
	
	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		System.out.println(AVAILABLE_OBJ);
		System.out.println(GamePrinter.description(game.getLevel()));
		
		return false;
	}

}