package com.kroy.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kroy.gameobjects.GameObject;
import com.kroy.gameobjects.Projectile;
import com.kroy.helpers.AssetLoader;
import com.kroy.screens.GameScreen;

public class KroyGame extends Game {
	
	int test = 3;
	ArrayList<Object> fortressList = new ArrayList<Object>();
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
	
	public int getTest() {
		return test;
	}
	public static boolean isLost(ArrayList<Object> firetruckList) {
		
		if (firetruckList.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	/*/
	public void objectHit(GameObject object, Projectile projectile) {
		if (projectile.getPosition() == object.getPosition()) {
			object.damage();
		}
	}
	public static FireTruck generateFireTruck() {
		FireTruck newTruck = new FireTruck(0, 0);
		return newTruck;
	}
	
	public static Patrol spawnPatrol() {
		Patrol newPatrol = new Patrol(0);
		return newPatrol;
	}
	
	public static Fortress generateFortress() {
		Fortress newFortress = new Fortress(0);
		return newFortress;
	}
	/*/
}
