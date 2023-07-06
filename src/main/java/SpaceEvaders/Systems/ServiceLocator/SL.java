package SpaceEvaders.Systems.ServiceLocator;

import SpaceEvaders.Systems.AudioManager.AudioManager;
import SpaceEvaders.Systems.EventsSystem.EventHandler;
import SpaceEvaders.Systems.InputHandler.InputHandler;
import SpaceEvaders.Systems.StateMachine.StateMachine;
import SpaceEvaders.UI.Window;

public class SL {
    public static final EventHandler eventHandler = new EventHandler();
    public static final InputHandler inputHandler = new InputHandler();
    public static final AudioManager audioManager = new AudioManager();
    public static final StateMachine stateMachine = new StateMachine();
    public static final Window window = new Window("Space Evaders");

    private SL() {
        // Private constructor to prevent instantiation
    }

    public static void init() {
        eventHandler.init();
        inputHandler.init();
        eventHandler.addListener(audioManager);
    }
}

