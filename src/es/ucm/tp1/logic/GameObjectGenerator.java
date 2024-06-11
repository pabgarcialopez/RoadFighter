package es.ucm.tp1.logic;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.actions.ThunderAction;
import es.ucm.tp1.logic.gameobjects.*;

public class GameObjectGenerator {

	public static void generateGameObjects(Game game, Level level) {
		
		for(int x = game.getVisibility()/2; x < game.getRoadLength(); x ++) {
			
			game.tryToAddObject(new Obstacle(game, game.getRandomLane(), x) , level.obstacleFrequency());
			game.tryToAddObject(new Coin(game, game.getRandomLane(), x) , level.coinFrequency());
			
			if (level.hasAdvancedObjects()) {
				
				game.tryToAddObject(new Wall(game, game.getRandomLane(), x), level.advancedObjectsFrequency());
				game.tryToAddObject(new Turbo(game, game.getRandomLane(), x), level.advancedObjectsFrequency());
				
				if (!SuperCoin.hasSuperCoin())
					game.tryToAddObject(new SuperCoin(game, game.getRandomLane(), x), level.advancedObjectsFrequency());
			
				game.tryToAddObject(new Truck(game, game.getRandomLane(), x), level.advancedObjectsFrequency());
				game.tryToAddObject(new Pedestrian(game, 0, x), level.advancedObjectsFrequency());
			}
		}
	}

	static void reset() {
		Obstacle.reset();
		Coin.reset();
		SuperCoin.reset();
	}

	static void generateRuntimeObjects(Game game) {
		
		if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction()); }
	}
	
	static void forceAdvanceObject(Game game, int id, int column) {
		GameObject o = null;
		
		switch (id) {
		
		case 1:
			o = new Wall(game, game.getRandomLane(), column);
			break;
			
		case 2:
			o = new Turbo(game , game.getRandomLane(), column);
			break;
			
		case 3:
			o = new SuperCoin(game, game.getRandomLane(), column);
			break;
			
		case 4:
			o = new Truck(game, game.getRandomLane(), column);
			break;
			
		case 5:
			o = new Pedestrian(game, 0, column);
			break;
		}
		
		game.forceAddObject(o);
	}
	
	static void addGrenade(Game game, int x, int y) {
		GameObject o = new Grenade(game, x, y);
		game.forceAddObject(o);
	}
}
