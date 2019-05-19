import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class Game extends Window {
    int score;


    public Game (Stage stg) {
        super(stg, "Lake Ontario");
        score = 1000;
    }

    public int getScore() throws Exception {
        display();
        showAndWait();
        refresh();
        return score;
    }

    public void display () {
        Image ontarioBackground = new Image( "elements/game/ontarioBack.png" );
        Image ontarioToronto = new Image( "elements/game/ontarioToronto.png" );
        Image lake = new Image( "elements/game/lake.png" );
        LogLine ontarioLogImg = new LogLine ("elements/game/ontarioLogLine.png");
        ImageView ontarioLogLine = new ImageView(ontarioLogImg);

        Water w = new Water(1);
        Music m = new Music("src/elements/oxfordComma.wav");

        drawImage( ontarioLogLine, 0, w.getYValue()-30 );

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

                Rectangle2D viewportRect = new Rectangle2D((int)(t*40), 0, 1000+(int)(t*40), 75);
                ontarioLogLine.setViewport(viewportRect);
                refresh();
                drawImage( ontarioLogLine, 0, w.getYValue()-370 );
            }
        }.start();
    }

}
