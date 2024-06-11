package es.ucm.tp1.logic.actions;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class ShootAction implements InstantAction {

	@Override
	public void execute(Game game) {
		
		boolean encontrado = false;
		int i = game.getPlayerPositionY();
		
		while(!encontrado && i < game.getPlayerPositionY() + game.getVisibility()) {
			
			Collider other = game.getColliderInPosition(game.getPlayerPositionX(), i);
			if(other != null) encontrado = other.receiveShoot();
			i++;
		}
		
		game.update();
	}
}
