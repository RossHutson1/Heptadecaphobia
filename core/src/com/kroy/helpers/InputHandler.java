package com.kroy.helpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.kroy.gameobjects.Firetruck;
import com.kroy.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	
	private GameWorld myWorld;
	private int mouseX;
	private int mouseY;
	private Vector2 cameraVel;
	
	public InputHandler(GameWorld myWorld) {
		this.myWorld = myWorld;
		cameraVel = new Vector2(0, 0);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.W) {
			cameraVel.y = -5;
		} else if (keycode == Input.Keys.S) {
			cameraVel.y = 5;
		} 
		if (keycode == Input.Keys.D) {
			cameraVel.x = 5;
		} else if (keycode == Input.Keys.A) {
			cameraVel.x= -5;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.W) {
			cameraVel.y = 0;
		} else if (keycode == Input.Keys.S) {
			cameraVel.y = 0;
		} 
		if (keycode == Input.Keys.D) {
			cameraVel.x = 0;
		} else if (keycode == Input.Keys.A) {
			cameraVel.x = 0;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
	public Vector2 getCameraDelta() {
		return new Vector2(cameraVel);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		myWorld.onClick(Gdx.input.getX(), Gdx.input.getY());
		/*/
		if (myWorld.isReady()) {
			myWorld.start();
		} else if (myWorld.isRunning()) {
			mouseX = Gdx.input.getX();
			mouseY = Gdx.input.getY();
			myTruck.onClick(mouseX, mouseY);
		}

		if (myWorld.isGameOver()) {
			myWorld.restart();
		}
		/*/
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
