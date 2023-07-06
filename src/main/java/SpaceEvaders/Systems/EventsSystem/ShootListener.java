package SpaceEvaders.Systems.EventsSystem;

import SpaceEvaders.GameState.GameState;

public abstract class ShootListener implements EventListener {
    @Override
    public void onEvent(EventType event, Object... args) {
        if (event == EventType.SHOOT && !GameState.paused) {
            onShoot(args);
        }
    }

    public abstract void onShoot(Object... args);
}
