package SpaceEvaders.Systems.AudioManager;

import SpaceEvaders.Systems.EventsSystem.PlayEventListener;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.EventsSystem.EventType;
import java.io.File;
import javax.sound.sampled.*;

public class AudioManager extends PlayEventListener {
    private static final String SOUND_FOLDER = "X:/SpaceInvadersJava/src/main/resources/sounds/SFX/";
    
    @Override
    public void onPlayEvent(EventType eventType, Object... args) {
        String soundFileName = getSoundFileName(eventType);
        if (soundFileName != null) {
            playSound(soundFileName, Variables.SFX_volume);
        }
    }
    
    private String getSoundFileName(EventType event) {
        switch (event) {
            case SHOOT:
                return "blipDecay.wav";
            case ENEMY_HIT:
                return "smallBlast.wav";
            case ENEMY_DESTROYED:
                return "thud.wav";
            case PLAYER_HIT:
                return "hitHurt.wav";
            case PLAYER_DESTROYED:
                return "gameOver.wav";   
            default:
                return null;
        }
    }
    
    private void playSound(String soundFileName, float volume) {
        try {
            File soundFile = new File(SOUND_FOLDER + soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Set volume control
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
