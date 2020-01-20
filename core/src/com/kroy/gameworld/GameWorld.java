package com.kroy.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kroy.gameobjects.FireStation;
import com.kroy.gameobjects.Firetruck;
import com.kroy.gameobjects.Fortress;
import com.kroy.gameobjects.GameObject;
import com.kroy.gameobjects.Projectile;

import java.lang.Math;
import java.util.ArrayList;

public class GameWorld {
    private Firetruck selectedTruck;
    private FireStation fStation;
    private ArrayList<Firetruck> truckList;
    private ArrayList<Fortress> fortressList;
    private static GameState currentState; 
    private GameRenderer renderer;
    public MapGrid map;
    private int weaponCount;
    
    public enum GameState {
        READY, RUNNING, GAMEWON, GAMELOST
    }
    /**
     * Initialising method for gameWorld that creates needed instances of various gameObjects
     * @param midPointY
     */
    public GameWorld (int midPointY) {
    	Firetruck truck1 = new Firetruck(0, 3, 76, 105);
    	Firetruck truck2 = new Firetruck(17, 9, 76, 105);
    	this.truckList = new ArrayList<Firetruck>();
    	this.truckList.add(truck1);
    	this.truckList.add(truck2);
    	this.selectedTruck = truck1;
    	currentState = GameState.READY;
    	this.fortressList = new ArrayList<Fortress>();
    	this.fortressList.add(generateFortress(10, new Vector2(Math.round(36.5f*45),Math.round(1.5f*45))));
    	this.fortressList.add(generateFortress(10, new Vector2(Math.round(29.5f*45),Math.round(33.5f*45))));
    	this.fortressList.add(generateFortress(10, new Vector2(Math.round(5.5f*45),Math.round(6.5f*45))));
    	fStation = new FireStation(1, 90, 45, truckList, new Vector2(36*45, 27*45));
    	weaponCount = 41;
    }
    /**
     * method to update the current state
     * @param delta the time between 2 frames
     */
    public void update(float delta) {
    	switch (currentState) {
    	case READY:
    		updateReady(delta);
    		break;
    	
    	case RUNNING:
    		updateRunning(delta);
    		break;
    	}
    }
    /**
     * update method when game is in running state
     * @param delta time between frames
     */
    private void updateRunning(float delta) {
    	if (weaponCount > 0) {
    		weaponCount -= 1;
    	} else {
    		weaponCount = 0;
    	}
    	for (int i = 0; i < truckList.size(); i++){
    		truckList.get(i).update(delta);
    		ArrayList<Fortress> toRemove = new ArrayList<Fortress>();
    		for(Fortress fort: this.fortressList) {
    			weaponCount = fort.findTrucks(this.truckList, weaponCount, fortressList);
    			if (fort.getHpCurrent() == 0) {
    				toRemove.add(fort);
    			}
    		}
    		fortressList.removeAll(toRemove);
    	}
    	ArrayList<Firetruck> truckToRemove = new ArrayList<Firetruck>();
    	for (Firetruck truck: truckList) {
    		if (truck.getHpCurrent() == 0) {
    			truckToRemove.add(truck);
    		}
    	}
    	truckList.removeAll(truckToRemove);
    	isWon(fortressList);
    	fStation.update(delta);
	}
    
	private void updateReady(float delta) {
		// TODO Auto-generated method stub
	}

	public ArrayList<Firetruck> getFiretrucks() {
        return truckList;
    }
	
	public ArrayList<Fortress> getFortList() {
		return fortressList;
	}
	/**
	 * Function used to instantiate instances of Fortress
	 * @param health the fortress' health as an int
	 * @param position Vector2 containing it's x and y position
	 * @return the instance of Fortress
	 */
	public static Fortress generateFortress(int health, Vector2 position) {
		Fortress newFortress = new Fortress(health, position);
		return newFortress;
	}
    // Methods to check game state
    public boolean isReady() {
    	return currentState == GameState.READY;
    }
    
    public boolean isRunning() {
    	return currentState == GameState.RUNNING;
    }
    
    public boolean isGameOver() {
        return (currentState == GameState.GAMEWON || currentState == GameState.GAMELOST);
    }
    //Methods to change game state
    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
    	for (int i = 0; i < truckList.size(); i++){
    		truckList.get(i).onRestart();
    	}
    }
    /**
     * Method to set the gameWorld's renderer
     * @param r the GameRenderer instance
     */
    public void setRenderer(GameRenderer r) {
    	renderer = r;
    }

    public Vector2 lockGoal(int x, int y) {
    	Vector2 newGoal = new Vector2(0, 0);
    	newGoal.add(x - (x%45) + (45/2),y - (y%45) + (45/2));
    	return newGoal;
    }
    /**
     * Static method to check if game is won and change state
     * @param fortressList the list of Fortress instances
     * @return boolean if game is won or not
     */
    public static boolean isWon(ArrayList<Fortress> fortressList) {
		if (fortressList.isEmpty()) {
			currentState = GameState.GAMEWON;
			return true;
		} else {
			return false;
		}
	}
    /**
     * Static method to check if game is lost and change state
     * @param firetruckList the list of Firetruck instances
     * @return boolean if game is lost or not
     */
    public static boolean isLost(ArrayList<Firetruck> firetruckList) {
		if (firetruckList.isEmpty()) {
			currentState = GameState.GAMELOST;
			return true;
		}
		else {
			return false;
		}
	}
    /**
     * Method to react to user input of a click
     * @param x x-coordinate of the click
     * @param y y-coordinate of the click
     */
    public void onClick(int x, int y) {
    	float zoom = renderer.getZoom();
    	int w = Gdx.graphics.getWidth();
    	int h = Gdx.graphics.getHeight();
    	x = (int) (((x - w/2) * zoom) + w/2);
    	y = (int) (((y - h/2) * zoom) + h/2);
    	Vector3 cPos = renderer.getCameraPos();
    	Vector2 offset = renderer.getOffset();
    	x = x + ((int) cPos.x - (int) offset.x);
    	y = y + ((int) cPos.y - (int) offset.y);
    	switch (currentState) {
    	case READY:
    		this.start();
    		break;
    	
    	case RUNNING:
    		boolean truckClicked = false;
        	for (int i = 0; i < truckList.size(); i++){
        		Firetruck truck = truckList.get(i);
	    		if(Math.abs(truck.getX()-x) < 45 && Math.abs(truck.getY()-y) < 45) {
	    			truck.onClick(x, y);
	    			selectedTruck = truck;
	    			truckClicked = true;
	    		}} if (!truckClicked) {
	    			Vector2 goal = lockGoal(x, y);
	    			this.selectedTruck.setGoal((int) goal.x, (int) goal.y);
	    		}
    		break;
}    	
    }
}
