package SpaceEvaders.Systems.StateMachine;

import SpaceEvaders.States.State;

public interface IState {
    State getState();
    void enter(Object... args);
    void update(double time);
    void exit();
}
