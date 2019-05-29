import javafx.scene.media.AudioClip;

/**
 * The Sound class
 * This class stores sound media
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class Sound {
    private AudioClip ac;

    public Sound (String path) {
        ac = new AudioClip(this.getClass().getResource(path).toExternalForm());
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
     * This method loops the sound.
     */
    public void loop () {
        ac.setCycleCount(AudioClip.INDEFINITE);
    }
}
