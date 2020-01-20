package com.kroy.gameworld;

import com.kroy.helpers.InputHandler;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20; import com.badlogic.gdx.graphics.OrthographicCamera; import com.badlogic.gdx.graphics.Texture; import com.badlogic.gdx.graphics.g2d.SpriteBatch; import com.badlogic.gdx.graphics.g2d.TextureRegion; import com.badlogic.gdx.graphics.glutils.ShapeRenderer; import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kroy.gameobjects.Firetruck;
import com.kroy.gameobjects.Projectile;
import com.kroy.gameobjects.Fortress;
import com.kroy.helpers.AssetLoader; import com.badlogic.gdx.graphics.g2d.Animation; import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameRenderer {

private GameWorld myWorld;
private OrthographicCamera cam;
private ShapeRenderer shapeRenderer;
private InputHandler inputHandler;
private SpriteBatch batcher;
private MapGrid mGrid;

private ArrayList<Firetruck> trucks;
private Animation truckAnimation;
private TextureRegion truckStraight, truckStraight1;

private Texture backgroundTexture, minsterTexture;
private Sprite background;

private FPSLogger fps;

public GameRenderer(GameWorld world) {
    myWorld = world;
    world.setRenderer(this);
    this.inputHandler = new InputHandler(myWorld);
    
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    
    cam = new OrthographicCamera(1000, 1000*(h/w));
    cam.setToOrtho(true, w, h);
    cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0); //set start pos
    
    batcher = new SpriteBatch();
    batcher.setProjectionMatrix(cam.combined);
    
    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(cam.combined);
    
    fps = new FPSLogger();
    
    initGameObjects();
    initAssets();
    
    mGrid = new MapGrid();
    
}

private void initGameObjects() {
    trucks = myWorld.getFiretrucks();
}

public InputHandler getInputHandler() {
	return this.inputHandler;
}

public float getZoom() {
	return cam.zoom;
}

private void initAssets() {
    truckAnimation = AssetLoader.truckAnimation;
    truckStraight = AssetLoader.truck;
    truckStraight1 = AssetLoader.truck1;
    
    minsterTexture = AssetLoader.minster;
    
    backgroundTexture = AssetLoader.map;
    background = new Sprite(backgroundTexture);
    background.flip(false,  true);
//    background.setScale(0.945f);
}

public void render(float runTime) {

    // Fill the entire screen with black, to prevent potential flickering.
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    batcher.setProjectionMatrix(cam.combined);
    shapeRenderer.setProjectionMatrix(cam.combined);
    
    if (myWorld.isReady()) {
    	shapeRenderer.begin(ShapeType.Filled);

        // Draw Background colour
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer.end();
    } else {
    	batcher.begin();
    	background.draw(batcher);
    	batcher.end();
    }

    if (myWorld.isReady()) {
        startMenu();
    } else if (myWorld.isGameOver()) {
    	gameOver();
    } else if (myWorld.isRunning()) {
    	gameRunning(runTime);
    	moveCamera();
    }
    fps.log();
}

public void startMenu() {
	batcher.begin();
	AssetLoader.shadow.draw(batcher, "Click to Start", Gdx.graphics.getWidth() / 2f - 180, Gdx.graphics.getHeight() / 8f);
    AssetLoader.font.draw(batcher, "Click to Start", Gdx.graphics.getWidth() / 2f - 180, Gdx.graphics.getHeight() / 8f);
    batcher.end();
}

public void gameOver() {
	batcher.begin();
	AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
    AssetLoader.font.draw(batcher, "Game Over", 24, 55);
    batcher.end();
}

public void gameRunning(float runTime) {
	for (int i = 0; i < trucks.size(); i++) {
		batcher.begin();
		Firetruck truck = trucks.get(i);
		batcher.draw((TextureRegion) truckAnimation.getKeyFrame(runTime),  truck.getX()-(truck.getWidth()/2),
    		truck.getY()-(truck.getHeight()/2), 36f,
    		52.5f, truck.getWidth(), truck.getHeight(),
    		0.3f, 0.3f, truck.getRotation());
		batcher.end();
		
		ArrayList<Projectile> projectileList = truck.getWeapon().getProjectiles();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(242 / 255.0f, 132 / 255.0f, 15 / 255.0f, 1);
		ArrayList<Fortress> fortressList = myWorld.getFortList();
		for (Fortress fort: fortressList) {
			if (fort.getPosition().x == Math.round(36.5f*45)) {
				shapeRenderer.rect(fort.getPosition().x - 45/2, fort.getPosition().y - 45/2, 90, 90);
			} else {
				shapeRenderer.rect(fort.getPosition().x - 45/2, fort.getPosition().y - 45/2, 45, 45);
			}
		}
		shapeRenderer.setColor(47 / 255.0f, 221 / 255.0f, 237 / 255.0f, 1);
		shapeRenderer.rect(truck.getX() - 25, truck.getY() - 30, truck.getWater() * 5, 10);
		for (Projectile projectile: projectileList) {
			shapeRenderer.circle(projectile.getPosition().x,
					projectile.getPosition().y, 5f);
		}
		ArrayList<Projectile> fortProjectileList = truck.getWeapon().getFortProjectiles();
		shapeRenderer.setColor(52 / 255.0f, 237 / 255.0f, 71 / 255.0f, 1);
		for (Projectile fortProjectile: fortProjectileList) {
			shapeRenderer.circle(fortProjectile.getPosition().x,
					fortProjectile.getPosition().y, 5f);
		}
		shapeRenderer.rect(truck.getX() - 25, truck.getY() - 45, truck.getHpCurrent() * 5, 10);
		shapeRenderer.end();
	batcher.begin();
	batcher.draw(minsterTexture, 1620, 45);
	batcher.end();
	}
}

public void moveCamera() {
	Vector2 cameraD = this.inputHandler.getCameraDelta();
	float zoom = this.inputHandler.getZoom();
	cam.translate(cameraD.x, cameraD.y);
	if (cam.zoom + zoom > 0.1) {
		cam.zoom += zoom;
	}
	cam.update();
}

public Vector3 getCameraPos() {
	return new Vector3(cam.position);
}

public Vector2 getOffset() {
	Vector2 temp = new Vector2(0,0);
	temp.add(cam.viewportWidth / 2f, cam.viewportHeight / 2f);
	return temp;
}

}