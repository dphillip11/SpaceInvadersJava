package SpaceEvaders.UI;

import javax.swing.*;
import SpaceEvaders.GameState.Constants;
import SpaceEvaders.GameState.GameState;
    
public class Window extends JFrame{
  

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((int)Constants.screenWidth, Constants.screenHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addKeyListener(GameState.inputHandler);
    }


}
