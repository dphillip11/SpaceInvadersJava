package SpaceEvaders.Systems.States;

import java.awt.Graphics;


import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


import javax.swing.JPanel;

import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.Systems.CollisionSystem.CollisionManager;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.Systems.BulletManager.BulletManager;


public class PlayState implements IState {

    JPanel gamePanel1 = new JPanel();
    JPanel gamePanel2 = new JPanel();

    private List<GameObject> gameObjects = new ArrayList<>();
    private BulletManager bulletManager = new BulletManager(gameObjects);
    CollisionManager collisionManager = new CollisionManager(Constants.screenWidth, Constants.screenHeight, 5);

    @Override
    public State getState()
    {
        return State.PLAY;
    }

    @Override
    public void enter(Object... args) {
        if (args.length == 0)
        {
            SL.eventHandler.addListener(bulletManager);


  
    // 
    // double spawnTimer = 0;
     //initialize collision manager
        // 

        //initialize player
        // PlayerShip player = new PlayerShip();
        // GameState.gameObjects.add(player);
        }
    }


    @Override
    public void update(double time) {
        //  Physics.UpdatePositions(deltaTime, GameState.gameObjects);
        //             update(deltaTime);
        //             collisionManager.performCollisionDetection(GameState.gameObjects);
        //             CullInactiveObjects();
    }

    @Override
    public void exit() {
        System.out.println("PlayState: exit");
    }
    

    // private void CullInactiveObjects()
    // {
    //     Iterator<GameObject> iterator = GameState.gameObjects.iterator();
    //     while (iterator.hasNext()) {
    //         GameObject gameObject = iterator.next();
    //         ((CollidableObject) gameObject).applyCollision();
    //         if (!gameObject.isActive()) {
    //             iterator.remove();
    //         }
    //     }
    // }
    
    // private void update(double deltaTime) {
    //     // spawn enemies
    //     spawnTimer -= deltaTime;
    //     if (spawnTimer <= 0) {
    //         spawnTimer = 4;
    //         EnemyManager.SpawnRow(GameState.gameObjects, Constants.screenWidth, 100, 100);
    //     }
    //     // check collisions
    //     collisionManager.performCollisionDetection(GameState.gameObjects);

    //     // remove inactive objects
    //     CullInactiveObjects();
    //     // repeat input for pressed keys
    //     SL.inputHandler.fireHeldArrowKeys();
    // }

    // public class GamePanel extends JPanel {

    // GamePanel(Window window) {
    //     window.add(this);
    // }

    // @Override
    // protected void paintComponent(Graphics g) {

    //     super.paintComponent(g);
    //     // Clear the screen
    //     g.setColor(java.awt.Color.BLACK);
    //     g.fillRect(0, 0, getWidth(), getHeight());
    //     ObjectRenderer.render(g, GameState.gameObjects);
    // }
}
    

