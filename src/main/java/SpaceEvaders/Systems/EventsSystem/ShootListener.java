package SpaceEvaders.Systems.EventsSystem;

import SpaceEvaders.States.*;
import SpaceEvaders.Systems.ServiceLocator.*;

public abstract class ShootListener implements EventListener {
    @Override
    public void onEvent(EventType event, Object... args) {
        if (event == EventType.SHOOT && SL.stateMachine.currentState.getState() == State.PLAY) {
            onShoot(args);
        }
    }

    public abstract void onShoot(Object... args);
}
