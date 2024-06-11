package es.ucm.tp1.logic.actions;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class ExplosionAction implements InstantAction {
	
	private static final int GRENADE_RANGE = 2;
	
	private int positionX;
	private int positionY;
	
	public ExplosionAction(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	@Override
	public void execute(Game game) {
		
		for(int j = positionX - 1; j < positionX + GRENADE_RANGE; j++) {
			for(int i = positionY - 1; i < positionY + GRENADE_RANGE; i++) {
				Collider other = game.getColliderInPosition(j, i);
				if(other != null) other.receiveExplosion();
			}
		}
	}
}