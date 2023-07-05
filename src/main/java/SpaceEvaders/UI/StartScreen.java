package SpaceEvaders.UI;
import SpaceEvaders.Application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel {
    private JButton startButton;
    private JButton exitButton;
    private StartButtonListener startButtonListener;

    public StartScreen() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Space Evaders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (startButtonListener != null) {
                    startButtonListener.onStart();
                }
            }
        });

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Stop the game loop and exit the application
                Application.stopGame();
                SwingUtilities.getWindowAncestor(StartScreen.this).dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void setStartButtonListener(StartButtonListener listener) {
        this.startButtonListener = listener;
    }

    public interface StartButtonListener {
        void onStart();
    }
}
