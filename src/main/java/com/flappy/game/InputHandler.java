package com.flappy.game;

import java.util.ArrayList;
import java.util.List;

enum Input {
    UP, DOWN, LEFT, RIGHT, SPACE
}

interface InputListener {
    void onKeyPressed(Input input);
}

public class InputHandler {
    private List<InputListener> listeners = new ArrayList<>();

    public void addListener(InputListener listener) {
        listeners.add(listener);
    }

    public void dispatch(Input input) {
        for (InputListener listener : listeners) {
            listener.onKeyPressed(input);
        }
    }
}
