package SpaceEvaders.Systems.EnemyManager;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import SpaceEvaders.Builder.SpaceshipBuilder;
import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.CommonState.EnemyShipPresets;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.GameObjects.Spaceship;
import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.Utilities.Vector2;


public class EnemyManager {

    SpaceshipBuilder spaceshipBuilder; 

    EnemyManager() {
        spaceshipBuilder = new SpaceshipBuilder();
        spaceshipBuilder.addPreset(EnemyShipPresets.variant0(), Variables.incidenceVariant0);
        spaceshipBuilder.addPreset(EnemyShipPresets.variant1(), Variables.incidenceVariant1);
        spaceshipBuilder.addPreset(EnemyShipPresets.variant2(), Variables.incidenceVariant2);
        spaceshipBuilder.addPreset(EnemyShipPresets.variant3(), Variables.incidenceVariant3);
        spaceshipBuilder.addPreset(EnemyShipPresets.variant4(), Variables.incidenceVariant4);
        spaceshipBuilder.addPreset(EnemyShipPresets.variant5(), Variables.incidenceVariant5);
    }

    static public void SpawnRow(List<GameObject> gameObjects, int screen_width) {
        //set random x speed
        int max_possible = screen_width / Variables.cellSize;
        int num_enemies = Math.max(1, (int) (Math.random() * max_possible));
        //calculate spacing between enemies
        int spacing = screen_width / num_enemies;
        //calculate starting position
        int start = (int) (Math.random() * spacing);
        //create enemies
        for (int i = 0; i < num_enemies; i++) {
            gameObjects.add(spaceshipBuilder.newShip(new Vector2(0, 0)));
            //set enemy velocity
            gameObjects.get(gameObjects.size() - 1)
                    .setPosition(new Vector2(start + i * spacing, Variables.spawnPosY));
            if (Math.random() < Variables.incidenceVariant1)
            {
                EnemyShip enemyShip = (EnemyShip)gameObjects.get(gameObjects.size() - 1);
                enemyShip.variant = 1;
                enemyShip.setRadius(Variables.enemySizeVariant1);
                enemyShip.setVelocity(new Vector2(0, Variables.enemySpeedVariant1));
                enemyShip.setHealth(Variables.enemyHealthVariant1);
            }
            else if (i % 2 == 0)
            {
                //faster group
                gameObjects.get(gameObjects.size() - 1).setVelocity(new Vector2(0, Variables.enemySpeedVariant0 * 2));
            } 
            else
            {
                gameObjects.get(gameObjects.size() - 1).setVelocity(new Vector2(0, Variables.enemySpeedVariant0));
            }
            
        }
    }
    
   static public void UpdateEnemyVelocities(List<GameObject> gameObjects, Vector2 player_position) {
    // Create a map to store the lists of ships in each row
    Map<Integer, List<Spaceship>> rowShipMap = new HashMap<>();

    // Iterate through all the game objects
    for (GameObject gameObject : gameObjects) {
        // Check if the object is an EnemyShip
        if (gameObject instanceof Spaceship) {
            Spaceship enemyShip = (Spaceship) gameObject;
            int rowY = (int)enemyShip.getPosition().y / Variables.evasionRange;
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
        //calculate total width of ships in row
        int totalWidth = 0;
        for (Spaceship enemyShip : shipList) {
            totalWidth += enemyShip.getRadius().x * 2;
        }
        int totalSpacing = (int) Constants.screenWidth - totalWidth;
        int averageSpacing = totalSpacing / (numShips + 1);
        totalWidth = 0;

        for (int i = 0; i < numShips; i++) {
            Spaceship spaceShip = shipList.get(i);
            spaceShip.updatePowerupStatus(1/Constants.targetFPS);
            if (spaceShip instanceof EnemyShip) {
                EnemyShip enemyShip = (EnemyShip) spaceShip;              
                // Calculate the target x position for the ship in the current row
                float targetX = (i + 1) * averageSpacing + totalWidth + enemyShip.getRadius().x;
                totalWidth += enemyShip.getRadius().x * 2;
            
                if (enemyShip.variant == 1)
                {
                    float lowerX = i > 0 ? shipList.get(i - 1).getMaxPos().x : 0;
                    float upperX = i < numShips - 1 ? shipList.get(i + 1).getMinPos().x : Constants.screenWidth;
                    targetX = (lowerX + upperX) / 2;
                    if (enemyShip.hasPowerup || Math.abs(targetX - player_position.x) < 50 && Math.random() < Variables.shootRateVariant1) {
                        enemyShip.shoot();
                    }
                }
                else
                {
                    if (enemyShip.hasPowerup || Math.abs(enemyShip.getPosition().x - player_position.x) < 50 && enemyShip.getPosition().y < player_position.y && Math.random() < Variables.shootRateVariant0) {
                        enemyShip.shoot();
                    }
                }
            
                // Calculate the new x velocity based on the target x position
                Vector2 newVelocity = new Vector2(0, enemyShip.getVelocity().y);
                if (targetX > enemyShip.getPosition().x + 5) {
                    newVelocity.x = enemyShip.getVelocity().y;
                } else if (targetX < enemyShip.getPosition().x - 5) {
                    newVelocity.x = -enemyShip.getVelocity().y;
                }
                else {
                    newVelocity.x = 0;
                }
                
                enemyShip.setVelocity(newVelocity);
            }

        }
    }
}








}
