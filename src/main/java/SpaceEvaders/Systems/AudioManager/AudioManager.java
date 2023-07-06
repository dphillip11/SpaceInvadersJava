package SpaceEvaders.Systems.AudioManager;

import SpaceEvaders.Systems.EventsSystem.PlayEventListener;
import SpaceEvaders.CommonState.Variables;
import SpaceEvaders.Systems.EventsSystem.EventType;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioManager extends PlayEventListener {
    private static final String SOUND_FOLDER = "X:/SpaceInvadersJava/src/main/resources/sounds/SFX/";
    private static final Map<EventType, Clip> soundCache = new HashMap<>();

    public AudioManager() {
        init();
    }


    public void init() {
        try {
            cacheSound(EventType.SHOOT, "blipDecay.wav");
            cacheSound(EventType.ENEMY_HIT, "smallBlast.wav");
            cacheSound(EventType.ENEMY_DESTROYED, "thud.wav");
            cacheSound(EventType.PLAYER_HIT, "hitHurt.wav");
            cacheSound(EventType.PLAYER_DESTROYED, "gameOver.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void cacheSound(EventType event, String soundFileName) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File soundFile = new File(SOUND_FOLDER + soundFileName);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        soundCache.put(event, clip);
    }

    @Override
    public void onPlayEvent(EventType eventType, Object... args) {
        String soundFileName = getSoundFileName(eventType);
        if (soundFileName != null) {
            playSound(eventType, Variables.SFX_volume);
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

    private void playSound(EventType event, float volume) {
        try {
            Clip clip = getSoundClip(event);
            if (clip != null) {
                // Set volume control
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = gainControl.getMaximum() - gainControl.getMinimum();
                float gain = (range * volume) + gainControl.getMinimum();
                gainControl.setValue(gain);

                clip.setFramePosition(0); // Rewind the clip to the beginning
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Clip getSoundClip(EventType event) {
        return soundCache.get(event);
    }
}
