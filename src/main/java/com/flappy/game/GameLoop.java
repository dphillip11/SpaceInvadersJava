package com.flappy.game;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GameLoop {
    private int TARGET_FPS = 60;
    private final float startPosX = 0.5f;
    private final float startPosY = 0.8f;
    public static int WINDOW_HEIGHT = 720;
    public static int WINDOW_WIDTH = 1024;
    private Window window = new Window();
    private boolean running;
    public GamePanel gamePanel = new GamePanel();
    private List<GameObject> gameObjects = new ArrayList<>();
    private CollisionManager collisionManager;;
    double spawnTimer = 0;
    
    public void start() {
        //setup window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.add(gamePanel);
        window.setVisible(true);

        // Get the default graphics device
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Check if fullscreen is supported
        if (graphicsDevice.isFullScreenSupported()) {
            // Set the window to fullscreen
            graphicsDevice.setFullScreenWindow(window);
        }

        WINDOW_HEIGHT = window.getHeight();
        WINDOW_WIDTH = window.getWidth();

        //initialize collision manager
        collisionManager = new CollisionManager(WINDOW_WIDTH, WINDOW_HEIGHT, 5);

        //initialize player
        Point position = window.convertToScreenPos(startPosX, startPosY);
        PlayerShip player = new PlayerShip(position.x, position.y, gameObjects);
        gameObjects.add(player);
        window.inputHandler.addListener(player);

        running = true;
        
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / TARGET_FPS; // 60 ticks per second
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update(delta/TARGET_FPS);
                delta--;
            }

            gamePanel.repaint();
        }
    }
    
    private void CullInactiveObjects()
    {
        Iterator<GameObject> iterator = gameObjects.iterator();
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
        EnemyManager.SpawnRow(gameObjects, WINDOW_WIDTH, 100, 100);
    }
    // check collisions
    collisionManager.performCollisionDetection(gameObjects);
    
    // Create a copy of the gameObjects list
    List<GameObject> gameObjectsCopy = new ArrayList<>(gameObjects);
    
    // Iterate over the copied list
    for (GameObject gameObject : gameObjectsCopy) {
        gameObject.update(deltaTime);
    }
    
    // remove inactive objects
    CullInactiveObjects();
    // repeat input for pressed keys
    window.fireHeldArrowKeys();
}
    
    public class GamePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Clear the screen
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Create a copy of the gameObjects collection
        List<GameObject> gameObjectsCopy = new ArrayList<>(gameObjects);
        
        // Iterate over the copied collection
        for (GameObject gameObject : gameObjectsCopy) {
            if (gameObject != null && gameObject.isActive())
                gameObject.draw(g);
        }
    }
}
}
