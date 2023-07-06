package SpaceEvaders;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static GameLoop game;

	public static void main(String[] args) {
		startGame();
	}

	public static void startGame() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				game = new GameLoop();

				Thread t = new Thread() {
					public void run() {
						game.start();
					}
				};
				t.start();
			}
		});
	}

	public static void exit()
	{
		game.exitLoop();
		System.exit(0);
	}
}



