package SpaceEvaders.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.sound.midi.SysexMessage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.Systems.RenderingSystem.ObjectRenderer;
import SpaceEvaders.States.PlayState;
//import image utils
import SpaceEvaders.Utilities.ImageUtils;

public class PlayPanel extends JPanel {

    private PlayState m_playState;
    private Image background;

    public PlayPanel(PlayState playState) {
        m_playState = playState;
        background = new ImageIcon("X:/SpaceInvadersJava/src/main/resources/images/space.jpg").getImage();
        float scale = (float) Constants.screenWidth / background.getWidth(null);
        background = ImageUtils.scaleImage(background, Constants.screenWidth, (int)(background.getHeight(null) * scale));
        background = ImageUtils.darkenImage(background, 0.3f);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the scrolling offset
        float offsetY = m_playState.time * Constants.backgroundScrollSpeed;
        offsetY = offsetY % (background.getHeight(null));
 
        // Draw the scrolled scaled and darkened background image
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(background, 0, -background.getHeight(null) + (int)offsetY, null);
        g2d.drawImage(background, 0, (int)offsetY, null);
        g2d.dispose();

        // Draw game objects
        ObjectRenderer.render(g, m_playState.gameObjects);

        // Display the score in the top left of the gamePanel
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + m_playState.score, 10, 20);

        // Repaint the panel
        repaint();
    }

    
}
