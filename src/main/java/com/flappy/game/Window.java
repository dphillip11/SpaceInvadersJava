package com.flappy.game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

    
public class Window extends JFrame implements KeyListener {
    public InputHandler inputHandler = new InputHandler();
    private boolean[] keys;

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(this);
        setVisible(true);

         keys = new boolean[256];
        Arrays.fill(keys, false);
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
            dispose(); // Close the window
        } else if (keyCode == KeyEvent.VK_UP) {
            inputHandler.dispatch(Input.UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            inputHandler.dispatch(Input.DOWN);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            inputHandler.dispatch(Input.LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            inputHandler.dispatch(Input.RIGHT);
        } else if (keyCode == KeyEvent.VK_SPACE) {
            inputHandler.dispatch(Input.SPACE);
        }
    }

    public void fireHeldArrowKeys() {
        if (keys[KeyEvent.VK_UP]) {
            inputHandler.dispatch(Input.UP);
        }
        if (keys[KeyEvent.VK_DOWN]) {
            inputHandler.dispatch(Input.DOWN);
        }
        if (keys[KeyEvent.VK_LEFT]) {
            inputHandler.dispatch(Input.LEFT);
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            inputHandler.dispatch(Input.RIGHT);
        }
    }

    
    public Point convertToScreenPos(float x, float y) {
        int pixelX = (int) (x * getWidth());
        int pixelY = (int) (y * getHeight());
        return new Point(pixelX, pixelY);
    }


}
