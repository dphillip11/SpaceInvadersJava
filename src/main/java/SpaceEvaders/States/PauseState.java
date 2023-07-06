package SpaceEvaders.States;

import SpaceEvaders.Systems.StateMachine.IState;
import SpaceEvaders.Systems.InputHandler.InputListener;
import SpaceEvaders.Systems.InputHandler.Input;
import SpaceEvaders.Systems.ServiceLocator.SL;
import SpaceEvaders.UI.PauseScreen;
import SpaceEvaders.Systems.EventsSystem.EventType;

public class PauseState implements IState, InputListener {
    private PlayState playState;
    private PauseScreen pauseScreen = new PauseScreen();

    @Override
    public State getState() {
        return State.PAUSE;
    }

    @Override
    public void enter(Object... args) {
        SL.inputHandler.init();
        SL.inputHandler.addListener(this);
        playState = (PlayState) args[0];
        SL.window.add(pauseScreen);
        pauseScreen.setVisible(true);
    }

    @Override
    public void update(double deltaTime) {
        SL.window.revalidate();
    }

    @Override
    public void exit() {
        SL.window.remove(pauseScreen);
    }

    @Override
    public void onKeyPressed(Input input) {
        if (input == Input.PAUSE) {
            SL.stateMachine.changeState(playState);
            SL.eventHandler.notify(EventType.RESUME);
        }
    }
}
