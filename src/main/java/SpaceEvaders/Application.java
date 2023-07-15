package SpaceEvaders;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static GameLoop game;

	public static void main(String[] args) {
		startGame();
	}

	public static void startGame() {
		game = new GameLoop();
		game.start();
	}

	public static void exit()
	{
		game.exitLoop();
		System.exit(0);
	}
}



