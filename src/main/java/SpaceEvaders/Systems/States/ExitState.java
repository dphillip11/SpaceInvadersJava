package SpaceEvaders.Systems.States;

import SpaceEvaders.Systems.StateMachine.IState;

public class ExitState implements IState {
    
        @Override
        public State getState()
        {
            return State.EXIT;
        }
        
        @Override
        public void enter(Object... args) {
            
        }
    
        @Override
        public void update(double time) {
        }
    
        @Override
        public void exit() {
            System.out.println("ExitState: exit");
        }
    
}
