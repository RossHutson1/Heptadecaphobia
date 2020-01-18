package com.kroy.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.kroy.gameworld.MapGrid;

public class Firetruck extends GameObject{

    private Vector2 position;
    private Vector2 initPos;
    private Vector2 velocity;

    private float rotation;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;
    private int goalX;
    private int goalY;
    public MapGrid mGrid;
    
    private boolean startMove;
    private boolean notDestroyed;
    private Weapon firehose= new Weapon(1,3,10);

    public Firetruck(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2((x * 45) + (int)45/2, (int)45/2 + (y*45));
        this.initPos = this.position;
        this.velocity = new Vector2(0, 0);
        goalX = (int)position.x;
        goalY = (int)position.y;
        this.startMove = false;
        this.notDestroyed = false;
        this.mGrid = new MapGrid();
        
    }

    public void update(float delta) {
    	if (this.getX()<goalX && this.velocity.y == 0) {
    		if((mGrid.getCellValue((int)(this.getY()/45), (int)(this.getX()/45)+1) == 1)) {
    		this.velocity.x = 5;
    		this.velocity.y = 0;
    		this.position.add(this.velocity);
    		} else {
    			this.position.x = this.getX() - (this.getX()%45) + 45/2;
    			this.velocity.x = 0;
    		}
    		if(this.getX()>=goalX) {
    			this.position.x=goalX;
        		this.velocity.x = 0;
    		}
    	} else if (this.getX()>goalX && this.velocity.y == 0) {
    	if(this.getX()%45 > 45/2 || (mGrid.getCellValue((int)(this.getY()/45), (int)(this.getX()/45)-1) == 1)) {
        this.velocity.x = -5;
		this.velocity.y = 0;
		this.position.add(this.velocity);
    	} else {
			this.position.x = this.getX() - (this.getX()%45) + 45/2;
			this.velocity.x = 0;    		
    	}
		if(this.getX()<=goalX) {
			this.position.x=goalX;
    		this.velocity.x = 0;
			}
    	}
    	if (this.getY()>goalY && this.velocity.x == 0) {
    		if((mGrid.getCellValue((int)(this.getY()/45)-1, (int)(this.getX()/45)) == 1)) {
            this.velocity.x = 0;
    		this.velocity.y = -5;
    		this.position.add(this.velocity);
    		} else {
    			this.position.y = this.getY() - (this.getY()%45) + 45/2;
    			this.velocity.y = 0;    		    			
    		}
    		if(this.getY()<=goalY) {
    			this.position.y=goalY;
        		this.velocity.y = 0;
    			}
    	} else if (this.getY()<goalY && this.velocity.x == 0) {
    		if((mGrid.getCellValue((int)(this.getY()/45)+1, (int)(this.getX()/45)) == 1)) {
            this.velocity.x = 0;
    		this.velocity.y = 5;
    		this.position.add(this.velocity);
    		} else {
    			this.position.y = this.getY() - (this.getY()%45) + 45/2;
    			this.velocity.y = 0;    		    			    			
    		}
    		if(this.getY()>=goalY) {
    			this.position.y=goalY;
        		this.velocity.y = 0;
    			}
    		}
    }
    	
    	
    	/*/
		if (this.position.x%45!=0 || this.position.y%45 != 0 || this.startMove) {
			this.position.add(this.velocity);
		} else {
			this.velocity.x = 0;
			this.velocity.y = 0;
		}
		this.startMove = false;
		/*/
    
    public void moveX(boolean moving, boolean right) {
    	if (moving) {
    		if (right) {
        		this.velocity.x = 5;
        		this.rotation = 90;
        	} else {
        		this.velocity.x = -5;
        		this.rotation = 270;
        	}
    	} else {
    		this.velocity.x = 0;
    	}
    	if (this.position.x%45!=0 || moving) {
    		this.startMove = true;
    	}
    }
    
    public void moveY(boolean moving, boolean up) {
    	if (moving) {
    		if (up) {
    			this.velocity.y = -5;
        		this.rotation = 0;
        	} else {
        		this.velocity.y = 5;
        		this.rotation = 180;
        	}
    	} else {
    		this.velocity.y = 0;
    	}
    	if (this.position.y%45!=0 || moving) {
    		this.startMove = true;
    	}
    }

    public void onClick(int mouseX, int mouseY) {
    	this.startMove = true;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getRotation() {
        return this.rotation;
    }
    
    public boolean notDestroyed() {
    	return this.notDestroyed;
    }
    
    public Vector2 getVelocity() {
    	return this.velocity;
    }
    
    public void startMove() {
    	this.startMove = true;
    }
    
    public void setGoal(int x, int y) {
    	this.goalX = x;
    	this.goalY = y;
    }
    
    public void onRestart() {
    	this.rotation = 0;
    	this.position= this.initPos;
    	this.velocity.y = 0;
    	this.velocity.x = 0;
        goalX = (int)position.x;
        goalY = (int)position.y;
    	this.notDestroyed = false;
    }
    public void refill() {
    	this.firehose.setNumProjectiles(10);
    }
    
    public void repair() {
    	this.hpCurrent = this.hpMax;
    }
    
    public void fireWeapon(Vector2 fortPosition) {
    	this.firehose.fire(fortPosition);
    }

}