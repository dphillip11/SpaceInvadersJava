package com.flappy.game;

import javax.swing.JFrame;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		//display splash screen
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showSplashScreen();
            }
        });
	}

	public static void showSplashScreen() {
        JFrame splashFrame = new JFrame("Splash Screen");
        splashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splashFrame.setSize(1024, 720);

        // Create the start screen and add it to the splash frame
        StartScreen startScreen = new StartScreen();
        splashFrame.add(startScreen);

        splashFrame.setVisible(true);

        // Start the game loop when the start button is clicked
        startScreen.setStartButtonListener(new StartScreen.StartButtonListener() {
            @Override
            public void onStart() {
                splashFrame.setVisible(false);
                startGame();
            }
        });
    }

	public static void startGame() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameLoop game = new GameLoop();

				//create a new thread to keep the GUI responsive while the game runs
				Thread t = new Thread() {
					public void run() {
						game.start();
					}
				};
				t.start();
			}
		});
	}

	public static void stopGame()
	{
		System.exit(0);
	}
}



