package SpaceEvaders.States;

import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.UI.StartScreen;
import SpaceEvaders.UI.Window;

public class SplashState implements IState{

    private StartScreen startScreen = new StartScreen();

    @Override
    public State getState()
    {
        return State.SPLASH;
    }
    
    @Override
    public void enter(Object... args) {
        SL.window.dispose();
        SL.init();
        SL.window = new Window("SplashWindow");
        startScreen.attach(SL.window);
    }

    @Override
    public void update(double time) {
        
    }

    @Override
    public void exit() {
        startScreen.detach(SL.window);
        SL.window.dispose();
        SL.init();
        SL.window = new Window("GameWindow");
    }
    
}
