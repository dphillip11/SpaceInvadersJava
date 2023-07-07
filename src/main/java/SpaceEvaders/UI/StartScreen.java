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

public class StartScreen extends JPanel {

    JLabel titleLabel1;
    JLabel titleLabel2;
    JButton startButton;
    JButton exitButton;
    JPanel buttonPanel;

    public StartScreen() {
        // Create and customize the components
        titleLabel1 = new JLabel("Space");
        titleLabel1.setFont(getCustomFont(100f));
        titleLabel1.setForeground(Color.WHITE);
        titleLabel2 = new JLabel("Evaders");
        titleLabel2.setFont(getCustomFont(100f));
        titleLabel2.setForeground(Color.WHITE);

        startButton = new JButton("Start");
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
        // Add the components to the frame
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(titleLabel1, BorderLayout.NORTH);
        frame.add(titleLabel2, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void detach(Window frame) {
        // Remove the components from the frame
        frame.remove(titleLabel1);
        frame.remove(titleLabel2);
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
