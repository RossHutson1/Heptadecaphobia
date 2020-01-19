package com.kroy.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	int damage, dX, dY;
	boolean remove, stop;
	private float angle, speed;
	private GameObject target;
	private Vector2 targetPosition, currentPosition;
	//image texture;
	
	public Projectile(int damage, float speed, Vector2 targetPosition, Vector2 weaponPosition) {
		this.damage = damage;
		this.speed = speed;
		this.targetPosition = targetPosition;
		this.currentPosition = weaponPosition;
		this.remove = false;
		this.stop = false;
		
		this.dX = (int) (this.targetPosition.x - this.currentPosition.x);
		this.dY = (int) (this.targetPosition.y - this.currentPosition.y);
		this.angle = (float) Math.atan2(dY, dX);
	}
	
	public void update(float delta) {

		double distance = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);
		if (distance > this.speed) {
			this.stop = true;
		}
		if (!this.stop) {
			float moveX = MathUtils.cos(this.angle) * this.speed;
			this.currentPosition.x += moveX;
			float moveY = MathUtils.sin(this.angle) * this.speed;
			this.currentPosition.y += moveY;
		} else {
			this.remove = true;
		}
		Gdx.app.log("Projectile", Math.round(this.currentPosition.x) + " " + Math.round(this.currentPosition.y) + " " + 
				Math.round(this.targetPosition.x) + " " + Math.round(this.targetPosition.y) + " " + this.angle);
	}
	
	public Vector2 getPosition() {
		return this.currentPosition;
	}
}