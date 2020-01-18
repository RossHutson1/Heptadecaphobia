package com.kroy.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	int damage, velocity;
	boolean animate;
	private GameObject target;
	private Vector2 weaponPosition;
	private Vector2 targetPosition;
	//image texture;
	
	public Projectile(int damage, int velocity, Vector2 targetPosition, Vector2 weaponPosition) {
		this.damage = damage;
		this.velocity = velocity;
		this.animate = false;
		this.targetPosition = targetPosition;
		this.weaponPosition = weaponPosition;
		//this.texture = texture;
	}
	public void draw() {
		
	}
	public void update() {
		
	}
	public Vector2 getPosition() {
		return this.weaponPosition;
	}
}
