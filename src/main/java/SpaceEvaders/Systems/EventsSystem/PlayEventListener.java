package SpaceEvaders.Systems.EventsSystem;

import SpaceEvaders.GameState.GameState;

public abstract class PlayEventListener implements EventListener {
    public final void onEvent(EventType eventType, Object... args) {
        if (!GameState.paused) {
            onPlayEvent(eventType, args);
        }
    }
    abstract public void onPlayEvent(EventType eventType, Object... args);
}
