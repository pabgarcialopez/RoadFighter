package es.ucm.tp1.logic;

import java.util.Random;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.gameobjects.GameObject; 
import es.ucm.tp1.logic.gameobjects.Player; 
import es.ucm.tp1.logic.actions.InstantAction; 
import es.ucm.tp1.logic.GameObjectContainer; 
import es.ucm.tp1.logic.GameObjectGenerator;
import es.ucm.tp1.exceptions.GameException;
import es.ucm.tp1.exceptions.InputOutputRecordException;
import es.ucm.tp1.exceptions.InvalidPositionException;

public class Game {
	
	private static final String INVALID_POSITION_MSG = "Invalid position.";
	
	private Level level;
	private boolean isTest;
	
	private Random random;
	private int roadLength;
	private long elapsedTime;
	private int cycle;
	private boolean exit;
	private Record record;
	private long startingElapsedTime;
	private long seed;
    
	private GameObjectContainer gameObjectContainer;
	
	private Player player;
	
	private static final String GOAL_SYMBOL = "¦";
	
	public Game(long seed, Level level, boolean isTest) throws GameException{
		this.isTest = isTest;
		this.exit = false;
		try {
			reset(level, seed);
		}
		catch(InputOutputRecordException e){
			throw new GameException(e.getMessage(), e);
		}
	}

	public void reset(Level level, Long seed) throws InputOutputRecordException{
		this.level = level;
		this.seed = seed;
		reset();
	}
	
	public void reset() throws InputOutputRecordException {
		
			record = Record.loadRecord(level);
			random = new Random(seed);
			gameObjectContainer = new GameObjectContainer();
			this.player = new Player(level.getWidth()/2, this);
			roadLength = level.getLength();
			
			GameObjectGenerator.reset();
			GameObjectGenerator.generateGameObjects(this, level);
			
			cycle = 0;
			elapsedTime = 0;
			startingElapsedTime = 0;
	}
	
	void tryToAddObject(GameObject gameObject, double frecuency) {
		if(random.nextDouble() < frecuency && gameObjectContainer.isPosEmpty(gameObject.getX(), gameObject.getY()))
			gameObjectContainer.add(gameObject);
	}
	 
	public String positionToString(int x, int y) {
		
		int relativeY = player.getPositionY() + y;
		String s = "";
		
		if (player.isInPosition(x, relativeY)) 
			 s = player.toString() + " ";
		
		s += gameObjectContainer.getPositionToString(x, relativeY);
		if (relativeY == getRoadLength()) 
			s += GOAL_SYMBOL;
		
		return s.trim();
	}
	
	public boolean isFinished() {

		if(!player.getIsAlive() || exit)
			return true;
		
		if(hasArrived()) {
			
			if(!isTestMode()) {
				try {
					record.setRecord(elapsedTime);
				} catch(InputOutputRecordException ex) {
					System.out.format(ex.getMessage() + "%n%n");
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public void update() {
		
		if(cycle == 0) startingElapsedTime = System.currentTimeMillis();
		else elapsedTime = System.currentTimeMillis() - startingElapsedTime;
		
		player.doCollision(); // Gestiona colisiones despues de haber comprobado una primera vez y movido
		
		gameObjectContainer.updateObjects();
		GameObjectGenerator.generateRuntimeObjects(this); // Rayo
		
		gameObjectContainer.removeDeadObjects(); // Elimina objetos muertos
		
		cycle++;
	}
	
	public void spendCoins(int coins) {
		player.spendCoins(coins);
	}
	public void addCoins(int coins) {
		player.addCoins(coins);
	}
	public void deleteAllObjects() {
		gameObjectContainer.clear();
	}
	
	public void deleteLastColumn() {

		int column = player.getPositionY() + level.getVisibility() - 1;
		for(int i = 0; i < level.getWidth(); i++) {
			gameObjectContainer.deleteObjectColumn(i, column);
		}
	}
	
	public void goUp() {
		if(!player.doCollision())
			player.playerUp();
		
		update();
	}
	
	public void goDown() {
		if(!player.doCollision())
			player.playerDown();
		
		update();
	}
	
	public void goStraight() {
		if(!player.doCollision())
			player.playerStraight();
		
		update();
	}
	
	public Collider getColliderInPosition(int positionX, int positionY) {
		return gameObjectContainer.getObjectInPosition(positionX, positionY);
	}
	
	public GameObject getObjectInPosition(int x, int y) {
		return gameObjectContainer.getObjectInPosition(x,y);
	}
	
	public int getRandomLane() {
		  return (int) (random.nextDouble() * getRoadWidth());
	}
	
	public int getRandomColumn() {
		return (int) (random.nextDouble() * level.getVisibility());
	}
	
	public void execute(InstantAction action) {
		action.execute(this);
	}
	
	void forceAddObject(GameObject object) {
		gameObjectContainer.add(object);
	}
	
	public void addForceAdvancedObject(int numberOfObject){
		deleteLastColumn();
		GameObjectGenerator.forceAdvanceObject(this, numberOfObject, player.getPositionY() + level.getVisibility() - 1);
	}
	
	public boolean checkGrenadePosition (int positionX, int positionY) throws InvalidPositionException{
		// Vemos si la posicion de la granada es correcta
		positionY += player.getPositionY();
		if (positionX < 0 || positionX > level.getWidth() || positionY >= level.getVisibility() + player.getPositionY() 
		 || gameObjectContainer.getObjectInPosition(positionX, positionY) != null) {
				throw new InvalidPositionException(INVALID_POSITION_MSG);
		}
		return true;
	}

	public void addGrenade(int positionX, int positionY){
		positionY += player.getPositionY();
		GameObjectGenerator.addGrenade(this, positionX, positionY);
	}
	
	public void deleteAllCoins() {
		player.deleteAllCoins();
	}
	
	public String getObjectInfo(int i, int j) {
		// i fila, j columna
		
		String s = "";
		
		if (player.isInPosition(i, j)) 
			s+= player.getPlayerSerialized();
		
		s += gameObjectContainer.getObjectSerialize(i, j);
		
		return s.trim();
	}
	
	// GETTERS
	
	public Level getLevel() {
		return this.level;
	}
	
	public int getRoadLength() {
		return roadLength;
	}
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	
	public int getVisibility() {
		return level.getVisibility();
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public boolean isUserExit() {
		return exit;
	}
	
	public void finishGame() {
		exit = true;
	}
	
	public void testOn() {
		isTest = true;
	}
	
	public boolean isTestMode() {
		return isTest;
	}
	
	public int distanceToGoal() {
		return player.distanceToGoal();
	}
	
	public int playerCoins() {
		return player.getCollectedCoins();
	}
	
	public long elapsedTime() {
		return elapsedTime;
	}
	
	public long getRecord() {
		return record.getCurrentRecord();
	}
	
	public boolean hasArrived() {
		return (distanceToGoal() + 1 == 0);
	}
	
	public int getPlayerPositionY() {
		return player.getPositionY();
	}

	public int getPlayerPositionX() {
		return player.getPositionX();
	}

	public boolean isNewRecord() {
		return record.isNewRecord();
	}
}
