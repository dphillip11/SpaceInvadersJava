package SpaceEvaders.UI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen{

    private JFrame frame;
    private JLabel pausedLabel;

    public PauseScreen() {
        frame = new JFrame("Paused");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        pausedLabel = new JLabel("PAUSED", SwingConstants.CENTER);
        pausedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        pausedLabel.setForeground(Color.RED);
        frame.add(pausedLabel, BorderLayout.CENTER);

        Timer timer = new Timer(500, new ActionListener() {
            private boolean isVisible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                pausedLabel.setVisible(isVisible);
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PauseScreen::new);
    }
}
