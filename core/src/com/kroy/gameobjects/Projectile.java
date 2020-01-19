package com.kroy.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	int damage;
	double dX, dY;
	boolean remove, stopX, stopY;
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
		this.stopX = false;
		this.stopY = false;
		
		this.dX = (int) (this.targetPosition.x - this.currentPosition.x);
		this.dY = (int) (this.targetPosition.y - this.currentPosition.y);
		this.angle = (float) Math.atan2(dY, dX);
	}
	
	public void update(float delta) {
		//this.dX = this.targetPosition.x - this.currentPosition.x;
		//this.dY = this.targetPosition.y - this.currentPosition.y;
		//double distance = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);
		float moveX = MathUtils.cos(this.angle) * this.speed;
		float moveY = MathUtils.sin(this.angle) * this.speed;
		Gdx.app.log("Projectile Move", "X: " + moveX);
		Gdx.app.log("Projectile Move", "Y: " + moveY);
		Gdx.app.log("BOOLEAN 1", "TEST = " + (this.currentPosition.x + moveX > this.targetPosition.x));
		Gdx.app.log("BOOLEAN 1", "TEST = " + (this.currentPosition.x < this.targetPosition.x));
		if (this.currentPosition.x + moveX > this.targetPosition.x && this.currentPosition.x < this.targetPosition.x) {
			this.stopX = true;
			this.currentPosition.x = this.targetPosition.x;
		} else {
			this.currentPosition.x += moveX;
		}
		if (this.currentPosition.y + moveY > this.targetPosition.y && this.currentPosition.y < this.targetPosition.y) {
			this.stopY = true;
			this.currentPosition.y = this.targetPosition.y;
		} else {
			this.currentPosition.y += moveY;
		}
		
		if (this.stopX && this.stopY) {
			this.remove = true;
		}
		Gdx.app.log("Projectile", Math.round(this.currentPosition.x) + " " + Math.round(this.currentPosition.y) + " " + 
				Math.round(this.targetPosition.x) + " " + Math.round(this.targetPosition.y) + " " + this.angle);
	}
	
	public Vector2 getPosition() {
		return this.currentPosition;
	}
}