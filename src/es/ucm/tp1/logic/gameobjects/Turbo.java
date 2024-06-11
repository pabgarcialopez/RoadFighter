package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Turbo extends GameObject {
	
	private static final String TURBO_SYMBOL = ">>>";
	
	private static final int TURBO_FORWARD = 3;
	
	public static final String INFO = "[TURBO] pushes the car: " + TURBO_FORWARD + " columns";
	
	private boolean isAlive;

	public Turbo(Game game, int positionX, int positionY) { 
		super(game, positionX, positionY);
		isAlive = true;
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		
		isAlive = false;
		player.push(TURBO_FORWARD);
		return true;
	}

	@Override
	public boolean receiveShoot() {return false;}
	
	@Override
	public boolean receiveExplosion() {return false;}
	
	@Override
	public boolean receiveThunder() {return false;}

	@Override
	public void onEnter() {}

	@Override
	public void update() {}

	@Override
	public void onDelete() {}

	@Override
	public String getSymbol() {
		return TURBO_SYMBOL;
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}
}
