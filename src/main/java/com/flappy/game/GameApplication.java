package com.flappy.game;


import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
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
	}


