package SpaceEvaders.Systems.StateMachine;

import SpaceEvaders.States.NullState;

public class 
StateMachine {

    public IState currentState = new NullState();

    public void update(double time) {
        currentState.update(time);
    }

    public void changeState(IState newState, Object... args) {
        currentState.exit();
        currentState = newState;
        currentState.enter(args);
    }

}
