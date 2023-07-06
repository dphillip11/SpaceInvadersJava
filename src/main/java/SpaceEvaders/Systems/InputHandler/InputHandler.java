package SpaceEvaders.Systems.InputHandler;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private boolean[] keys = new boolean[256];
    private List<InputListener> listeners = new ArrayList<>();

    public void init()
    {
        //clear listeners
        listeners.clear();
        Arrays.fill(keys, false);
    }
    
    public void addListener(InputListener listener) {
        listeners.add(listener);
    }

    public void dispatch(Input input) {
        for (InputListener listener : listeners) {
            listener.onKeyPressed(input);
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            if (!keys[keyCode]) {
                keys[keyCode] = true;
                handleKeyInput(keyCode);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    private void handleKeyInput(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            dispatch(Input.ESCAPE);
        } else if (keyCode == KeyEvent.VK_UP) {
            dispatch(Input.UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            dispatch(Input.DOWN);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            dispatch(Input.LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            dispatch(Input.RIGHT);
        } else if (keyCode == KeyEvent.VK_SPACE) {
            dispatch(Input.SPACE);
        }
        else if (keyCode == KeyEvent.VK_P) {
            dispatch(Input.PAUSE);
        }
    }

    public void fireHeldArrowKeys() {
        if (keys[KeyEvent.VK_UP]) {
            dispatch(Input.UP);
        }
        if (keys[KeyEvent.VK_DOWN]) {
            dispatch(Input.DOWN);
        }
        if (keys[KeyEvent.VK_LEFT]) {
            dispatch(Input.LEFT);
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            dispatch(Input.RIGHT);
        }
    }
}
