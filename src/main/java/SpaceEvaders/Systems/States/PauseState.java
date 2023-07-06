package SpaceEvaders.Systems.States;

import SpaceEvaders.Systems.StateMachine.IState;

public class PauseState implements IState {
        
        @Override
        public State getState()
        {
            return State.PAUSE;
        }

        @Override
        public void enter(Object... args) {
            System.out.println("PauseState: enter");
        }
    
        @Override
        public void update(double time) {
        }
    
        @Override
        public void exit() {
            System.out.println("PauseState: exit");
        }
    
}
