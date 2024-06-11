package es.ucm.tp1.control;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;;

public interface Buyable {
	
	public static final String NOT_ENOUGH_COINS_MSG = "Not enough coins";

	public int cost();
	public default void buy(Game game) throws NotEnoughCoinsException {
		if(game.playerCoins() < cost()) 
			throw new NotEnoughCoinsException(NOT_ENOUGH_COINS_MSG);
	}
}
