import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 *
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
     *
     */
    public void play () {
        player.play();
    }

    /**
     *
     */
    public void pause () {
        player.pause();
    }

    /**
     *
     */
    public void stop () {
        player.stop();
    }
}
