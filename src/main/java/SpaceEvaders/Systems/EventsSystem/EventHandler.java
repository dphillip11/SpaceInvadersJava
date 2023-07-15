package SpaceEvaders.Systems.EventsSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EventHandler {

    private class EventObject {
        public EventType event;
        public Object[] args;

        public EventObject(EventType event, Object... args) {
            this.event = event;
            this.args = args;
        }
    }

    private List<EventListener> eventListeners = Collections.synchronizedList(new ArrayList<>());
    private List<EventObject> eventQueue = Collections.synchronizedList(new ArrayList<>());

    public void init() {
        //clear listeners
        eventListeners.clear();
    }

    public void addListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public void removeListener(EventListener listener) {
        eventListeners.remove(listener);
    }

    public void notify(EventType event, Object... args) {
        eventQueue.add(new EventObject(event, args));
    }

    public void dispatch() {
        List<EventListener> copyListeners = new ArrayList<>(eventListeners);
        List<EventObject> copyQueue = new ArrayList<>(eventQueue);

        for (EventObject event : copyQueue) {
            for (EventListener listener : copyListeners) {
                listener.onEvent(event.event, event.args);
            }
        }
        eventQueue.clear();
    }

    public void clear() {
        eventQueue.clear();
    }
}
