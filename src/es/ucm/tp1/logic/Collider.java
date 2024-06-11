package es.ucm.tp1.logic;

import es.ucm.tp1.logic.gameobjects.Player;

public interface Collider {
	public boolean receiveCollision(Player player);
	public boolean receiveShoot();
	public boolean receiveExplosion();
	public boolean receiveThunder();
}
