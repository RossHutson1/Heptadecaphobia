package com.kroy.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Weapon {

	private int damage, firerate, range;
	private boolean animate;
	private Vector2 position = new Vector2();
	private int numProjectiles;
	//private image texture;//change type
	
	public Weapon( int firerate,int range, int numProjectiles) {
		this.damage = 1;
		this.firerate = firerate;
		this.range = range;
		this.animate = false;

		this.numProjectiles = numProjectiles;
		//this.texture = texture;
	}
	public void fire(Vector2 fortPosition) {
		this.numProjectiles -=1;
		Projectile projectile = new Projectile(1,1,fortPosition,this.position);
	}
	public int getDamage() {
		return this.damage;
	}
	public int getFirerate() {
		return this.firerate;
	}
	public int getRange() {
		return this.range;
	}


	public void draw() {
		
	}
	public void update() {
		
	}
	public void setNumProjectiles(int numProjectiles) {
		this.numProjectiles = numProjectiles;
	}
}
