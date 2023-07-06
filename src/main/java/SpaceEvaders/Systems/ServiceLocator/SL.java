package SpaceEvaders.Systems.ServiceLocator;

import SpaceEvaders.Systems.AudioManager.AudioManager;
import SpaceEvaders.Systems.BulletManager.BulletManager;
import SpaceEvaders.Systems.EventsSystem.EventHandler;
import SpaceEvaders.Systems.InputHandler.InputHandler;

public class SL {
    public static final EventHandler eventHandler = new EventHandler();
    public static final InputHandler inputHandler = new InputHandler();
    public static final BulletManager bulletManager = new BulletManager();
    public static final AudioManager audioManager = new AudioManager();

    private SL() {
        // Private constructor to prevent instantiation
    }

    public static void init() {
        eventHandler.init();
        inputHandler.init();
        eventHandler.addListener(bulletManager);
        eventHandler.addListener(audioManager);
    }
}

