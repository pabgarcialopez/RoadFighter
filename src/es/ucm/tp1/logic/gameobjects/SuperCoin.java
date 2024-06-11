package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class SuperCoin extends Coin{
	
	private static final String SUPERCOIN_SYMBOL = "$";
	private static final int PRIZE = 1000;
	public  static final String INFO = "[SUPERCOIN] gives " + PRIZE + " coins";
	private static boolean existsSuperCoin = false;
	
	private boolean isAlive;
	
	public SuperCoin(Game game, int positionX, int positionY) {
		super(game, positionX, positionY);
		this.isAlive = true;
	}

	@Override
	public boolean receiveCollision(Player player) {
		
		game.addCoins(PRIZE);
		onDelete();
		isAlive = false;
		
		return false;
	}
	
	@Override
	public void onEnter() {
		existsSuperCoin = true;
	}
	
	@Override
	public void onDelete() {
		existsSuperCoin = false;
	}
	
	@Override
	public boolean isAlive() {
		return isAlive;
	}

	@Override
	public void update() {}
	
	@Override
	public String getSymbol() {return SUPERCOIN_SYMBOL;}
	
	public static boolean hasSuperCoin() {return existsSuperCoin;}
	
	public static void reset() {existsSuperCoin = false;}
}