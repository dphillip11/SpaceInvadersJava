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
        System.out.println("SplashState: enter");
        SL.window.dispose();
        SL.window = new Window("SplashWindow");
        startScreen.attach(SL.window);
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void exit() {
        System.out.println("SplashState: exit");
        startScreen.detach(SL.window);
        SL.window.dispose();
        SL.window = new Window("GameWindow");
    }
    
}
