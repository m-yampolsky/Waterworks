import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import java.io.*;
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
    private int score = 0;

    /**
     * The level names.
     */
    private static String[] names = {"Lake Ontario", "Lake Erie", "Lake Superior"};

    /**
     * The current level selected.
     */
    private int level;
    private int won = 0;
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
    private String name = "          ";
    private int onChar = 0;
    private boolean saved = false;
    private char[] charsName = name.toCharArray();



    public int endStatus = 0;
    final File SCORES_FILE = new File (System.getProperty("user.home") + "/highScoresFile.wtr");


    /**
     * @param stg The JavaFX Stage to display to.
     * @param lvl The selected game level.
     */
    public Game (Stage stg, int lvl) {
        super(stg, names [lvl - 1]);
        score = 0;
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
        if (won > 0)
            win();
        else if (won < 0)
            lose();
        if (won != 0)
            showAndWait();
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
        Image scoreImg = (Image)(Resources.get("winScore"));
        ImageView menu = (ImageView)(Resources.get("winMenu"));
        ImageView nextLevel = (ImageView)(Resources.get("winNextLevel"));
        Sound click = (Sound)(Resources.get("click"));
        ImageView textField = (ImageView)(Resources.get("textField"));
        ImageView nameLabel = (ImageView)(Resources.get("nameLabel"));
        ImageView saveButton = (ImageView)(Resources.get("saveButton"));
        Text output = new Text ();
        ImageView savedMessage = (ImageView)(Resources.get("savedMsg"));
        Text scoreTxt = new Text ("" + score);

        scoreTxt.setFont(new Font ("Consolas", 70));
        scoreTxt.setFill (Color.WHITE);


        // Listener for MouseClick
        menu.setOnMouseClicked(e -> {
            if (!saved){
                name = "USER";
                PrintWriter output1;
                try {
                    output1 = new PrintWriter(new BufferedWriter(new FileWriter(SCORES_FILE, true)));
                    output1.println("level played:" + level);
                    output1.println(name);
                    output1.println(score);
                    output1.close();
                } catch (IOException error) {
                }
            }
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
        saveButton.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listener for MouseExit
        menu.setOnMouseExited(e -> {
            setCursor(0);
        });
        saveButton.setOnMouseExited(e -> {
            setCursor(0);
        });

        drawImage(back, 0, 0);
        drawImage(title, 155, 200);
        drawImage(scoreImg, 225, 400);
        drawImage(menu, -390, 325);
        add (scoreTxt, 90, 60);


        output.setFont(Font.font("Consolas", 50));

        drawImage (textField, 0, 185);
        drawImage (nameLabel, -262, 185);
        drawImage (saveButton, 352, 185);

        saveButton.setOnMouseClicked(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                if (!saved) {
                    if (onChar > 0) {
                        saved = true;
                        remove(textField);
                        remove(nameLabel);
                        remove(saveButton);
                        remove(output);
                        drawImage(savedMessage, 0, 185);

                        nextLevel.setOnMouseExited(e -> {
                            setCursor(0);
                        });
                        nextLevel.setOnMouseEntered(e -> {
                            setCursor(1);
                        });
                        if (level < 3)
                            drawImage(nextLevel, 310, 325);
                        if (!SCORES_FILE.exists()) {
                            try {
                                SCORES_FILE.createNewFile();
                            } catch (IOException e) {
                            }
                        }
                        PrintWriter output;
                        try {
                            output = new PrintWriter(new BufferedWriter(new FileWriter(SCORES_FILE, true)));
                            output.println("level played:" + level);
                            output.println(name);
                            output.println(score);
                            output.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        });


        getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e) {
                        if (!saved) {
                            String code = e.getCode().toString();
                            if ((code.length() == 1 || e.getCode().isDigitKey()) && onChar <= 9) {
                                if (code.contains("DIGIT"))
                                    charsName[onChar] = code.charAt(code.length() - 1);
                                else
                                    charsName[onChar] = code.charAt(0);
                                onChar++;
                                name = new String(charsName);
                                output.setText(name);
                                remove(output);
                                add(output, 85, 185);
                            } else if (code.equals("SPACE") && onChar != 0 && onChar <= 9) {
                                onChar++;
                                output.setText(name);
                                remove(output);
                                add(output, 85, 185);
                            } else if ((code.equals("DELETE") || code.equals("BACK_SPACE")) && onChar != 0) {
                                charsName[onChar - 1] = ' ';
                                onChar--;
                                name = new String(charsName);
                                output.setText(name);
                                remove(output);
                                add(output, 85, 185);
                            }
                        }
                    }
                });


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
        ImageView deviceImg;
        DeviceLine deviceLine;
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
            deviceImg = (ImageView)(Resources.get("ontarioDeviceImg"));
            deviceLine = (DeviceLine)(Resources.get ("ontarioDeviceLine"));
        }
        else if (level == 2)
        {
            lakeBackground = (Image)(Resources.get("erieBack"));
            cityBack = (Image)(Resources.get("erieCabin"));
            lake = (Image)(Resources.get("erieLake"));
            logImg = (ImageView)(Resources.get("erieLogImg"));
            logLine = (LogLine)(Resources.get("erieLogLine"));
            finalDevice = (AnimatedImageView)(Resources.get("finalSink"));
            deviceImg = (ImageView)(Resources.get("erieDeviceImg"));
            deviceLine = (DeviceLine)(Resources.get ("erieDeviceLine"));
        }
        else
        {
            lakeBackground = (Image)(Resources.get("superiorBack"));
            cityBack = (Image)(Resources.get("superiorTrees"));
            lake = (Image)(Resources.get("superiorLake"));
            logImg = (ImageView)(Resources.get("superiorLogImg"));
            logLine = (LogLine)(Resources.get("superiorLogLine"));
            finalDevice = (AnimatedImageView)(Resources.get("finalTub"));
            deviceImg = (ImageView)(Resources.get("superiorDeviceImg"));
            deviceLine = (DeviceLine)(Resources.get ("superiorDeviceLine"));
        }

        drawImage(menuBtn, 400, -330);

        getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
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
                drawImage( deviceImg, 0, w.getYValue()-700);
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
                        score -= 1;
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

                int deviceType = avatarImg.isTouchingDevice(deviceLine, startX+jumpX+120-70, w.getYValue()-640+325-jumpY+65, 120, 220);
                if (deviceType == 1){
                    w.changeHeight(1);
                    score += 7;
                }
                else if (deviceType == -1){
                    w.changeHeight(-1);
                    score -= 7;
                }

                if (-380+jumpX >= 9450-(int)(t*40)) {
                    stop();
                    refresh();
                    MediaPlayer p;
                    if (level == 1)
                        p = displayVideo("elements/ontarioWin.mp4");
                    else if (level == 2)
                        p = displayVideo("elements/erieWin.mp4");
                    else
                        p = displayVideo("elements/superiorWin.mp4");
                    won = 1;
                    score += w.getHeight();
                    p.setOnEndOfMedia(() -> {
                        hideStage();
                    });
                }
                if (w.getYValue() == 750) {
                    stop();
                    refresh();
                    MediaPlayer p;
                    if (level == 1)
                        p = displayVideo("elements/ontarioLose.mp4");
                    else if (level == 2)
                        p = displayVideo("elements/erieLose.mp4");
                    else
                        p = displayVideo("elements/superiorLose.mp4");
                    p.setOnEndOfMedia(() -> {
                        hideStage();
                    });
                    won = -1;
                }
                if (jumpY <= -550) {
                    stop();
                    refresh();
                    won = -1;
                    hideStage();
                }

                if (endStatus == -1) {
                    stop();
                }
            }
        }.start();
    }

}
