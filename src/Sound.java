import javafx.scene.media.AudioClip;

import java.util.ArrayList;

/**
 * The Sound class
 * This class stores sound media
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class Sound {
    private AudioClip ac;
    private static ArrayList<AudioClip> all = new ArrayList<AudioClip>();

    public Sound (String path) {
        ac = new AudioClip(this.getClass().getResource(path).toExternalForm());
        all.add(ac);
    }

    /**
     * This method plays the sound.
     */
    public void play () {
        if (!ac.isPlaying())
            ac.play();
    }

    /**
     * This method stops the sound.
     */
    public void stop () {
        ac.stop();
    }

    public static void stopAll () {
        for (AudioClip a : all)
            a.stop();
    }

    /**
     * This method loops the sound.
     */
    public void loop () {
        ac.setCycleCount(AudioClip.INDEFINITE);
    }
}
