package com.kroy.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kroy.gameobjects.Firetruck;
import java.lang.Math;
import java.util.ArrayList;

public class GameWorld {
    private Firetruck selectedTruck;
    private ArrayList<Firetruck> truckList;
    private GameState currentState;
    private GameRenderer renderer;
    public MapGrid map;
    
    public enum GameState {

        READY, RUNNING, GAMEOVER

    }

    public GameWorld (int midPointY) {
    	Firetruck truck1 = new Firetruck(0, 3, 76, 105);
    	Firetruck truck2 = new Firetruck(17, 9, 76, 105);
    	this.truckList = new ArrayList<Firetruck>();
    	this.truckList.add(truck1);
    	this.truckList.add(truck2);
    	this.selectedTruck = truck1;
    	currentState = GameState.READY;
    }

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

    private void updateRunning(float delta) {
    	for (int i = 0; i < truckList.size(); i++){
    		truckList.get(i).update(delta);
    	}
	}

	private void updateReady(float delta) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Firetruck> getFiretrucks() {
        return truckList;

    }
    
    public boolean isReady() {
    	return currentState == GameState.READY;
    }
    
    public boolean isRunning() {
    	return currentState == GameState.RUNNING;
    }
    
    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
    	for (int i = 0; i < truckList.size(); i++){
    		truckList.get(i).onRestart();
    	}

    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public void setRenderer(GameRenderer r) {
    	renderer = r;
    }
    
    public Vector2 lockGoal(int x, int y) {
    	Vector2 newGoal = new Vector2(0, 0);
    	newGoal.add(x - (x%45) + (45/2),y - (y%45) + (45/2));
    	return newGoal;
    }
    
    public void onClick(int x, int y) {
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

    	case GAMEOVER:
    		this.restart();
    		break;
}    	
    }
}
