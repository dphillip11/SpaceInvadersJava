package SpaceEvaders;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import SpaceEvaders.UI.StartScreen;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//display splash screen
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StartScreen();
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



