package com.kroy.gameobjects;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import java.awt.Image;
import java.lang.Math;

public abstract class GameObject {
	
	protected Vector2 position;
	protected int hpMax = 10;
	protected int hpCurrent;
	protected ArrayList<Image> texture;
	protected int damage = 1;

	public Vector2 getPosition(){
		return this.position;
	};

	public int getHpMax(){
		return this.hpMax;
	};

	public int getHpCurrent() {
		return this.hpCurrent;
	};

	public boolean damage(int damageTaken) {
		if (damageTaken > this.hpCurrent) {
			this.hpCurrent = 0;
			return true;
		}
		this.hpCurrent -= damage;
		return false;
	};

	protected double getAngleTo(GameObject target) {
		Vector2 tPos = target.getPosition();
		int dX = (int) (tPos.x - this.position.x);
		int dY = (int) (tPos.y - this.position.y);
		double angle = Math.atan2(dY, dX);
		return angle;
	};

	protected double getDistanceTo(GameObject target) {
		Vector2 tPos = target.getPosition();
		int dX = (int) (tPos.x - this.position.x);
		int dY = (int) (tPos.y - this.position.y);
		double distance = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);
		return distance;
	};
}