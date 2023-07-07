package SpaceEvaders.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;



import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.GameObjects.CollidableObject;
import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.Systems.InputHandler.Input;
import SpaceEvaders.Systems.InputHandler.InputListener;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.EventsSystem.EventListener;
import SpaceEvaders.Systems.CollisionSystem.CollisionManager;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.Systems.BulletManager.BulletManager;
import SpaceEvaders.GameObjects.PlayerShip;
import SpaceEvaders.Systems.PhysicsManager.Physics;
import SpaceEvaders.Systems.EnemyManager.EnemyManager;
import SpaceEvaders.UI.PlayPanel;



public class PlayState implements IState, InputListener, EventListener {

    private List<GameObject> gameObjects = new ArrayList<>();
    private BulletManager bulletManager = new BulletManager(gameObjects);
    private CollisionManager collisionManager = new CollisionManager(Constants.screenWidth, Constants.screenHeight, 5);

    private float time = 0;
    private float spawnTimer = 0;
    public class Score{
        public int value = 0;}
    private Score score = new Score();

    private PlayPanel playPanel = new PlayPanel(gameObjects, score);
    // Window gameWindow = new Window("Game Window");

    private PlayerShip player = new PlayerShip();

    public void onKeyPressed(Input input) {
        if (input == Input.PAUSE) {
            SL.stateMachine.changeState(new PauseState(), this);
            SL.eventHandler.notify(EventType.PAUSED);
        }
        if (input == Input.ESCAPE) {
            SL.stateMachine.changeState(new SplashState(), this);
        }
    }

    public void onEvent(EventType event, Object... data) {
        System.out.println("PlayState: onEvent");
        if (event == EventType.ENEMY_HIT)
            score.value += 100;
        System.out.println(score);
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
        SL.eventHandler.addListener(this);

        if (args.length == 0) {
            gameObjects.add(player);
            playPanel.setSize(Constants.screenWidth, Constants.screenHeight);
            playPanel.setVisible(true);
            SL.window.add(playPanel);
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
        playPanel.repaint();
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
    

