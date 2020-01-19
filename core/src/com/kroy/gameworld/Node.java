package com.kroy.gameworld;

import java.util.ArrayList;

public class Node {
	public boolean targetNode = false;
	private boolean[] hasNeighbours; // [right, left, up, down] each item represents is a neighbouring position in the grid is a road or not
	private int depth, x, y, targetX, targetY;
	private MapGrid map;
	private Node[] neighbours = new Node[4];
	ArrayList<Node> path;
	
	//when calling make sure it is a place on the road and 
	
	public Node(int y, int x, int targetY, int targetX) {
		depth = 0;
		MapGrid map = new MapGrid();
		map.setCellValue(y, x, 9); //set map cell value to 9 once visited so it is never revisited
		Node[] neighbours = new Node[4];
		ArrayList<Node> path = new ArrayList<Node>();
		path.add(this);
		
		if (atTarget(x, y, targetX, targetY)) {
			targetNode = true;
		} else {
			boolean[] hasNeighbours = getNeighbours(x, y);
			if (hasNeighbours[0]) {
				neighbours[0] = new Node(depth+1, y, x+1, targetX, targetY, path, map);
			}
			if (hasNeighbours[2]) {
				neighbours[1] = new Node(depth+1, y, x-1, targetX, targetY, path, map);
			}
			if (hasNeighbours[3]) {
				neighbours[2] = new Node(depth+1, y+1, x, targetX, targetY, path, map);
			}
			if (hasNeighbours[4]) {
				neighbours[3] = new Node(depth+1, y-1, x+1, targetX, targetY, path, map);
			}
		}	
		
	}
	
	public Node(int depth, int y, int x, int targetY, int targetX, ArrayList <Node> oldPath, MapGrid map) {
		this.depth = depth + 1;
		this.x = x;
		this.y = y;
		this.targetX = targetX;
		this.targetY = targetY;
		ArrayList<Node> path = oldPath;
		path.add(this);
		
		if (atTarget(x, y, targetX, targetY)) {
			targetNode = true;
		} else {
			boolean[] hasNeighbours = getNeighbours(x, y);
			if (hasNeighbours[0]) {
				neighbours[0] = new Node(depth+1, y, x+1, targetX, targetY, path, map);
			}
			if (hasNeighbours[1]) {
				neighbours[1] = new Node(depth+1, y, x-1, targetX, targetY, path, map);
			}
			if (hasNeighbours[2]) {
				neighbours[2] = new Node(depth+1, y+1, x, targetX, targetY, path, map);
			}
			if (hasNeighbours[3]) {
				neighbours[3] = new Node(depth+1, y-1, x+1, targetX, targetY, path, map);
			}
		}	
	}
	
	public ArrayList<Node> getBestPath() {
		int bestDepth = 999;
		Node bestNode = null;
		if (this.targetNode) {
			return this.path;
		} else if (!(this.hasNeighbours[0]) && !(this.hasNeighbours[1]) && !(this.hasNeighbours[2]) && !(this.hasNeighbours[3])) {
			return null;
		}
		else {
			for (Node node : neighbours) {
				if (node.getBestPath().size() < bestDepth && node.getBestPath() != null) {
					bestNode = node;
				}
			}
			return bestNode.getBestPath();
		}
	}
	
	private boolean[] getNeighbours(int x, int y) {
		boolean[] neighbours = new boolean[4];
		if (map.getCellValue(y, x+1) == 1) {
			neighbours[0] = true;
			map.setCellValue(y, x+1, 9);
		}
		if (map.getCellValue(y, x-1) == 1) {
			neighbours[1] = true;
			map.setCellValue(y, x-1, 9);
		}
		if (map.getCellValue(y+1, x) == 1) {
			neighbours[2] = true;
			map.setCellValue(y+1, x, 9);
		}
		if (map.getCellValue(y-1, x) == 1) {
			neighbours[3] = true;
			map.setCellValue(y-1, x, 9);
		}
		return neighbours;
	}
	
	public boolean atTarget(int x, int y, int targetX, int targetY) {
		if (x == targetX && y == targetY) {
			return true;
		} else {
			return false;
		}
	}

}
