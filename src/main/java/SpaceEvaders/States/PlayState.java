package SpaceEvaders.States;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.GameObjects.CollidableObject;
import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.Systems.InputHandler.Input;
import SpaceEvaders.Systems.InputHandler.InputListener;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.CollisionSystem.CollisionManager;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.Systems.BulletManager.BulletManager;
import SpaceEvaders.GameObjects.PlayerShip;
import SpaceEvaders.Systems.PhysicsManager.Physics;
import SpaceEvaders.Systems.EnemyManager.EnemyManager;
import SpaceEvaders.Systems.RenderingSystem.ObjectRenderer;


public class PlayState implements IState, InputListener {

    public JPanel gamePanel = new JPanel(true) {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Constants.screenWidth, Constants.screenHeight);
            ObjectRenderer.render(g, gameObjects);
        }
    };

    private List<GameObject> gameObjects = new ArrayList<>();
    private BulletManager bulletManager = new BulletManager(gameObjects);
    private CollisionManager collisionManager = new CollisionManager(Constants.screenWidth, Constants.screenHeight, 5);

    private float time = 0;
    private float spawnTimer = 0;
    private int score = 0;

    private PlayerShip player = new PlayerShip();

    public void onKeyPressed(Input input) {
        if (input == Input.PAUSE) {
            SL.stateMachine.changeState(new PauseState(), this);
            SL.eventHandler.notify(EventType.PAUSED);
        }
    }

    @Override
    public State getState() {
        return State.PLAY;
    }

    @Override
    public void enter(Object... args) {
        SL.inputHandler.init();
        SL.inputHandler.addListener(this);
        SL.inputHandler.addListener(player);

        if (args.length == 0) {
            gameObjects.add(player);
            gamePanel.setSize(Constants.screenWidth, Constants.screenHeight);
            gamePanel.setVisible(true);
            SL.window.add(gamePanel);
            SL.eventHandler.addListener(bulletManager);
        }
    }

    @Override
    public void update(double deltaTime) {
        time += deltaTime;
        Physics.UpdatePositions(deltaTime, gameObjects);
        collisionManager.performCollisionDetection(gameObjects);
        CullInactiveObjects();
        EnemyManager.UpdateEnemyVelocities(gameObjects);
        spawnTimer -= deltaTime;
        if (spawnTimer <= 0) {
            spawnTimer = 4;
            EnemyManager.SpawnRow(gameObjects, Constants.screenWidth, 100, 100);
        }

        // Render to the buffer
        gamePanel.repaint();

        // Repeat input for pressed keys
        SL.inputHandler.fireHeldArrowKeys();
    }

    @Override
    public void exit() {
        System.out.println("PlayState: exit" + time);
    }
    

    private void CullInactiveObjects() {
    Iterator<GameObject> iterator = gameObjects.iterator();
    while (iterator.hasNext()) {
        GameObject gameObject = iterator.next();
        ((CollidableObject) gameObject).applyCollision();
        if (!gameObject.isActive()) {
            iterator.remove(); // Use the iterator's remove method
        }
    }

}
}
    

