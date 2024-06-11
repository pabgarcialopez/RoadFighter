package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.actions.*;

public class Grenade extends GameObject {
	
	private static final String GRENADE_SYMBOL = "ð";
	public static final String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	private static final int INITIAL_LIVES = 4;
	
	private int lives;
	
	public Grenade(Game game, int positionX, int positionY) { 
		super(game, positionX, positionY);
		this.lives = INITIAL_LIVES;
	}
	
	@Override
	public boolean receiveCollision(Player player) {return false;}
	
	@Override
	public boolean receiveShoot() {return false;}
	
	@Override
	public boolean receiveExplosion() {return false;}
	
	@Override
	public boolean receiveThunder() {return false;}
	
	@Override
	public void onEnter() {}
	
	@Override
	public void onDelete() {
		InstantAction action = new ExplosionAction(super.x, super.y);
		game.execute(action);
	}
	
	@Override
	public void update() {lives--;}

	@Override
	public boolean isAlive() {
		return (lives > 0);
	}
	
	@Override
	public String getSymbol() {
		return GRENADE_SYMBOL + "["+ lives + "]";
	}
	
	@Override
	public String getSerializedInfo() {
		return (super.getSerializedInfo() + " " + lives);
	}
}