package es.ucm.tp1.logic;

import java.util.ArrayList;
import java.util.List;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.utils.StringUtils;

public class GameObjectContainer {

	private List <GameObject> gameObjects;
	
	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
	
	void add(GameObject gameObject) {
		gameObjects.add(gameObject);
		gameObject.onEnter();
	}
	
	private void delete(GameObject gameObject) {
		gameObjects.remove(gameObject);
	}
	
	void removeDeadObjects() {	
		for(int i = 0; i < gameObjects.size(); i++) {
			if(!gameObjects.get(i).isAlive()) {
				gameObjects.get(i).onDelete();
				gameObjects.remove(i);	
			}
		}
	}
	
	GameObject getObjectInPosition(int x, int y) {
		GameObject gameObject = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < gameObjects.size()) {
			if(gameObjects.get(i).isInPosition(x, y) && gameObjects.get(i).isAlive()) {
				encontrado = true;
				gameObject = gameObjects.get(i);
			}
			i++;
		}
		return gameObject;
	}
	
	
	boolean isPosEmpty(int x, int y) {
		GameObject go = getObjectInPosition(x, y);
		if(go == null) return true;
		else return false;
	}
	
	void clear() {
		int size = gameObjects.size();
		for(int i = size - 1; i >= 0; i--) {
			gameObjects.get(i).onDelete();
			gameObjects.remove(i);
		}
	}
	
	void deleteObjectColumn(int i, int column) {
		GameObject other = getObjectInPosition(i, column);
		if(other != null) {
			other.onDelete();
			delete(other);
		}
	}
	
	String getPositionToString(int x, int y) {
		
		String s = "";
		
		for (GameObject g : gameObjects) 
			if (g.isInPosition(x, y)) 
				s += g.toString() + " "; 
			
		return s;
	}
	
	void updateObjects() {
		for(int i = 0; i < gameObjects.size(); i++)
			gameObjects.get(i).update();
	}
	
	String getObjectSerialize(int x, int y) {
		
		String s = "";
		for (GameObject g : gameObjects) 
			if (g.isInPosition(x, y)) 
				s +=  g.getSerializedInfo() + StringUtils.LINE_SEPARATOR; 
			
		return s;
	}
	
	String manageSaveObjects() {
		String s = "";
		for(int i= 0; i < gameObjects.size(); i++) {
			s += gameObjects.get(i).getSerializedInfo() + StringUtils.LINE_SEPARATOR;
		}
		return s;
	}
}