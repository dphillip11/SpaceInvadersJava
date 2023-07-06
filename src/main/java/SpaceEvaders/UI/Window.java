package SpaceEvaders.UI;

import javax.swing.*;
import java.awt.*;
import SpaceEvaders.GameState.Constants;
import SpaceEvaders.Systems.ServiceLocator.SL;
    
public class Window extends JFrame{
  

    public Window(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((int)Constants.screenWidth, Constants.screenHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addKeyListener(SL.inputHandler);
        //setup window
        this.setVisible(true);

        // Get the default graphics device
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Check if fullscreen is supported
        if (graphicsDevice.isFullScreenSupported()) {
            // Set the window to fullscreen
            graphicsDevice.setFullScreenWindow(this);
        }

        Constants.screenHeight = this.getHeight();
        Constants.screenWidth = this.getWidth();
    }


}
