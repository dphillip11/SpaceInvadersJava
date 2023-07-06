package SpaceEvaders.Systems.States;

import SpaceEvaders.Systems.InputHandler.InputListener;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.Systems.InputHandler.Input;

public class SplashState implements IState, InputListener {

    @Override
    public State getState()
    {
        return State.SPLASH;
    }
    
    @Override
    public void enter(Object... args) {
        System.out.println("SplashState: enter");
        SL.inputHandler.addListener(this);
    }

    @Override
    public void update(double time) {
    }

    @Override
    public void exit() {
        System.out.println("SplashState: exit");
    }

    @Override
    public void onKeyPressed(Input input) {
        System.out.println("SplashState: onKeyPressed");
        if (input == Input.SPACE)
        {
            SL.stateMachine.changeState(new PlayState());
        }
    }
    
}
