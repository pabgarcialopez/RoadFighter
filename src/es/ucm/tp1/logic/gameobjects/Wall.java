package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Wall extends Obstacle {
	
	public static final String INFO = "[WALL] hard obstacle";
	
	private static final String[] SYMBOLS  = {"", "░", "▒", "█"};
	
	private static final int INITIAL_LIVES = 3;
	private static final int PRIZE = 5;
	
	public Wall(Game game, int positionX, int positionY) {
		super(game, positionX, positionY, INITIAL_LIVES);
	}
	public void onDelete() {
		game.addCoins(PRIZE);
		super.onDelete();
	}

	@Override
	public String getSymbol() {
		return SYMBOLS[lives];
	}
	
	@Override
	public String getSerializedInfo() {
		StringBuilder s = new StringBuilder();
		
		s.append(super.getSerializedInfo()).append(" " + lives);
		
		return s.toString();
	}
}
