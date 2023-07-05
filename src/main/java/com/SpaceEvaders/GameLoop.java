package com.SpaceEvaders;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

import com.SpaceEvaders.GameState.Constants;
import com.SpaceEvaders.GameState.GameState;
import com.SpaceEvaders.Systems.CollisionSystem.CollisionManager;
import com.SpaceEvaders.Systems.EnemyManager.EnemyManager;

import com.SpaceEvaders.GameObjects.PlayerShip;
import com.SpaceEvaders.GameObjects.GameObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GameLoop {

    private Window window = new Window();
    private boolean running;
    public GamePanel gamePanel = new GamePanel();
    private GameState gameState = new GameState();
    private CollisionManager collisionManager;
    double spawnTimer = 0;
    
    public void start() {
        //setup window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Constants.screenWidth, Constants.screenHeight);
        window.add(gamePanel);
        window.setVisible(true);

        // Get the default graphics device
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Check if fullscreen is supported
        if (graphicsDevice.isFullScreenSupported()) {
            // Set the window to fullscreen
            graphicsDevice.setFullScreenWindow(window);
        }

        Constants.screenHeight = window.getHeight();
        Constants.screenWidth = window.getWidth();

        //initialize collision manager
        collisionManager = new CollisionManager(Constants.screenWidth, Constants.screenHeight, 5);

        //initialize player
        PlayerShip player = new PlayerShip();
        GameState.gameObjects.add(player);

        running = true;
        
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / Constants.targetFPS; // 60 ticks per second
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update(delta/Constants.targetFPS);
                delta--;
            }

            gamePanel.repaint();
        }
    }
    
    private void CullInactiveObjects()
    {
        Iterator<GameObject> iterator = GameState.gameObjects.iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (!gameObject.isActive()) {
                iterator.remove();
            }
        }
    }
    
    private void update(double deltaTime) {
    // spawn enemies
    spawnTimer -= deltaTime;
    if (spawnTimer <= 0) {
        spawnTimer = 4;
        EnemyManager.SpawnRow(GameState.gameObjects, Constants.screenWidth, 100, 100);
    }
    // check collisions
    collisionManager.performCollisionDetection(GameState.gameObjects);
    
    // Create a copy of the gameObjects list
    List<GameObject> gameObjectsCopy = new ArrayList<>(GameState.gameObjects);
    
    // Iterate over the copied list
    for (GameObject gameObject : gameObjectsCopy) {
        gameObject.update(deltaTime);
    }
    
    // remove inactive objects
    CullInactiveObjects();
    // repeat input for pressed keys
    GameState.inputHandler.fireHeldArrowKeys();
}
    
    public class GamePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Clear the screen
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
}
