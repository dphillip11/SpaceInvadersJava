package SpaceEvaders.States;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;



import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.GameObjects.HealthPowerup;
import SpaceEvaders.GameObjects.CollidableObject;
import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.Systems.InputHandler.Input;
import SpaceEvaders.Systems.InputHandler.InputListener;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.EventsSystem.EventListener;
import SpaceEvaders.Systems.CollisionSystem.CollisionManager;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.BulletManager.BulletManager;
import SpaceEvaders.GameObjects.PlayerShip;
import SpaceEvaders.Systems.PhysicsManager.Physics;
import SpaceEvaders.Systems.EnemyManager.EnemyManager;
import SpaceEvaders.UI.PlayPanel;
import SpaceEvaders.Utilities.Vector2;



public class PlayState implements IState, InputListener, EventListener {

    public List<GameObject> gameObjects = new ArrayList<>();
    private BulletManager bulletManager = new BulletManager(gameObjects);
    private CollisionManager collisionManager = new CollisionManager(Constants.screenWidth, Constants.screenHeight, 5);

    public float time = 0;
    private float spawnTimer = 0;
    public int score = 0;
    public float difficulty = 1;

    private PlayPanel playPanel = new PlayPanel(this);
    public PlayerShip player = new PlayerShip();


    @Override
    public void enter(Object... args) {
        SL.inputHandler.init();
        SL.inputHandler.addListener(this);
        SL.inputHandler.addListener(player);
        SL.eventHandler.addListener(this);

        if (args.length == 0) {
            player.setPosition(Constants.screenWidth / 2, Constants.screenHeight - 100);
            gameObjects.add(player);
            playPanel.setSize(Constants.screenWidth, Constants.screenHeight);
            playPanel.setVisible(true);
            SL.window.add(playPanel);
            SL.eventHandler.addListener(bulletManager);
        }

        SL.eventHandler.notify(EventType.PLAY);
    }

    @Override
    public void update(double deltaTime) {
        playPanel.healthBar.setHealth(player.getHealth(), player.getMaxHealth());
        time += deltaTime;
        difficulty = 1 + (time / 10);
        UpdateDifficulty();
        Physics.UpdatePositions(deltaTime, gameObjects);
        collisionManager.performCollisionDetection(gameObjects);
        CullInactiveObjects();
        EnemyManager.UpdateEnemyVelocities(gameObjects, player.getPosition());
        spawnTimer -= deltaTime;
        if (spawnTimer <= 0) {
            spawnTimer = Variables.enemySpawnInterval;
            EnemyManager.SpawnRow(gameObjects, Constants.screenWidth);
        }

        // Render to the buffer
        playPanel.repaint();
        // Repeat input for pressed keys
        SL.inputHandler.fireHeldArrowKeys();

    }

    @Override
    public void exit() {
        System.out.println("PlayState: exit, time: " + time);
    }

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
        if (event == EventType.ENEMY_HIT)
            score += 100;
        if (event == EventType.PLAYER_DESTROYED) {
            SL.stateMachine.changeState(new GameOverState(), score);
        }
        if (event == EventType.PLAYER_REPLENISH_HEALTH) {
            assert (data.length == 1);
            player.replenishHealth((int) data[0]);
        }
        if (event == EventType.SPAWN_HEALTH_POWERUP) {
            assert (data.length == 2);
            System.out.println(((Vector2) data[0]).x);
            System.out.println(((Vector2) data[0]).y);
            System.out.println((int)data[1]);
            gameObjects.add(new HealthPowerup((Vector2) data[0], (int)data[1]));
        }
    }

    @Override
    public State getState() {
        return State.PLAY;
    }
    

    private synchronized void CullInactiveObjects() {
        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            ((CollidableObject) gameObject).resetCollisions();
            if (!gameObject.isActive()) {
                iterator.remove(); // Use the iterator's remove method
            }
        }
    }

    public void UpdateDifficulty() {

    

    }
}
    

