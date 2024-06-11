package es.ucm.tp1.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;

public class HelpCommand extends Command {

	private static final String NAME = "help";
	private static final String DETAILS = "[h]elp";
	private static final String SHORTCUT = "h";
	private static final String HELP = "show this help";
	private static final String COMMANDS = "Available commands:";

	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		StringBuilder buffer = new StringBuilder(COMMANDS);
		
		for (int i = 0; i < AVAILABLE_COMMANDS.length; i++) {
			buffer.append(StringUtils.LINE_SEPARATOR);
			buffer.append(AVAILABLE_COMMANDS[i].getMSG());
		}

		System.out.println(buffer.toString());
		return false;
	}
}
