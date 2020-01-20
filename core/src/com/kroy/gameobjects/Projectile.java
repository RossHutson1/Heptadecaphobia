package com.kroy.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	int damage;
	double dX, dY;
	boolean remove, stopX, stopY;
	ArrayList<Fortress> fortressList1;
	private float angle, speed;
	private Vector2 targetPosition, currentPosition1;
	//image texture;
	
	public Projectile(int damage, float speed, Vector2 targetPosition,
			Vector2 weaponPosition, ArrayList<Fortress> fortressList) {
		this.damage = damage;
		this.speed = speed;
		this.targetPosition = targetPosition;
		this.currentPosition1 = weaponPosition;
		this.remove = false;
		this.stopX = false;
		this.stopY = false;
		fortressList1 = fortressList;
		
		this.dX = (int) (this.targetPosition.x - this.currentPosition1.x);
		this.dY = (int) (this.targetPosition.y - this.currentPosition1.y);
		this.angle = (float) Math.atan2(dY, dX);
	}
	
	public void update(float delta) {
		float moveX = MathUtils.cos(this.angle) * this.speed;
		float moveY = MathUtils.sin(this.angle) * this.speed;
		if (this.currentPosition1.x + moveX > this.targetPosition.x && this.currentPosition1.x < this.targetPosition.x) {
			this.stopX = true;
			this.currentPosition1.x = this.targetPosition.x;
		} else if (this.currentPosition1.x + moveX < this.targetPosition.x && this.currentPosition1.x > this.targetPosition.x) {
			this.stopX = true;
			this.currentPosition1.x = this.targetPosition.x;
		} else {
			this.currentPosition1.x += moveX;
		}
		if (this.currentPosition1.y + moveY > this.targetPosition.y && this.currentPosition1.y < this.targetPosition.y) {
			this.stopY = true;
			this.currentPosition1.y = this.targetPosition.y;
		} else if (this.currentPosition1.y + moveY < this.targetPosition.y && this.currentPosition1.y > this.targetPosition.y) {
			this.stopY = true;
			this.currentPosition1.y = this.targetPosition.y;
		} else {
			this.currentPosition1.y += moveY;
		}
		
		if (this.stopX && this.stopY) {
			this.remove = true;
			for (Fortress fort : fortressList1) {
				if (fort.getPosition().toString().equals(this.targetPosition.toString())) {
					fort.damage(this.damage);
				}
			}
		}
	}
	
	public Vector2 getPosition() {
		return this.currentPosition1;
	}
}