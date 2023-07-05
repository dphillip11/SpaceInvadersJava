package SpaceEvaders.Systems.EventsSystem;

public interface EventListener {
    void onEvent(EventType event, Object... args);
}
