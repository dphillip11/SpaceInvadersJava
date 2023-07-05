package SpaceEvaders.GameState;

import java.util.List;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Systems.EventsSystem.EventHandler;
import SpaceEvaders.Systems.EventsSystem.InputHandler;

import java.util.ArrayList;



public class GameState {

    public float time = 0;
    public boolean running = true;
    public boolean paused = false;

    public static final List<GameObject> gameObjects = new ArrayList<>();
    public static final EventHandler eventHandler = new EventHandler();
    public static final InputHandler inputHandler = new InputHandler();

    //calling contructor will clear static fields
    public GameState() {
        gameObjects.clear();
        eventHandler.init();
        inputHandler.init();
    }

}
