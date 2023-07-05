package SpaceEvaders.GameState;

import java.util.List;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Systems.BulletManager.BulletManager;
import SpaceEvaders.Systems.EventsSystem.EventHandler;
import SpaceEvaders.Systems.InputHandler.InputHandler;

import java.util.ArrayList;



public class GameState {

    public float time = 0;
    public boolean running = true;
    public boolean paused = false;

    public static final List<GameObject> gameObjects = new ArrayList<>();
    public static final EventHandler eventHandler = new EventHandler();
    public static final InputHandler inputHandler = new InputHandler();
    public static final BulletManager bulletManager = new BulletManager();

    //calling contructor will clear static fields
    public GameState() {
        gameObjects.clear();
        eventHandler.init();
        inputHandler.init();
        eventHandler.addListener(bulletManager);
    }

}
