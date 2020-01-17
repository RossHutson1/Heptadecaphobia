package com.kroy.gameobjects;

import java.util.ArrayList;

//Possibly still need to implement more methods here
//such as a method to add water?

public class Fortress extends GameObject{

	int waterCapacity; // amount of water to defeat aliens within fortress
	int waterLevel; // amount of water currently in the fortress
	int level; // how strong the fortress is
	int pumpSpeed; // Speed at which water is drained from fortress
	//Weapon weapon; // Used for the fortress to shoot at the fireTrucks
	
	public Fortress(int waterCapacityInitial) {
		this.waterCapacity = waterCapacityInitial; 
		this.waterLevel = 0;
		this.level = 1;
		this.pumpSpeed = 1; 
		//this.weapon = new Weapon();
	}
	
	private void Fire(GameObject target) {
		
	};
	
	private void levelUp() {
		
	};
	public void findTrucks(ArrayList<Firetruck> trucklist) {
		//ArrayList<Firetruck> trucks = 
		for (double x=  this.position.x-2; x<=this.position.x+2; x+=1) {
			for (double y=  this.position.y-2; y<=this.position.y+2; y+=1) {
				for (Firetruck truck : trucklist) {
					if (x == (truck.getX()) && y == (truck.getY())) {
						truck.fireWeapon(this.position);
					}
				}
			}
		}
	}
}
