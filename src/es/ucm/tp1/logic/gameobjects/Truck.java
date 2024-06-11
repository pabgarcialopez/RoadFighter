package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Truck extends Obstacle {

	private static final String TRUCK_SYMBOL = "←";
	public static final String INFO = "[TRUCK] moves towards the player";
	private static final int INITIAL_LIVES = 1;
	
	public Truck(Game game, int x, int y) {
		super(game, x, y, INITIAL_LIVES);
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.kill();
		return true;
	}

	@Override
	public boolean isAlive() {
		return (super.lives > 0);
	}

	@Override
	public void update() {
		this.y--;
	}
	
	@Override
	public String getSymbol() {
		return TRUCK_SYMBOL;
	}
}
