package com.kroy.gameobjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	int damage, speed;
	float dx, dy, angle;
	boolean animate, remove;
	private GameObject target;
	private Vector2 targetPosition, currentPosition;
	//image texture;
	
	public Projectile(int damage, int speed, Vector2 targetPosition, Vector2 weaponPosition) {
		this.damage = damage;
		this.speed = speed;
		this.animate = false;
		this.targetPosition = targetPosition;
		this.currentPosition = weaponPosition;
		this.remove = false;
	}
	
	public void update(float delta) {
		
		int dX = (int) (this.targetPosition.x - this.currentPosition.x);
		int dY = (int) (this.targetPosition.y - this.currentPosition.y);
		float angle = (float) Math.atan2(dY, dX);
		
		float moveX = MathUtils.cos(angle) * this.speed;
		float moveY = MathUtils.sin(angle) * this.speed;
		this.currentPosition.x += dx*delta;
		this.currentPosition.y += dy*delta;
		
		if (this.currentPosition == this.targetPosition) {
			this.remove = true;
			// remove from fortress health
		}
	}
	
	public Vector2 getPosition() {
		return this.currentPosition;
	}
}
