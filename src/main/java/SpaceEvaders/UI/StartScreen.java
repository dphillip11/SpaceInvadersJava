package SpaceEvaders.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SpaceEvaders.Application;
import SpaceEvaders.Utilities.FontManager;


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
        titleLabel1.setFont(FontManager.getCustomFont(100f));
        titleLabel1.setForeground(Color.WHITE);
        titleLabel2 = new JLabel("Evaders");
        titleLabel2.setFont(FontManager.getCustomFont(100f));
        titleLabel2.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.add(titleLabel1);
        titlePanel.add(titleLabel2);

        startButton = new JButton("Start");
        startButton.setFont(FontManager.getCustomFont(16f));
        startButton.setPreferredSize(new Dimension(200, 80));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               SL.stateMachine.changeState(new PlayState());
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(FontManager.getCustomFont(16f));
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
         JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.add(titleLabel1);
        titlePanel.add(titleLabel2);

        // Add the components to the frame
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    
}
