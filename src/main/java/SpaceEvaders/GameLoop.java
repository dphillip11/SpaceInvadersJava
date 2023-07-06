package SpaceEvaders;

import SpaceEvaders.CommonState.Constants;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.Systems.States.SplashState;
import SpaceEvaders.Systems.States.ExitState;

public class GameLoop{

    public boolean isActive = true;

    public void start() {
        SL.stateMachine.changeState(new SplashState());
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / Constants.targetFPS; // 60 ticks per second
        double delta = 0;

        while (isActive) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                double deltaTime = 0;
                deltaTime = delta / Constants.targetFPS;
                SL.stateMachine.update(deltaTime);
                delta--;
            }
        }
    }

    public void exitLoop() {
        SL.stateMachine.changeState(new ExitState());
        SL.stateMachine.currentState.exit();
        isActive = false;
    }

}
