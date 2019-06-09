import javafx.scene.media.AudioClip;

import java.util.ArrayList;

/**
 * The Sound class
 * This class stores sound media, and allows specific AudioClips to be played, stopped, and looped. It also allows the stopping of all AudioClips created with this class, due to its static ArrayList all and static method stopAll().
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * <h2>Total Time Spent: 1.25 hours</h2>
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 6 06.09.2019
 *
 * <pre>
 * Version History:
 * May 18:
 * Vansh created the class. -- 0.25 hours
 * May 27:
 * Vansh created constructor, play method and stop method. -- 0.5 hour
 * June 5:
 * Vansh created static AudioClip ArrayList all to store all AudioClips made by the class, and static stopAll method to stop all Sounds. -- 0.5 hours
 * </pre>
 */
public class Sound {
    /**
     * This stores the AudioClip itself, at the path specified in the constructor.
     */
    private final AudioClip ac;


    /**
     * This stores a static List of all AudioClips created from this class.
     */
    private static final ArrayList<AudioClip> all = new ArrayList<AudioClip>();

    /**
     * This is the class constructor. It sets the global AudioClip to the Sound at the specified path, then adds it to the static all ArrayList.
     * @param path The path of the AudioClip.
     */
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

    /**
     * This static method stops all AudioClips created with this class, by looping through the static ArrayList all, and calling stop() on each element.
     */
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
