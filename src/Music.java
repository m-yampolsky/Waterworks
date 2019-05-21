import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * The Music class
 * This class represents the music objects that will be played during the program.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.20.2019
 */
public class Music {
    /**
     * The JavaFX Media Object.
     */
    private Media media;

    /**
     * The JavaFX MediaPlayer Object in order to play the Media.
     */
    private MediaPlayer player;

    /**
     * @param path The local path url where the audio file is located.
     */
    public Music (String path) {
        media = new Media(Paths.get(path).toUri().toString());
        player = new MediaPlayer(media);
    }

    /**
     * This method plays the music.
     */
    public void play () {
        player.play();
    }

    /**
     * This method sets the music to loop.
     */
    public void loop () {
        player.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * This method pauses the music.
     */
    public void pause () {
        player.pause();
    }

    /**
     * This method stops the music.
     */
    public void stop () {
        player.stop();
    }
}
