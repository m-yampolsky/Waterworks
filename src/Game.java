import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class Game extends Window {
    int score;
    static String[] names = {"Lake Ontario", "Lake Erie", "Lake Superior"};
    int level;

    public Game (Stage stg, int lvl) {
        super(stg, names [lvl - 1]);
        score = 1000;
        level = lvl;
    }

    public int getScore() throws Exception {
        display();
        showAndWait();
        refresh();
        return score;
    }

    public void display () {
        Image lakeBackground;
        Image cityBack;
        Image lake = new Image( "elements/game/lake.png" );
        LogLine logImg;
        ImageView logLine;
        GameChar avatarImg = new GameChar ("elements/game/backgroundChar.png" );
        ImageView avatar = new ImageView (avatarImg);
        avatar.setPreserveRatio(true);
        avatar.setFitHeight(260);
        
        if (level == 1)
        {
            lakeBackground = new Image("elements/game/ontarioBack.png");
            cityBack = new Image("elements/game/ontarioToronto.png");
            logImg = new LogLine("elements/game/ontarioLogLine.png");
            logLine = new ImageView(logImg);
        }
        else if (level == 2)
        {
            lakeBackground = new Image("elements/game/ontarioBack.png");
            cityBack = new Image("elements/game/ontarioToronto.png");
            logImg = new LogLine("elements/game/ontarioLogLine.png");
            logLine = new ImageView(logImg);
        }
        else
        {
            lakeBackground = new Image("elements/game/ontarioBack.png");
            cityBack = new Image("elements/game/ontarioToronto.png");
            logImg = new LogLine("elements/game/ontarioLogLine.png");
            logLine = new ImageView(logImg);
        }
        
        Water w = new Water(1);
        Music m = new Music("src/elements/oxfordComma.wav");

         drawImage( logLine, 0, w.getYValue()-30 );
        drawImage (avatar, 0, w.getYValue()-30 );

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
                drawImage( lakeBackground, 0, 0 );
                drawImage( cityBack, 480-(int)(t*3), 245 );
                drawImage( lake, 1, w.getYValue() );

                Rectangle2D viewportRect = new Rectangle2D((int)(t*40), 0, 1000+(int)(t*40), 75);
                logLine.setViewport(viewportRect);
                refresh();
                drawImage( logLine, 0, w.getYValue()-370 );
                drawImage( avatar, -380, w.getYValue()-490 );

                if (w.getYValue() == 750)
                    stop();
            }
        }.start();
    }

}
