package com.kroy.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kroy.gameobjects.Firetruck;
import com.kroy.gameobjects.Fortress;
import com.kroy.gameobjects.GameObject;
import com.kroy.gameobjects.Projectile;
import com.kroy.helpers.AssetLoader;
import com.kroy.screens.GameScreen;

public class KroyGame extends Game {
	
	int test = 3;
	/*/int state;
	Screen screen = new Screen();
	EventListener eventListener = new EventListener();
	Map map = new Map();
	FireStation fireStation = new FireStation(0, 0, 0);
	ArrayList<Object> patrolList = new ArrayList<Object>();/*/
	ArrayList<Firetruck> truckList = new ArrayList<Firetruck>();
	ArrayList<Fortress> fortressList = new ArrayList<Fortress>();
	@Override
	public void create() {
		Gdx.app.log("Game", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
		
		
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
	
	public static boolean isWon(ArrayList<Object> fortressList) {
		
		if (fortressList.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public int getTest() {
		return test;
	}
	public ArrayList<Firetruck> getTruckList() {
		return this.truckList;
	}
	public static boolean isLost(ArrayList<Object> firetruckList) {
		
		if (firetruckList.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public void truckFiresAtFortress() {
		for(Fortress fort: this.fortressList) {
			fort.findTrucks(this.truckList);
		}
	}
	//public void objectHit(GameObject object, Projectile projectile) {
		//if (projectile.getPosition() == object.getPosition()) {
			//object.damage();
		//}
	//}
	
	public static Firetruck generateFireTruck() {
		Firetruck newTruck = new Firetruck(0, 0,72,105);
		return newTruck;
	}
	
	//public static Patrol spawnPatrol() {
		//Patrol newPatrol = new Patrol(0);
		//return newPatrol;
	//}
	
	public static Fortress generateFortress() {
		Fortress newFortress = new Fortress(0);
		return newFortress;
	}
	
}
