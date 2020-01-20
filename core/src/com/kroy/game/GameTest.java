
package com.kroy.gameworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.kroy.gameobjects.FireStation;
import com.kroy.gameobjects.Firetruck;
import com.kroy.gameobjects.Fortress;
import com.kroy.gameworld.GameWorld;

import org.junit.Test;


public class GameTest {
	/**
	 * @Test isWon()
	 */
	
	@Test
	public void testIsWon() {
		GameWorld game = new GameWorld();
		ArrayList<Fortress> emptyList = new ArrayList<Fortress>();
		ArrayList<Fortress> nonemptyList = new ArrayList<Fortress>();
		Fortress fort = new Fortress(10,new Vector2(1,1));
		nonemptyList.add(fort);
		assertTrue(game.isWon(emptyList));
		assertFalse(game.isWon(nonemptyList));
	}
	@Test
	public void testIsLost() {
		GameWorld game = new GameWorld();
		ArrayList<Firetruck> emptyList = new ArrayList<Firetruck>();
		ArrayList<Firetruck> nonemptyList = new ArrayList<Firetruck>();
		Firetruck truck = new Firetruck(1,1,1,1);
		nonemptyList.add(truck);
		assertTrue(game.isLost(emptyList));
		assertFalse(game.isLost(nonemptyList));
	}
	@Test
	public void testEngineHealth() {
		Firetruck truck = new Firetruck(1,1,72,105);
		assertEquals(truck.getHpCurrent(),10);
		truck.damage(1);
		assertEquals(truck.getHpCurrent(),9);
	}
	/*@Test
	public void testStationHealth() {
		FireStation station = new FireStation(1,1,1);
		assertEquals(station.getHpCurrent(),10);
		station.damage();
		assertEquals(station.getHpCurrent(),9);
		
	}*/
	@Test
	public void testFortressHealth() {
		Fortress fort = new Fortress(10,new Vector2(1,1));
		assertEquals(fort.getHpCurrent(),10);
		fort.damage(1);
		assertEquals(fort.getHpCurrent(),9);
		
	}
	@Test
	public void testEngineStartNum() {
		GameWorld game = new GameWorld();
		assertEquals(game.getFiretrucks().size(),2);
	}
	@Test
	public void testTest() {
		GameWorld game = new GameWorld();
		assertEquals(game.getTest(),3);
	}
	@Test
	public void testFortressStartNum() {
		GameWorld game = new GameWorld();
		assertEquals(game.getFortList().size(),3);
	}
	@Test 
	public void testRefills() {
		Firetruck truck = new Firetruck(1,1,1,1);
		truck.fireWeapon(new Vector2(1,1),1,new ArrayList<Fortress>(),new Firetruck(1,1,1,1));
		assertEquals(truck.getHpCurrent(),9);
		truck.refill();
		assertEquals(truck.getHpCurrent(),10);
		
	}
	@Test 
	public void testRepairs() {
		Firetruck truck = new Firetruck(1,1,1,1);
		truck.damage(1);
		assertEquals(truck.getHpCurrent(),9);
		truck.repair();
		assertEquals(truck.getHpCurrent(),10);
	}
	@Test
	public void testFiretruckDestroyed() {
		Firetruck truck = new Firetruck(1,1,1,1);
		GameWorld game = new GameWorld();
		assertEquals(game.getFiretrucks().size(),2);
		for(int i =0;i<=10;i++) {
			truck.damage(1);
		}
		assertEquals(game.getFiretrucks().size(),1);
	}
	@Test
	public void testFortressDestroyed() {
		Fortress fort = new Fortress(10,new Vector2(1,1));
		GameWorld game = new GameWorld();
		assertEquals(game.getFortList().size(),3);
		for(int i =0;i<=10;i++) {
			fort.damage(1);
		}
		assertEquals(game.getFortList().size(),2);
	}

	@Test
	public void testEngineProjectiles() {
		Firetruck truck = new Firetruck(1,1,1,1);
		assertEquals(truck.getNumProjectiles(),10);
		truck.fireWeapon(new Vector2(1,1),1,new ArrayList<Fortress>(),new Firetruck(1,1,1,1));
		assertEquals(truck.getNumProjectiles(),9);
		
	}
	@Test
	public void testFortProjectiles() {
		Firetruck truck = new Firetruck(1,1,1,1);
		Fortress fort = new Fortress(10,new Vector2(1,1));
		ArrayList<Firetruck> trucks = new ArrayList<Firetruck>();
		trucks.add(truck);
		assertEquals(fort.getNumProjectiles(),10);
		fort.fireWeapon(new Vector2(1,1),1,new ArrayList<Fortress>(),trucks);
		assertEquals(fort.getNumProjectiles(),9);
		
	}
}

