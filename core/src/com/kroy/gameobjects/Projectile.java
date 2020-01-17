package com.kroy.gameobjects;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	int damage, velocity;
	boolean animate;
	GameObject target;
	Vector2 position;
	Vector2 fortPosition;
	//image texture;
	
	public Projectile(int damage, int velocity, Vector2 fortPosition, Vector2 position) {
		this.damage = damage;
		this.velocity = velocity;
		this.animate = false;
		this.fortPosition = fortPosition;
		this.position = position;
		//this.texture = texture;
	}
	public void draw() {
		
	}
	public void update() {
		
	}
	public Vector2 getPosition() {
		return this.position;
	}
}
