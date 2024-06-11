package es.ucm.tp1.logic.gameobjects;
import es.ucm.tp1.logic.*;
import es.ucm.tp1.utils.StringUtils;

public class Player {
	
	private static final int INITIAL_COINS = 5;
	
	private static final int INITIAL_COLUMN = 0;
	
	private static final String ALIVE_SYMBOL = ">";
	
	private static final String CRASHED_SYMBOL = "@";
	
	private Game game;
	
	private int positionX, positionY;
	
	private boolean isPlayerAlive;
	
	private int collectedCoins;

	public Player(int x, Game game) {
		this.positionX = x;
		this.positionY = INITIAL_COLUMN;
		this.collectedCoins = INITIAL_COINS;
		this.isPlayerAlive = true;
		this.game = game;
	}

	public boolean isInPosition(int x, int y) {
		if(positionX == x && positionY == y)
			return true;
		
		return false;
	}
	public void update() {}
	
	// Player
	public boolean doCollision() {
		Collider other = game.getColliderInPosition(positionX, positionY);
		if (other != null) 
			return other.receiveCollision(this);
		
		return false;
	}
	
	public void playerUp() {
		
		playerStraight();
		if(positionX > 0) positionX--;
	}
	
	public void playerDown() {
		playerStraight();
		if(positionX < game.getRoadWidth() - 1) positionX++;
	}
	
	public int distanceToGoal() {
		return game.getLevel().getLength() - this.positionY;
	}
	
	public String toString() {
		if(isPlayerAlive) return ALIVE_SYMBOL;
		else return CRASHED_SYMBOL;
	}
	
	public void toInitialPosition() {
		positionX = game.getLevel().getWidth() / 2;
		positionY = INITIAL_COLUMN;
		collectedCoins = INITIAL_COINS;
	}

	public void spendCoins(int coins) {
		collectedCoins -= coins;
	}
	
	public void addCoins(int coins) {
		collectedCoins += coins;
	}
	public String getPlayerSerialized() {
		
		return (this.toString() + " (" + this.positionY +", " + this.positionX + ")" + StringUtils.LINE_SEPARATOR);
	}
	
	public void push(int turboForward) {
		positionY += turboForward;
	}
	
	public void deleteAllCoins() {
		collectedCoins = 0;
	}
	
	public void playerStraight() {
		positionY++;
	}
	
	// GETTERS
	
	public int getPositionX() {return positionX;}
	
	public int getPositionY() {return positionY;}
	
	public boolean getIsAlive() {return isPlayerAlive;}
	
	public int getCollectedCoins() {return collectedCoins;}
	
	public void kill() {isPlayerAlive = false;}
}
