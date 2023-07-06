package SpaceEvaders.States;

import SpaceEvaders.Systems.StateMachine.IState;

public class NullState implements IState {
    @Override
    public State getState()
    {
        return State.NULL;
    }

    @Override
    public void enter(Object... args) {
        System.out.println("NullState: enter");
    }

    @Override
    public void update(double time) {
        System.out.println("NullState: update");
    }

    @Override
    public void exit() {
        System.out.println("NullState: exit");
    }
    
}
