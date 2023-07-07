package SpaceEvaders.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;
import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.Systems.RenderingSystem.ObjectRenderer;
import SpaceEvaders.GameObjects.GameObject;
import SpaceEvaders.States.PlayState;




public class PlayPanel extends JPanel {

    private PlayState.Score score;
    private List<GameObject> gameObjects;

    public PlayPanel(List<GameObject> gameObjects, PlayState.Score score) {
        this.gameObjects = gameObjects;
        this.score = score;
    }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Constants.screenWidth, Constants.screenHeight);
            ObjectRenderer.render(g, gameObjects);

            // Display the score in the top left of the gamePanel
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Score: " + score.value, 10, 20);
        }
    };
