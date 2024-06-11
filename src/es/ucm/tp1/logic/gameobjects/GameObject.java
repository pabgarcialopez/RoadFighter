package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public abstract class GameObject implements Collider {

	protected Game game;
	protected int x, y;
	protected String symbol;

	public GameObject(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public int getX() {return x;}

	public int getY() {return y;}

	public void incrementColumn() {this.y++;}
	
	protected String getSymbol() {return symbol;}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public String getSerializedInfo() {
		return (this.toString() + " (" + this.y +", " + this.x + ")");
	}
	
	@Override
	public String toString() {
		if (isAlive()) 
			return getSymbol();

		return "";
	}
	
	public abstract boolean isAlive();
	public abstract void onEnter();
	public abstract void update();
	public abstract void onDelete();
}
