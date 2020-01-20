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
	ArrayList<Projectile> fortProjectileList = new ArrayList<Projectile>();
	/**
	 * Method to initialise instance of Weapon
	 * @param firerate rate of fire of the weapon (unused)
	 * @param range range of weapon (unused)
	 * @param numProjectiles number of projectiles weapon can fire before refill
	 */
	public Weapon(int firerate, int range, int numProjectiles) {
		this.damage = 1;
		this.firerate = firerate;
		this.range = range;
		this.animate = false;
		this.numProjectiles = numProjectiles;
	}
	
	public int fire(Vector2 fortPosition, Vector2 truckPosition, int weaponCount,
			ArrayList<Fortress> fortressList, Firetruck truck) {
		Gdx.app.log("WEAPON COUNT", "" + weaponCount);
		if (weaponCount%20 == 0) {
			if (this.numProjectiles > 0) {
				this.numProjectiles -=1;
				projectileList.add(new Projectile(this.damage,5,new Vector2(fortPosition),
						new Vector2(truckPosition), fortressList, truck, true));
			}
		}
		if (weaponCount == 0) {
			fortProjectileList.add(new Projectile(this.damage,5,new Vector2(truck.getPosition()),
					new Vector2(fortPosition), fortressList, truck, false));
			Gdx.app.log("FORT-PROJECTILE", "FIRE");
			weaponCount = 41;
		}
		return weaponCount;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getWater() {
		return this.numProjectiles;
	}
	
	public int getFirerate() {
		return this.firerate;
	}
	
	public int getRange() {
		return this.range;
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
		ArrayList<Projectile> fortToRemove = new ArrayList<Projectile>();
		for(Projectile fortProjectile: this.fortProjectileList) {
			fortProjectile.update(delta);
			if (fortProjectile.remove) {
				fortToRemove.add(fortProjectile);
			}
		}
		fortProjectileList.removeAll(fortToRemove);
	}
	
	public void setNumProjectiles(int numProjectiles) {
		this.numProjectiles = numProjectiles;
	}
	
	public int getNumProjectiles() {
		return this.numProjectiles;
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectileList;
	}
	
	public ArrayList<Projectile> getFortProjectiles() {
		return fortProjectileList;
	}
}