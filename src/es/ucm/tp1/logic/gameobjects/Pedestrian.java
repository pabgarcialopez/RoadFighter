package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Pedestrian extends Obstacle {

	private static final String PEDESTRIAN_SYMBOL = "☺";
	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	private static final int INITIAL_LIVES = 1;
	
	private boolean goingDown;
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y, INITIAL_LIVES);
		this.goingDown = true;
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		player.deleteAllCoins();
		lives--;
		return super.receiveCollision(player);
	}

	@Override
	public void update() {
		
		// Si esta en la primera fila, baja.
		if(x == 0) goingDown = true;
		
		// Si esta en la ultima fila, sube.
		else if (x == game.getRoadWidth() - 1) goingDown = false;
			
		if(goingDown) x++;
		else x--;
	}
	
	@Override
	public boolean receiveShoot() {	
		game.deleteAllCoins();
		super.receiveShoot();
		return true;
	}

	@Override
	public boolean receiveExplosion() {
		return receiveShoot();
	}
	
	@Override
	public boolean receiveThunder() {
		lives = 0;
		return true;
	}
	
	@Override
	public String getSymbol() {
		return PEDESTRIAN_SYMBOL;
	}
	
	@Override
	public String getSerializedInfo() {
		StringBuilder s = new StringBuilder();
		
		s.append(super.getSerializedInfo()).append(" ");
		if(goingDown) s.append("down");
		else s.append ("up");
		
		return s.toString();
	}
}
