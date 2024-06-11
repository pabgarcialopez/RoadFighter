package es.ucm.tp1.logic.actions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;

public class WaveAction implements InstantAction{

	public WaveAction() {}
	
	@Override
	public void execute(Game game) {
		
		int playerPositionY = game.getPlayerPositionY();
		int visibility = game.getVisibility();
		int width = game.getRoadWidth();
		
		for(int i = playerPositionY + visibility - 1; i >= playerPositionY; i--) {
			
			for(int j = 0; j < width; j++) {
				
				if(game.getObjectInPosition(j, i + 1) == null) {
					GameObject gameObject = game.getObjectInPosition(j, i);
					if(gameObject != null) 
						gameObject.incrementColumn();
					
				}
			}
		}
		
		game.update();
	}
}


