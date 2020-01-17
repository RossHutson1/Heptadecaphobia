package com.kroy.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class MapGrid {
	private int[][] grid;
	
	public MapGrid() {
		int[][] grid = new int[45][45];
		
		FileHandle handle = Gdx.files.local("mapGrid.csv");
		String text = handle.readString();
		String cellsArray[] = text.split(",|\\r?\\n");
		for (int i = 0; i < cellsArray.length; i++) {
			grid[i/43][i%43] = Integer.parseInt(cellsArray[i]);
		}
		
		for (int x = 0; x < 43; x++) {
			for (int y = 0; y < 36; y++) {
				System.out.print(grid[y][x]);
			}
			System.out.println();
		}
		
		
		
	}
}
