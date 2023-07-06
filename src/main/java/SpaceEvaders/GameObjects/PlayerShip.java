package SpaceEvaders.GameObjects;

import java.awt.Toolkit;

import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.CommonState.GameState;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.EventsSystem.EventType;
import SpaceEvaders.Systems.InputHandler.Input;
import SpaceEvaders.Systems.InputHandler.InputListener;
import SpaceEvaders.Utilities.Vector2;

public class PlayerShip extends Spaceship implements InputListener {

    public PlayerShip()
    {
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/spaceship.png"));
        radius = Variables.playerSize;
        type = ObjectType.PLAYER;
        SL.inputHandler.addListener(this);
        setHealth(Variables.maxPlayerHealth);
    }

    @Override
    public void die() {
        SL.eventHandler.notify(EventType.PLAYER_DESTROYED);
        setActive(false);
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
        setVelocity(new Vector2(0,0));
    } //

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
            float offsetY = Variables.playerBulletRadius.y + radius.y;
            shoot(new Vector2(0, -Variables.playerBulletSpeed), position.subtract(new Vector2(0,offsetY)), Variables.playerBulletRadius, true);
        }
    }
}
