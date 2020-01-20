package com.kroy.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

//Possibly still need to implement more methods here
//such as a method to add water?

public class Fortress extends GameObject{

	int level; // how strong the fortress is
	int pumpSpeed; // Speed at which water is drained from fortress
	private Weapon weapon= new Weapon(1,3,10);
	
	public Fortress(int waterCapacityInitial, Vector2 position) {
		this.position = position;
		this.level = 1;
		this.pumpSpeed = 1; 
		this.hpCurrent = this.hpMax;
		//this.weapon = new Weapon();
	}
	
	private void Fire(GameObject target) {
		
	}
	
	private void levelUp() {
		
	}
	
	public int getNumProjectiles() {
    	return this.weapon.getNumProjectiles();
    }
	
	//public void fireWeapon(Vector2 truckPosition, int weaponCount,
	//		ArrayList<Fortress> fortressList, ArrayList<Firetruck> truckList) {
    //	this.weapon.fire(new Vector2(truckPosition), this.position, weaponCount, fortressList, truckList);
    //}
	
	public int findTrucks(ArrayList<Firetruck> truckList, int weaponCount, ArrayList<Fortress> fortressList) {
		for (double x=  this.position.x-2*45; x<=this.position.x+2*45; x+=1) {
			for (double y=  this.position.y-2*45; y<=this.position.y+2*45; y+=1) {
				for (Firetruck truck : truckList) {
					if (x == (truck.getX()) && y == (truck.getY())) {
						weaponCount = truck.fireWeapon(this.position, weaponCount, fortressList, truck);
					}
				}
			}
		}
		return weaponCount;
	}
	
}