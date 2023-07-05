package com.SpaceEvaders.Systems.EventsSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

abstract class ShootListener implements EventListener {
    @Override
    public void onEvent(EventType event, Object... args) {
        if (event == EventType.SHOOT) {
            onShoot(args);
        }
    }

    public abstract void onShoot(Object... args);
}

public class EventHandler {
    private List<EventListener> eventListeners = Collections.synchronizedList(new ArrayList<>());

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
        synchronized (eventListeners) {
            for (EventListener listener : eventListeners) {
                listener.onEvent(event, args);
            }
        }
    }
}
