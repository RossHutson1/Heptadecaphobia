package com.kroy.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.kroy.gameobjects.Firetruck;

public class FireStation extends GameObject {
	
	private int range;
	private int width;
	private int height;
	private Vector2 position;
	private ArrayList<Firetruck> truckList;
	
	public FireStation(int range, int width1, int height1, ArrayList<Firetruck> tList, Vector2 pos) {
		this.range = range;
		truckList = tList;
		position = pos;
		width = width1;
		height = height1;
	}
	
	public FireStation(int range, int width, int height, ArrayList<Firetruck> tList, int x, int y) {
		this(range, width, height, tList, new Vector2(x, y));
	}

	public void update(float delta) {
		for(int i = 0; i < truckList.size(); i++) {
			Firetruck truck = truckList.get(i);
			Vector2 pos = truck.getPosition();
			if(pos.x > (position.x - range - 23) && pos.x < (position.x + width + range + 23) &&
			   pos.y > (position.y - range - 23) && pos.y < (position.y + height+ range + 23)) {
				truck.refill();
				truck.repair();
			}
		}
	}

}
