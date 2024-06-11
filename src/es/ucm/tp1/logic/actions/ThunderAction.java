package es.ucm.tp1.logic.actions;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class ThunderAction implements InstantAction {
	
	private static final String THUNDER_MSG = "Thunder hit position: ";
	
	private int positionX, positionY;
	private boolean hit;
	private String objectSymbol;
	
	public ThunderAction() {
		this.hit = false;
	}
	
	@Override
	public void execute(Game game) {
		
		positionY = game.getRandomColumn(); // Columna
		positionX = game.getRandomLane(); // Fila
		
		// Comprobar que en esa posición hay un obstaculo o no.
		Collider other = game.getColliderInPosition(positionX, positionY + game.getPlayerPositionY());
		
		if(other != null) {

			objectSymbol = other.toString();
			hit = other.receiveThunder();
		}
		
		thunderMSG();
	}
	
	private void thunderMSG() {
		
		StringBuilder buffer = new StringBuilder();
		buffer.append(THUNDER_MSG + "(" + positionY + " , " + positionX + ")");
		
		if(hit) buffer.append(" -> " + objectSymbol);
		
		System.out.println(buffer.toString());
	}
}
