import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Game extends Window {

    public Game (Stage stg) {
        super(stg, "Lake Ontario");
    }

    public void display () {
        Image ontarioBackground = new Image( "elements/game/ontarioBack.png" );
        Image ontarioToronto = new Image( "elements/game/ontarioToronto.png" );
        Image lake = new Image( "elements/game/lake.png" );
        Image ontarioLogLine = new Image( "elements/game/ontarioLogLine.png" );

        Water w = new Water(1);
        Music m = new Music("src/elements/oxfordComma.wav");

        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                m.play();
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                drawImage( ontarioBackground, 0, 0 );
                drawImage( ontarioToronto, 480-(int)(t*3), 245 );
                drawImage( lake, 1, w.getYValue() );
                drawImage( ontarioLogLine, -(int)(t*40), w.getYValue()-30 );
            }
        }.start();
    }

}
