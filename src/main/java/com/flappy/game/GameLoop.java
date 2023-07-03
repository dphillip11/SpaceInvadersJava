package com.flappy.game;

import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GameLoop {
    private final float startPosX = 0.5f;
    private final float startPosY = 0.8f;
    private final int WINDOW_HEIGHT = 700;
    private final int WINDOW_WIDTH = 1000;
    private Window window = new Window();
    private boolean running;
    public GamePanel gamePanel = new GamePanel();
    private List<GameObject> gameObjects = new ArrayList<>();
    
    public void start() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Point position = window.convertToScreenPos(startPosX, startPosY);
        Spaceship spaceship = new Spaceship(position.x, position.y, gameObjects);
        gameObjects.add(spaceship);
        window.inputHandler.addListener(spaceship);
        window.add(gamePanel);
        window.setVisible(true);

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
