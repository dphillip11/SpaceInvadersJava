package com.flappy.game;

import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GameLoop {
    private final float startPosX = 0.5f;
    private final float startPosY = 0.8f;
    private final int WINDOW_HEIGHT = 1200;
    private final int WINDOW_WIDTH = 1000;
    private Window window = new Window();
    private boolean running;
    public GamePanel gamePanel = new GamePanel();
    private List<GameObject> gameObjects = new ArrayList<>();
    private CollisionManager collisionManager = new CollisionManager(window.getWidth(),window.getHeight(), 10);
    
    public void start() {
        //setup window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.add(gamePanel);
        window.setVisible(true);
        //initialize player
        Point position = window.convertToScreenPos(startPosX, startPosY);
        PlayerShip player = new PlayerShip(position.x, position.y, gameObjects);
        gameObjects.add(player);
        window.inputHandler.addListener(player);
        //spawn an enemy
        gameObjects.add(new Enemy(200, 100, gameObjects));
        EnemyManager.SpawnRow(gameObjects, WINDOW_WIDTH, 100, 1);

        running = true;

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / 60.0; // 60 ticks per second
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update(delta);
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
        // Update game logic here
        
        for (GameObject gameObject : gameObjects) {
            gameObject.update(deltaTime);
        }
        CullInactiveObjects();
        window.fireHeldArrowKeys();
        collisionManager.performCollisionDetection(gameObjects);
    }
    
    public class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Clear the screen
            g.setColor(java.awt.Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            for (GameObject gameObject: gameObjects) {
                gameObject.draw(g);
            }
        }
    }
}
