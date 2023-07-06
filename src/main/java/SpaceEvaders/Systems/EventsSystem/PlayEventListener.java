package SpaceEvaders.Systems.EventsSystem;

import SpaceEvaders.States.*;
import SpaceEvaders.Systems.ServiceLocator.*;

public abstract class PlayEventListener implements EventListener {
    public final void onEvent(EventType eventType, Object... args) {
        if (SL.stateMachine.currentState.getState() == State.PLAY) {
            onPlayEvent(eventType, args);
        }
    }
    abstract public void onPlayEvent(EventType eventType, Object... args);
}
