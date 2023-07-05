package SpaceEvaders.GameObjects;

import java.awt.Toolkit;

import SpaceEvaders.GameState.GameState;
import SpaceEvaders.GameState.Variables;
import SpaceEvaders.Systems.EventsSystem.Input;
import SpaceEvaders.Systems.EventsSystem.InputListener;
import SpaceEvaders.Utilities.Vector2;

public class PlayerShip extends Spaceship implements InputListener {

    public PlayerShip()
    {
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/spaceship.png"));
        radius = Variables.playerSize;
        GameState.inputHandler.addListener(this);
    }

    @Override
    public void onKeyPressed(Input input) {

        if (input == Input.UP) {
            velocity = new Vector2(velocity.x, -Variables.playerSpeed);
        }
        else if (input == Input.DOWN) {
           velocity = new Vector2(velocity.x, Variables.playerSpeed);
        }
        if (input == Input.LEFT) {
            velocity = new Vector2(-Variables.playerSpeed, velocity.y);
        }
        else if (input == Input.RIGHT) {
            velocity = new Vector2(Variables.playerSpeed, velocity.y);
        }
        if (input == Input.SPACE) {
            shoot(new Vector2(0, -Variables.playerBulletSpeed), position.subtract(new Vector2(0, radius.y / 2)), Variables.playerBulletRadius, true);
        }
    }
}
