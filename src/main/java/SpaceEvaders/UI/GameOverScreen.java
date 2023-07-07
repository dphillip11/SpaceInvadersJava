package SpaceEvaders.UI;

import javax.swing.*;

import SpaceEvaders.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.States.PlayState;

public class GameOverScreen extends JPanel {

    JLabel titleLabel1;
    JLabel titleLabel2;
    JLabel scoreLabel;
    JButton startButton;
    JButton exitButton;
    JPanel buttonPanel;
    private int score;

    public GameOverScreen(int score) {
        this.score = score;
        // Create and customize the components
        titleLabel1 = new JLabel("Game");
        titleLabel1.setFont(getCustomFont(100f));
        titleLabel1.setForeground(Color.WHITE);
        titleLabel2 = new JLabel("Over");
        titleLabel2.setFont(getCustomFont(100f));
        titleLabel2.setForeground(Color.WHITE);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(getCustomFont(40f));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        startButton = new JButton("Retry");
        startButton.setFont(getCustomFont(16f));
        startButton.setPreferredSize(new Dimension(200, 80));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               SL.stateMachine.changeState(new PlayState());
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(getCustomFont(16f));
        exitButton.setPreferredSize(new Dimension(200, 80));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.exit();
            }
        });

        // Create a separate panel for the buttons
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
    }

    public void attach(Window frame) {
        // Create a panel to hold the title labels
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.add(titleLabel1);
        titlePanel.add(titleLabel2);

        // Add the components to the frame
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scoreLabel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void detach(Window frame) {
        // Remove the components from the frame
        frame.remove(titleLabel1);
        frame.remove(titleLabel2);
        frame.remove(scoreLabel);
        frame.remove(buttonPanel);
    }

    private Font getCustomFont(float size) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/fonts/space_jaeger.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }
}

