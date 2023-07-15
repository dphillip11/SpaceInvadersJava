package SpaceEvaders.States;

import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.UI.StartScreen;

public class SplashState implements IState{

    private StartScreen startScreen = new StartScreen();

    @Override
    public State getState()
    {
        return State.SPLASH;
    }
    
    @Override
    public void enter(Object... args) {
        SL.changeWindow("Splash");
        startScreen.attach(SL.window);
    }

    @Override
    public void update(double time) {
        
    }

    @Override
    public void exit() {
        SL.changeWindow("gameWindow");
    }
    
}
