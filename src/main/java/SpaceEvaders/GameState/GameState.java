package SpaceEvaders.GameState;

import java.util.List;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Systems.InputHandler.*;
import SpaceEvaders.Systems.EventsSystem.*;

import java.util.ArrayList;

public class GameState extends PlayEventListener implements InputListener  {

    public static float time = 0;
    public static boolean paused = false;
    public static boolean running = true;
    public static int score = 0;

    public static final List<GameObject> gameObjects = new ArrayList<>();

    //calling contructor will clear static fields
    public void init() {
        //initialise service locator
        SL.init();
        SL.inputHandler.addListener(this);
        SL.eventHandler.addListener(this);
        gameObjects.clear();
        score = 0;
    }

    public void onKeyPressed(Input input) {
        if (input == Input.PAUSE) {
            paused = !paused;
            System.out.println("Paused: " + paused);
        }
    }

    public void onPlayEvent(EventType event, Object... args) {
        if (event == EventType.ENEMY_DESTROYED) {
            score++;
            System.out.println("Score: " + score);
        }
    }

}
