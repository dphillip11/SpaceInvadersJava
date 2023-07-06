package SpaceEvaders.UI;

import javax.swing.*;

import SpaceEvaders.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class StartScreen {

    public StartScreen() {
        Window frame = new Window("Space Evaders, Splash Screen");
        // Create and customize the components
        JLabel titleLabel1 = new JLabel("Space");
        titleLabel1.setFont(getCustomFont(100f));
        titleLabel1.setForeground(Color.WHITE);
        JLabel titleLabel2 = new JLabel("Evaders");
        titleLabel2.setFont(getCustomFont(100f));
        titleLabel2.setForeground(Color.WHITE);

        JButton startButton = new JButton("Start");
        startButton.setFont(getCustomFont(16f));
        startButton.setPreferredSize(new Dimension(200, 80));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.startGame();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(getCustomFont(16f));
        exitButton.setPreferredSize(new Dimension(200, 80));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Create a separate panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Add the components to the frame
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(titleLabel1, BorderLayout.NORTH);
        frame.add(titleLabel2, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
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
