import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
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
    private ImageView charFrame;
    private ImageView lastCharFrame;
    private ImageView finalDevFrame;
    private ImageView lastFinalDevFrame;
    private boolean logTouched;
    private boolean falling;
    private int startX;
    String name;

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
        name = "";
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


    public void lose( boolean showBack) {
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

        if (showBack) {
            drawImage(back, 0, 0);
            drawImage(title, 130, 260);
        }
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

        Image textField = (Image)(Resources.get("textField"));
        Image nameLabel = (Image)(Resources.get("nameLabel"));
        ImageView saveButton = (ImageView)(Resources.get("saveButton"));


        drawImage (textField, 40, 510);
        drawImage (nameLabel, 60, 530);
        drawImage (saveButton, 352, 185);

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
        AnimatedImageView finalDevice;
        ImageView deviceImg = (ImageView)(Resources.get("ontarioDeviceImg"));
        DeviceLine deviceLine = (DeviceLine)(Resources.get ("ontarioDeviceLine"));
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
            finalDevice = (AnimatedImageView)(Resources.get("finalWasher"));
        }
        else if (level == 2)
        {
            lakeBackground = (Image)(Resources.get("erieBack"));
            cityBack = (Image)(Resources.get("erieCabin"));
            lake = (Image)(Resources.get("erieLake"));
            logImg = (ImageView)(Resources.get("erieLogImg"));
            logLine = (LogLine)(Resources.get("erieLogLine"));
            finalDevice = (AnimatedImageView)(Resources.get("finalTub"));
        }
        else
        {
            lakeBackground = (Image)(Resources.get("superiorBack"));
            cityBack = (Image)(Resources.get("superiorTrees"));
            lake = (Image)(Resources.get("superiorLake"));
            logImg = (ImageView)(Resources.get("superiorLogImg"));
            logLine = (LogLine)(Resources.get("superiorLogLine"));
            finalDevice = (AnimatedImageView)(Resources.get("finalSink"));
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
                if (jumpY < 10) {
                    logTouched = avatarImg.isTouchingLog (logLine, startX, jumpX);
                    if (!logTouched)
                        falling = true;
                }

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
                Rectangle2D viewportRectDevices = new Rectangle2D((int)(t*40), 0, 1000, deviceLine.getHeight());
                startX = (int)(t*40);
                logImg.setViewport(viewportRect);
                deviceImg.setViewport(viewportRectDevices);
                remove(deviceImg);
                remove(logImg);
                remove(avatar);
                drawImage( logImg, 0, w.getYValue()-370 );
                drawImage( deviceImg, 0, w.getYValue()-640);
                if (charFrame != null)
                        lastCharFrame = charFrame;
                 charFrame = walking.getFrame(t);
                drawImage(charFrame, -380+jumpX, w.getYValue()-490-jumpY);
                remove(lastCharFrame);

                if (finalDevFrame != null)
                    lastFinalDevFrame = finalDevFrame;
                finalDevFrame = finalDevice.getFrame(t);
                drawImage(finalDevFrame, 9500-(int)(t*40), w.getYValue()-430);
                remove(lastFinalDevFrame);
                
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
                            jumpX += 3;
                        jumpY += (int) (3 * (4 - (t - jumpStart)));
                    } else {
                        jumping = false;
                        jumpStop = System.nanoTime();
                    }
                else {
                    if (jumpX > 0 && jumpY == 0)
                        jumpX--;
                   if (falling){
                        jumpY -= 10;
                        //stop();
                    }
                    else if (jumpY > 0)
                        jumpY = Math.max(jumpY - ((int) (5 * ((currentNanoTime - jumpStop) / 300000000.0))), 0);
                    else
                        jumpStart = 0;
                }

                //int deviceType = avatarImg.isTouchingDevice(deviceLine, startX, jumpX, walking, jumpY, w.getYValue()-490-jumpY+375);
                int deviceType = avatarImg.isTouchingDevice(deviceLine, startX+jumpX+120-50, w.getYValue()-640+325-jumpY+65, 100, 250);
                if (deviceType == 1)
                    w.changeHeight(1);
                else if (deviceType == -1)
                    w.changeHeight(-1);

                if (-380+jumpX >= 9450-(int)(t*40)) {
                    stop();
                    refresh();
                    win();
                }
                if (w.getYValue() == 750) {
                    stop();
                    refresh();
                    if (level == 1)
                        displayVideo("elements/ontarioLose.mp4");
                    else if (level == 2)
                        displayVideo("elements/erieLose.mp4");
                    else
                        displayVideo("elements/superiorLose.mp4");
                    lose(false);
                }
                if (jumpY <= -550) {
                    stop();
                    refresh();
                    lose(true);
                }

                if (endStatus == -1) {
                    stop();
                }
            }
        }.start();
    }

}
