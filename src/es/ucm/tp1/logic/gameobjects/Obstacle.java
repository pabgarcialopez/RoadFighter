package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Obstacle extends GameObject{

	private static final String OBSTACLE_SYMBOL = "░"; 
	
	public static final String INFO = "[Obstacle] hits car";
	
	private static final int INITIAL_LIVES = 1;
	
	protected int lives;
	
	private static int totalObstacles = 0;

	public Obstacle(Game game, int positionX, int positionY) { 
		super(game, positionX, positionY);
		this.lives = INITIAL_LIVES;
	}
	
	public Obstacle(Game game, int positionX, int positionY, int lives) {
		this(game, positionX, positionY);
		this.lives = lives;
	}
	
	@Override
	public boolean isAlive() { return (lives != 0); }
	
	@Override
	public void onEnter() {totalObstacles++;}
	
	@Override
	public void update() {}
	
	@Override
	public void onDelete() {totalObstacles--;}
	
	public static void reset() {totalObstacles = 0;}
	
	public boolean receiveCollision(Player player) {
		player.kill();	
		return true;
	}
	
	@Override
	public boolean receiveShoot() {
		this.lives--;
		return true;
	}
	
	@Override
	public boolean receiveExplosion() {
		lives = 0;
		return true;
	}
	
	@Override
	public boolean receiveThunder() {
		return receiveExplosion();
	}
	
	public static int getTotalObstacles() {return totalObstacles;}
	
	@Override
	public String getSymbol() {
		return OBSTACLE_SYMBOL;
	}
}