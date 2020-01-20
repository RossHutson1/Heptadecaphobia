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

}
