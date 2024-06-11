package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Buyable;
import es.ucm.tp1.logic.Game;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.InvalidPositionException;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;

public class GrenadeCommand extends Command implements Buyable{
	
	private static final String NAME = "grenade";
	private static final String DETAILS = "[g]renade <x> <y>";
	private static final String SHORTCUT = "g";
	private static final String HELP = "add a grenade in position x, y";
	private static final String FAILED_MSG = "Failed to add grenade";
	private static final String INCORRECT_NUMBER_OF_ARGS_G = "Incorrect number of arguments for grenade command:";
	private static final int COST = 3;
	
	private int positionX;
	private int positionY;
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		try {	
			game.checkGrenadePosition(positionX, positionY);
			this.buy(game);
			game.addGrenade(positionX, positionY);
			game.spendCoins(COST);
			game.update();
			return true;
		}
		
		catch(InvalidPositionException f) {
			System.out.println(f.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG), f);
		}
		
		catch (NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG), e);
		}
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException{
		
		if (matchCommandName(words[0])) {
			if(words.length != 3) 
				throw new CommandParseException(String.format("[ERROR]: %s %s", INCORRECT_NUMBER_OF_ARGS_G, DETAILS));	
			else {
				try {
					positionY = Integer.parseInt(words[1]);
					positionX = Integer.parseInt(words[2]);
				} catch(NumberFormatException nfe) {
					return null;
				}
				return this;
			}
		}
		else return null;
	} 

	@Override
	public int cost() {return COST;}
}
