import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The Game class
 * This class represents the window that has all the graphics for the actual Waterworks game.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class Game extends Window {
    /**
     * The user's current level score.
     */
    private int score;

    /**
     * The level names.
     */
    private static String[] names = {"Lake Ontario", "Lake Erie", "Lake Superior"};

    /**
     * The current level selected.
     */
    private int level;
    int jumpY;
    int jumpX;
    private boolean jumping;
    private double jumpStart;
    private double jumpStop;
    private double t = 0;
    private ImageView frame;
    private ImageView lastFrame;
    private boolean logTouched;
    private boolean falling;
    private int startX;

    public int endStatus = 0;


    /**
     * @param stg The JavaFX Stage to display to.
     * @param lvl The selected game level.
     */
    public Game (Stage stg, int lvl) {
        super(stg, names [lvl - 1]);
        score = 1000;
        level = lvl;
        jumpY = 0;
        jumpX = 0;
        jumping = false;
        jumpStart = 0;
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


    public void lose() {
        Image back = (Image)(Resources.get("loseBack"));
        Image title = (Image)(Resources.get("loseTitle"));
        ImageView menu = (ImageView)(Resources.get("loseMenu"));
        ImageView tryAgain = (ImageView)(Resources.get("loseTryAgain"));
        Sound click = (Sound)(Resources.get("click"));

        // Listener for MouseClick
        menu.setOnMouseClicked(e -> {
            click.play();
            hideStage();
        });
        tryAgain.setOnMouseClicked(e -> {
            click.play();
            endStatus = 1;
            hideStage();
        });
        // Listener for MouseEnter
        menu.setOnMouseEntered(e -> {
            setCursor(1);
        });
        tryAgain.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listener for MouseExit
        menu.setOnMouseExited(e -> {
            setCursor(0);
        });
        tryAgain.setOnMouseExited(e -> {
            setCursor(0);
        });

        drawImage(back, 0, 0);
        drawImage(title, 130, 260);
        drawImage(menu, -390, 325);
        drawImage(tryAgain, 310, 325);
    }


    public void win() {
        Image back = (Image)(Resources.get("winBack"));
        Image title = (Image)(Resources.get("winTitle"));
        Image score = (Image)(Resources.get("winScore"));
        ImageView menu = (ImageView)(Resources.get("winMenu"));
        ImageView nextLevel = (ImageView)(Resources.get("winNextLevel"));
        Sound click = (Sound)(Resources.get("click"));

        // Listener for MouseClick
        menu.setOnMouseClicked(e -> {
            click.play();
            hideStage();
        });
        nextLevel.setOnMouseClicked(e -> {
            click.play();
            endStatus = 2;
            hideStage();
        });
        // Listener for MouseEnter
        menu.setOnMouseEntered(e -> {
            setCursor(1);
        });
        nextLevel.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listener for MouseExit
        menu.setOnMouseExited(e -> {
            setCursor(0);
        });
        nextLevel.setOnMouseExited(e -> {
            setCursor(0);
        });

        drawImage(back, 0, 0);
        drawImage(title, 155, 200);
        drawImage(score, 225, 400);
        drawImage(menu, -390, 325);
        if (level < 3)
            drawImage(nextLevel, 310, 325);
    }


    /**
     * This method displays all the graphics for the Game window. It displays appropriate images, depending on the level being played.
     */
    public void display () {
        ArrayList<String> input = new ArrayList<String>();

        Image lakeBackground;
        Image dirtBack = (Image)(Resources.get("dirtBack"));
        Image cityBack;
        Image lake;
        ImageView logImg;
        LogLine logLine;
        GameChar avatarImg = (GameChar)(Resources.get("avatarImg"));
        ImageView avatar = (ImageView)(Resources.get("avatar"));
        AnimatedImageView walking = (AnimatedImageView)(Resources.get("walking"));
        ImageView menuBtn = (ImageView)(Resources.get("menuBtn"));
        Sound click = (Sound)(Resources.get("click"));

        Water w = new Water(1);

        // Listener for MouseClick
        menuBtn.setOnMouseClicked(e -> {
            click.play();
            endStatus = -1;
            hideStage();
        });
        // Listener for MouseEnter
        menuBtn.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listener for MouseExit
        menuBtn.setOnMouseExited(e -> {
            setCursor(0);
        });


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
            lakeBackground = (Image)(Resources.get("erieBack"));
            cityBack = (Image)(Resources.get("erieCabin"));
            lake = (Image)(Resources.get("erieLake"));
            logImg = (ImageView)(Resources.get("erieLogImg"));
            logLine = (LogLine)(Resources.get("erieLogLine"));
        }
        else
        {
            lakeBackground = (Image)(Resources.get("superiorBack"));
            cityBack = (Image)(Resources.get("superiorTrees"));
            lake = (Image)(Resources.get("superiorLake"));
            logImg = (ImageView)(Resources.get("superiorLogImg"));
            logLine = (LogLine)(Resources.get("superiorLogLine"));
        }

        drawImage(menuBtn, 400, -330);

        getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        getScene().setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                        if (code.equals("SPACE") && jumping)
                            jumpStop = System.nanoTime();
                    }
                });


        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                t = (currentNanoTime - startNanoTime) / 300000000.0;

                //falling = jumpStop < jumpStart && !jumping;

                // background image clears canvas
                drawImage( lakeBackground, 0, 0 );
                if (level == 1)
                    drawImage( cityBack, 480-(int)(t*3), 249 );
                else if (level == 2)
                    drawImage( cityBack, 580-(int)(t*3), 304 );
                else
                    drawImage( cityBack, 480-(int)(t*3), 20 );
                drawImage( dirtBack, 0, 542 );
                drawImage( lake, 1, w.getYValue() );

                Rectangle2D viewportRect = new Rectangle2D((int)(t*40), 0, 1000, 75);
                startX = (int)(t*40);
                logImg.setViewport(viewportRect);
                remove(logImg);
                remove(avatar);
                drawImage( logImg, 0, w.getYValue()-370 );
                //drawImage( avatar, -380, w.getYValue()-490-jumpY );
                remove(lastFrame);
                if (frame != null)
                        lastFrame = frame;
                 frame = walking.getFrame(t);
                drawImage(frame, -380+jumpX, w.getYValue()-490-jumpY);

                remove(lastFrame);
               // avatarImg.setX (-380 + jumpX);

                if (input.contains("SPACE") && !falling) {
                    if (!jumping && jumpY == 0 && (currentNanoTime - jumpStop) / 300000000.0 > 2) {
                        jumping = true;
                        jumpStart = t;
                    }
                }else
                    jumping = false;
                if (jumping)
                    if ((t-jumpStart) <= 2) {
                        if (jumpX < 700)
                            jumpX += 2;
                        jumpY += (int) (3 * (4 - (t - jumpStart)));
                    } else {
                        jumping = false;
                        jumpStop = System.nanoTime();
                    }
                else {
                    if (jumpX > 0 && jumpY == 0)
                        jumpX--;
                    if (jumpY <= 0)
                    {
                        logTouched = avatarImg.isTouchingLog (logLine, startX, jumpX);
                        if (!logTouched)
                            falling = true;
                    }
                   if (falling){
                        jumpY -= 10;
                        //stop();
                    }
                    else if (jumpY > 0)
                        jumpY = Math.max(jumpY - ((int) (5 * ((currentNanoTime - jumpStop) / 300000000.0))), 0);
                    else
                        jumpStart = 0;
                }

                if (w.getYValue() == 750 || jumpY <= -550) {
                    stop();
                    refresh();
                    lose();
                }

                if (endStatus == -1) {
                    stop();
                }
            }
        }.start();
    }

}
