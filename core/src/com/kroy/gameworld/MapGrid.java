package com.kroy.gameworld;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class MapGrid {
	private int[][] grid;
	
	public MapGrid() {
		grid = new int[45][45];
		
		FileHandle handle = Gdx.files.local("mapGrid.csv");
		String text = handle.readString();
		String cellsArray[] = text.split(",|\\r?\\n");
//		System.out.print(Arrays.toString(cellsArray));
		for (int i = 0; i < cellsArray.length; i++) {
			grid[i/43][i%43] = Integer.parseInt(cellsArray[i]);
		}
		
	}
	public int getCellValue(int row, int col) {
		return grid[row][col];
	}
}
