package SpaceEvaders.States;

import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.UI.GameOverScreen;
import SpaceEvaders.UI.Window;
import SpaceEvaders.Systems.ServiceLocator.SL;


public class GameOverState implements IState {

    private int score;
    private GameOverScreen gameOverScreen;


    @Override
    public State getState()
    {
        return State.GAMEOVER;
    }
    
    @Override
    public void enter(Object... args) {
        score = (int) args[0];
        gameOverScreen = new GameOverScreen(score);
        SL.window.dispose();
        SL.init();
        SL.window = new Window("GameOver");
        gameOverScreen.attach(SL.window);
    }

    @Override
    public void update(double time) {
    
    }

    @Override
    public void exit() {
        gameOverScreen.detach(SL.window);
        SL.window.dispose();
        SL.init();
        SL.window = new Window("GameWindow");
    }
    
}
