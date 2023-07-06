package SpaceEvaders.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;

public class PauseScreen extends JPanel {
    private JLabel pausedLabel;

    public PauseScreen() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        pausedLabel = new JLabel("Paused");
        pausedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pausedLabel.setForeground(Color.WHITE);
        pausedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pausedLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(pausedLabel, BorderLayout.CENTER);
    }
}
