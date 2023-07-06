package SpaceEvaders;

import javax.swing.*;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.GameObjects.CollidableObject;
import SpaceEvaders.GameObjects.PlayerShip;
import SpaceEvaders.GameState.Constants;
import SpaceEvaders.GameState.GameState;
import SpaceEvaders.Systems.CollisionSystem.CollisionManager;
import SpaceEvaders.Systems.EnemyManager.EnemyManager;
import SpaceEvaders.Systems.RenderingSystem.ObjectRenderer;
import SpaceEvaders.Systems.PhysicsManager.Physics;

import SpaceEvaders.UI.Window;

import java.awt.Graphics;
import java.util.Iterator;

public class GameLoop {

    private Window window = new Window("Space Evaders");
    public GamePanel gamePanel = new GamePanel(window);
    private CollisionManager collisionManager;
    double spawnTimer = 0;
    
    public void start() {
        GameState gameState = new GameState();
        gameState.init();
        
        //initialize collision manager
        collisionManager = new CollisionManager(Constants.screenWidth, Constants.screenHeight, 5);

        //initialize player
        PlayerShip player = new PlayerShip();
        GameState.gameObjects.add(player);

        GameState.running = true;
        
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / Constants.targetFPS; // 60 ticks per second
        double delta = 0;

        while (GameState.running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                double deltaTime = 0;
                if (!GameState.paused)
                {
                    deltaTime = delta / Constants.targetFPS;
                    Physics.UpdatePositions(deltaTime, GameState.gameObjects);
                    update(deltaTime);
                    collisionManager.performCollisionDetection(GameState.gameObjects);
                    CullInactiveObjects();
                }
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
            ((CollidableObject)gameObject).applyCollision();
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
    
    
    // remove inactive objects
    CullInactiveObjects();
    // repeat input for pressed keys
    SL.inputHandler.fireHeldArrowKeys();
}
    
public class GamePanel extends JPanel {
        
    GamePanel(Window window) {
        window.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
       
        super.paintComponent(g);
        // Clear the screen
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
         ObjectRenderer.render(g, GameState.gameObjects);
    }
}
}
