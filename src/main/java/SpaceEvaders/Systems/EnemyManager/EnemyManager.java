package SpaceEvaders.Systems.EnemyManager;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.GameObjects.EnemyShip;
import SpaceEvaders.GameObjects.Spaceship;
import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Utilities.Vector2;


public class EnemyManager {
    static public void SpawnRow(List<GameObject> gameObjects, int screen_width, int cellSize, float speed) {
        //set random x speed
        float x_velocity = (float) (Math.random() * 4 * speed - speed * 2);
        int max_possible = screen_width / cellSize;
        int num_enemies = Math.max(1, (int) (Math.random() * max_possible));
        //calculate spacing between enemies
        int spacing = screen_width / num_enemies;
        //calculate starting position
        int start = (int) (Math.random() * spacing);
        //create enemies
        for (int i = 0; i < num_enemies; i++) {
            gameObjects.add(new EnemyShip());
            //set enemy velocity
            gameObjects.get(gameObjects.size() - 1)
                    .setPosition(new Vector2(start + i * spacing, -Variables.enemySize.y * 2));
            if (Math.random() < 0.03)
            {
                EnemyShip enemyShip = (EnemyShip)gameObjects.get(gameObjects.size() - 1);
                enemyShip.variant = 1;
                enemyShip.setRadius(enemyShip.getRadius().multiply(2));
                enemyShip.setVelocity(new Vector2(x_velocity, speed * 0.75f));
            }
            else if (i % 2 == 0)
            {
                gameObjects.get(gameObjects.size() - 1).setVelocity(new Vector2(x_velocity, speed));
            } 
            else
            {
                gameObjects.get(gameObjects.size() - 1).setVelocity(new Vector2(x_velocity, speed * 1.5f));
            }
            
        }
    }
    
   static public void UpdateEnemyVelocities(List<GameObject> gameObjects) {
    // Create a map to store the lists of ships in each row
    Map<Integer, List<Spaceship>> rowShipMap = new HashMap<>();

    // Iterate through all the game objects
    for (GameObject gameObject : gameObjects) {
        // Check if the object is an EnemyShip
        if (gameObject instanceof Spaceship) {
            Spaceship enemyShip = (Spaceship) gameObject;
            int rowY = (int)(enemyShip.getPosition().y / 300);

            // Get or create the list of ships for the current row
            List<Spaceship> shipList = rowShipMap.getOrDefault(rowY, new ArrayList<>());

            // Add the ship to the list for the current row
            shipList.add(enemyShip);
            rowShipMap.put(rowY, shipList);
        }
    }

    // Iterate through the ships in each row
    for (List<Spaceship> shipList : rowShipMap.values()) {
        // Sort the ships by their x positions
        shipList.sort((ship1, ship2) -> Float.compare(ship1.getPosition().x, ship2.getPosition().x));

        // Calculate the average spacing between ships
        int numShips = shipList.size();
        int totalSpacing = (int) Constants.screenWidth - (numShips * (int) Variables.enemySize.x);
        int averageSpacing = totalSpacing / (numShips + 1);

        // Update the x velocity of ships in the current row
        float currentX = averageSpacing;
        for (Spaceship enemyShip : shipList) {
            // Calculate the target x position for the ship in the current row
            float targetX = currentX + Variables.enemySize.x;

            // Calculate the new x velocity based on the target x position
            Vector2 newVelocity = new Vector2(enemyShip.getVelocity().x, enemyShip.getVelocity().y);
            if (targetX > enemyShip.getPosition().x + 5) {
                newVelocity.x = Variables.enemySpeed;
            } else if (targetX < enemyShip.getPosition().x - 5) {
                newVelocity.x = -Variables.enemySpeed;
            }
            else {
                newVelocity.x = 0;
            }
            if (enemyShip instanceof EnemyShip) {
                 enemyShip.setVelocity(newVelocity);
            }

            currentX += Variables.enemySize.x + averageSpacing;
        }
    }
}








}
