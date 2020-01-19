package com.kroy.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Weapon {

	private int damage, firerate, range;
	private boolean animate;
	private Vector2 position = new Vector2();
	private int numProjectiles;
	ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	//private image texture;//change type
	
	public Weapon(int firerate, int range, int numProjectiles) {
		this.damage = 1;
		this.firerate = firerate;
		this.range = range;
		this.animate = false;
		this.numProjectiles = numProjectiles;
		//this.texture = texture;
	}
	public void fire(Vector2 fortPosition, Vector2 truckPosition) {
		if (this.numProjectiles > 0) {
			this.numProjectiles -=1;
			projectileList.add(new Projectile(this.damage,1,fortPosition,truckPosition));
		}
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
	public void update(float delta) {
		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
		for(Projectile projectile: this.projectileList) {
			projectile.update(delta);
			if (projectile.remove) {
				toRemove.add(projectile);
			}
		}
		projectileList.removeAll(toRemove);
	}
	public void setNumProjectiles(int numProjectiles) {
		this.numProjectiles = numProjectiles;
	}
	public ArrayList<Projectile> getProjectiles() {
		return projectileList;
	}
}