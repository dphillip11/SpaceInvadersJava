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
import SpaceEvaders.GameObjects.Asteroid;
import SpaceEvaders.GameObjects.CollidableObject;
import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.GameObjects.ObjectType;
import SpaceEvaders.Utilities.Vector2;


public class EnemyManager {

    static public void init() {
        SpaceshipBuilder.clear();
        SpaceshipBuilder.addPreset(EnemyShipPresets.variant0(), Variables.incidenceVariant0);
        SpaceshipBuilder.addPreset(EnemyShipPresets.variant1(), Variables.incidenceVariant1);
        SpaceshipBuilder.addPreset(EnemyShipPresets.variant2(), Variables.incidenceVariant2);
        SpaceshipBuilder.addPreset(EnemyShipPresets.variant3(), Variables.incidenceVariant3);
        SpaceshipBuilder.addPreset(EnemyShipPresets.variant4(), Variables.incidenceVariant4);
        SpaceshipBuilder.addPreset(EnemyShipPresets.variant5(), Variables.incidenceVariant5);
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
            gameObjects.add(SpaceshipBuilder.newShip(new Vector2(start + i * spacing, Variables.spawnPosY)));          
        }
    }
    
   static public void UpdateEnemyVelocities(List<GameObject> gameObjects, Vector2 player_position) {
    // Create a map to store the lists of ships in each row
    Map<Integer, List<CollidableObject>> rowShipMap = new HashMap<>();

    // Iterate through all the game objects
    for (GameObject gameObject : gameObjects) {
        // Check if the object is an spaceShip
        if (gameObject instanceof Spaceship || gameObject instanceof Asteroid) {
            CollidableObject object = (CollidableObject) gameObject;
            int rowY = (int)object.getPosition().y / Variables.evasionRange;
            // Get or create the list of ships for the current row
            List<CollidableObject> shipList = rowShipMap.getOrDefault(rowY, new ArrayList<>());

            // Add the ship to the list for the current row
            shipList.add(object);
            rowShipMap.put(rowY, shipList);
        }
    }

    // Iterate through the ships in each row
    for (List<CollidableObject> shipList : rowShipMap.values()) {
        // Sort the ships by their x positions
        shipList.sort((ship1, ship2) -> Float.compare(ship1.getPosition().x, ship2.getPosition().x));

        // Calculate the average spacing between ships
        int numShips = shipList.size();
        //calculate total width of ships in row
        int totalWidth = 0;
        for (CollidableObject enemyShip : shipList) {
            totalWidth += enemyShip.getRadius().x * 2;
        }
        int totalSpacing = (int) Constants.screenWidth - totalWidth;
        int averageSpacing = totalSpacing / (numShips + 1);
        totalWidth = 0;

        for (int i = 0; i < numShips; i++) {
            CollidableObject object = shipList.get(i);
            if (object.getType() == ObjectType.ASTEROID) {
                continue;
            }
            Spaceship spaceShip = (Spaceship) object;
            if (spaceShip.hasPowerup) {
                    spaceShip.shoot();
            }
            if (spaceShip.getType() == ObjectType.ENEMY) {             
                // Calculate the target x position for the ship in the current row
                float targetX = (i + 1) * averageSpacing + totalWidth + spaceShip.getRadius().x;
                totalWidth += spaceShip.getRadius().x * 2;
            
                if (spaceShip.variant % 2 == 0)
                {
                    float lowerX = i > 0 ? shipList.get(i - 1).getMaxPos().x : 0;
                    float upperX = i < numShips - 1 ? shipList.get(i + 1).getMinPos().x : Constants.screenWidth;
                    targetX = (lowerX + upperX) / 2;
                }
                
                if(!spaceShip.hasPowerup && Math.abs(targetX - player_position.x) < 50 ) {
                    spaceShip.shootRandom();
                }
            
                // Calculate the new x velocity based on the target x position
                Vector2 newVelocity = new Vector2(0, spaceShip.getVelocity().y);
                if (targetX > spaceShip.getPosition().x + 5) {
                    newVelocity.x = Math.abs(spaceShip.getVelocity().y);
                } else if (targetX < spaceShip.getPosition().x - 5) {
                    newVelocity.x = -Math.abs(spaceShip.getVelocity().y);
                }
                else {
                    newVelocity.x = 0;
                }
                
                spaceShip.setVelocity(newVelocity);
            }

        }
    }
}








}
