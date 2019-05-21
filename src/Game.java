import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Game class
 * This class represents the window that has all the graphics for the actual Waterworks game.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.20.2019
 */
public class Game extends Window {
    /**
     * The user's current level score.
     */
    int score;

    /**
     * The level names.
     */
    static String[] names = {"Lake Ontario", "Lake Erie", "Lake Superior"};

    /**
     * The current level selected.
     */
    int level;

    /**
     * @param stg The JavaFX Stage to display to.
     * @param lvl The selected game level.
     */
    public Game (Stage stg, int lvl) {
        super(stg, names [lvl - 1]);
        score = 1000;
        level = lvl;
    }

    /**
     * @return The user's score at the end of the game.
     */
    public int getScore() {
        display();
        showAndWait();
        refresh();
        return score;
    }

    /**
     * This method displays all the graphics for the Game window. It displays appropriate images, depending on the level being played.
     */
    public void display () {
        Image lakeBackground;
        Image cityBack;
        Image lake;
        ImageView logImg;
        LogLine logLine;
        GameChar avatarImg = (GameChar)(Resources.get("avatarImg"));
        ImageView avatar = (ImageView)(Resources.get("avatar"));

        Water w = new Water(1);
        Music m = (Music)(Resources.get("oxfordComma"));


        avatar.setPreserveRatio(true);
        avatar.setFitHeight(260);

        if (level == 1)
        {
            lakeBackground = (Image)(Resources.get("ontarioBack"));
            cityBack = (Image)(Resources.get("ontarioToronto"));
            lake = (Image)(Resources.get("ontarioLake"));
            logImg = (ImageView)(Resources.get("ontarioLogImg"));
            logLine = (LogLine)(Resources.get("ontarioLogLine"));
        }
        else if (level == 2)
        {
            lakeBackground = (Image)(Resources.get("ontarioBack"));
            cityBack = (Image)(Resources.get("ontarioToronto"));
            lake = (Image)(Resources.get("ontarioLake"));
            logImg = (ImageView)(Resources.get("ontarioLogImg"));
            logLine = (LogLine)(Resources.get("ontarioLogLine"));
        }
        else
        {
            lakeBackground = (Image)(Resources.get("ontarioBack"));
            cityBack = (Image)(Resources.get("ontarioToronto"));
            lake = (Image)(Resources.get("ontarioLake"));
            logImg = (ImageView)(Resources.get("ontarioLogImg"));
            logLine = (LogLine)(Resources.get("ontarioLogLine"));
        }

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
                logImg.setViewport(viewportRect);
                refresh();
                drawImage( logImg, 0, w.getYValue()-370 );
                drawImage( avatar, -380, w.getYValue()-490 );

                if (w.getYValue() == 750)
                    stop();
            }
        }.start();
    }

}
