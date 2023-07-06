package SpaceEvaders.States;

import SpaceEvaders.Systems.StateMachine.IState;

public class GameOverState implements IState {

    @Override
    public State getState()
    {
        return State.GAMEOVER;
    }
    
    @Override
    public void enter(Object... args) {
        System.out.println("GameOverState: enter");
    }

    @Override
    public void update(double time) {
        System.out.println("GameOverState: update");
    }

    @Override
    public void exit() {
        System.out.println("GameOverState: exit");
    }
    
}
