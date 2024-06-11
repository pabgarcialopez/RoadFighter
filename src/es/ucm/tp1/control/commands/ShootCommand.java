package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Buyable;
import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.actions.ShootAction;

public class ShootCommand extends Command implements Buyable{

	private static final String NAME = "shoot";
	private static final String DETAILS = "[s]hoot";
	private static final String SHORTCUT = "s";
	private static final String HELP = "shoot bullet";
	private static final String FAILED_MSG = "Failed to shoot";
	private static final int COST = 1;
	
	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		try {
			this.buy(game);
			game.spendCoins(COST);
			game.execute(new ShootAction());
			return true;
			
		} catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG), e);
		}	
	}

	@Override
	public int cost() {return COST;}
	
}
