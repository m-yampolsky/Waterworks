import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Music {
    private Media media;
    private MediaPlayer player;

    public Music (String path) {
        media = new Media(Paths.get(path).toUri().toString());
        player = new MediaPlayer(media);
    }

    public void play () {
        player.play();
    }

    public void pause () {
        player.pause();
    }

    public void stop () {
        player.stop();
    }
}
