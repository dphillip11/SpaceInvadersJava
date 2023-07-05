package com.SpaceEvaders.GameState;

import java.util.List;
import java.util.ArrayList;

import com.SpaceEvaders.GameObjects.GameObject;
import com.SpaceEvaders.Systems.EventsSystem.EventHandler;
import com.SpaceEvaders.Systems.EventsSystem.InputHandler;



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
