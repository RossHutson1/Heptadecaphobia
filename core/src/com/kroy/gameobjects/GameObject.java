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
	/**
	 * Accessor method for a gameObject's position
	 * @return a Vector2 containing it's x and y position
	 */
	public Vector2 getPosition(){
		return this.position;
	};
	/**
	 * Accessor method to get the maximum possible health for a gameObject
	 * @return int containing the max health
	 */
	public int getHpMax(){
		return this.hpMax;
	};
	/**
	 * Accessor method for the current health of the gameObject
	 * @return int containing the health
	 */
	public int getHpCurrent() {
		return this.hpCurrent;
	};
	/**
	 * Method to take away from the health of a gameObject when it's hit
	 * @param damageTaken the damage the projectile inflicted
	 * @return a boolean of if the gameObject is alive or not
	 */
	public boolean damage(int damageTaken) {
		if (damageTaken > this.hpCurrent) {
			this.hpCurrent = 0;
			return true;
		}
		this.hpCurrent -= damage;
		return false;
	};
	
	/**
	 * Function to return the angle between the gameObject and a target gameObject
	 * @param target The object target
	 * @return Returns angle between objects in radians
	 */
	protected double getAngleTo(GameObject target) {
		Vector2 tPos = target.getPosition();
		int dX = (int) (tPos.x - this.position.x);
		int dY = (int) (tPos.y - this.position.y);
		double angle = Math.atan2(dY, dX);
		return angle;
	};
	/**
	 * Function to return the distance between the gameObject and a target gameObject
	 * @param target The object target
	 * @return returns the distance in pixels between the objects
	 */
	protected double getDistanceTo(GameObject target) {
		Vector2 tPos = target.getPosition();
		int dX = (int) (tPos.x - this.position.x);
		int dY = (int) (tPos.y - this.position.y);
		double distance = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);
		return distance;
	};
}