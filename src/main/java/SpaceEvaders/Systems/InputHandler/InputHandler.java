package SpaceEvaders.Systems.InputHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;

public class InputHandler implements KeyListener {
    private boolean[] keys = new boolean[256];
    private List<InputListener> listeners = new ArrayList<>();

    public void init() {
        // Clear listeners
        synchronized (listeners) {
            listeners.clear();
        }
        Arrays.fill(keys, false);
    }

    public void setSwingKeyListener(JFrame frame) {
        frame.addKeyListener(this);
    }

    public void addListener(InputListener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void dispatch(Input input) {
        List<InputListener> copyListeners;
        synchronized (listeners) {
            copyListeners = new ArrayList<>(listeners);
        }

        for (InputListener listener : copyListeners) {
            listener.onKeyPressed(input);
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            synchronized (keys) {
                if (!keys[keyCode]) {
                    keys[keyCode] = true;
                    handleKeyInput(keyCode);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            synchronized (keys) {
                keys[keyCode] = false;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    private void handleKeyInput(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            dispatch(Input.ESCAPE);
        } if (keyCode == KeyEvent.VK_P) {
            dispatch(Input.PAUSE);
        }else if (keyCode == KeyEvent.VK_UP) {
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

