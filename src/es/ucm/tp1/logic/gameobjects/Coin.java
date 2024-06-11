package es.ucm.tp1.logic.gameobjects;
import es.ucm.tp1.logic.Game;

public class Coin extends GameObject{

	private static final String COIN_SYMBOL = "¢";
	
	public static final String INFO = "[Coin] gives 1 coin to the player";
	
	private static final int SINGLE_COIN = 1;
	
	private static int totalCoins = 0;
	
	private boolean isAlive;
	
	public Coin(Game game, int positionX, int positionY) { 
		super(game, positionX, positionY);
		this.isAlive = true;
	}
	
	@Override
	public void onEnter() {totalCoins++;}
	
	@Override
	public void update() {}
	
	@Override
	public void onDelete() {totalCoins--;}
	
	@Override
	public boolean isAlive() {return isAlive;}
	
	public static void reset() {totalCoins = 0;}

	public boolean receiveCollision(Player player) {
		
		isAlive = false;
		player.addCoins(SINGLE_COIN);
		
		return false;
	}
	
	@Override
	public boolean receiveShoot() {return false;}

	@Override
	public boolean receiveExplosion() {return false;}

	@Override
	public boolean receiveThunder() {return false;}
	
	public static int getTotalCoins() {return totalCoins;}
	
	@Override
	public String getSymbol() {return COIN_SYMBOL;}
}
