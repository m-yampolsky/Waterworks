import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * The Game class
 * This class represents the window that has all the graphics for the third room of the Waterworks program. It extends the abstract Window class to use its basic mechanics methods for displaying, hiding and clearing the screen.
 * This class starts with a screen of a lake with logs on it, and with inefficient and efficient water devices on and above the logs. The cactus avatar is on the left side of the screen, and is being displayed with a walking animation, as the logs and devices move left.
 * The avatar jumps when the user presses the space bar. There is a menu button in the top left corner, and when the user presses it, they return to the Main Menu. The water level of the lake is constantly dropping, and if it drops all the way, the user loses, and a lose animation is played, and a lose screen is shown.
 * From the lose screen, they can press a button to return to Main Menu, or they can press a button to try the same level again. If they miss the jump between two logs, they will also lose, and the same lose screen is shown with the same two options.
 * If they make it to the end of the logs, they will reach a turned on inefficient water devices, and an animation of the avatar turning off the device will play. The win screen will then be displayed, where the user will be prompted to enter their name.
 * They are limited to ten characters, and they must press the save button to save their name and score. They can press a button to return to main menu, and if they are on the first two lakes, after saving, a button will also appear to continue to the next lake.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * May 17:
 * Vansh added the background, water, and logs for the Ontario level, and added the animation that moved them across the screen.
 * May 19:
 * Maria added the level property to the game, adding the set of if statements in display() that check for the different levels, and change the images displayed based on the level.
 * Vansh added the Rectangular Viewports in the display() method to only have to load a portion of an Image at a time to save RAM.
 * May 20:
 * Maria added the avatar to the game, using an ImageView representation of the character, as well as a GameChar representation.
 * Vansh removed all the Image & ImageView loading from the class, and added that instead to the Resources class. Now, the Images and ImageView objects are acessed from the Resources hashmap.
 * May 26:
 * Vansh added an animation of the avatar walking into the display() method, by using the AnimatedImageView class. He also added the jumping animation,
 * writing the portion of the display() method that checks for keyboard input and responds by moving the avatar up screen.
 * May 31:
 * Maria wrote the collision detection for the logs. She wrote the portion of the display() method that determines if the avatar is or is not touching a kg.
 * June 1:
 * Vansh added graphics for Lake Erie and Lake Superior by using different files for each lake in the display() method.
 * June 2:
 * Maria added the falling avatar animation in the display() method to the user missing a log. She also made the first call to the isTouchingDevice() method, however that collision detection was not yet working.
 * Vansh wrote a second call to the isTouchingDevice() method, and was able to improve the Collision Detection by correcting errors in calculation, however it was still not working perfectly.
 * Vansh also added the win and lose screens.
 * June 3:
 * Maria added the user input related elements to the screen (without any activity yet). She set up the Image and ImageView objects used in the win() method.
 * June 4:
 * Maria added user input to the win() method to ask for the user's name. She also created the SCORES_FILE variable, and imported java.io.* classes to write and read from the highs scores storing file.
 * She wrote the portion of the win() method that writes to the high scores file. She also added the actual score to the win screen.
 * Vansh made the music stop when the user goes to the lose screen. He also added the lose animation and the win animations that are displayed when the user loses or wins.
 * June 5:
 * Maria modified the win() method to include simple instructions for user input. She added a image that explicitly states how many characters they can enter, and that a blank name cannot be saved.
 * Maria also added a default "USER" name, if the user chooses to return to Main Menu without saving their score. This change was also made in the win() method.
 * Vansh corrected an error in the collision detection, as the parameter being passed into the isTouchingDevice() method called in the display() method for the startY value was being calculated incorrectly.
 * June 6:
 * Maria modifed the SCORE_FILE variable to store the location of the User's Desktop folder, to write the high scores file there. She also created the SCORES_FILE_BACKUP to store the original file location,
 * which will be used in the case that the user has blocked access to their Desktop. She also changed the win method to try and write to the Desktop first, and if that fails, to write to the user's home directory.
 * June 8:
 * Vansh adjusted the call to avatarImg.isTouchingDevice() in order to make collision detection more accurate.
 * June 9:
 * Vansh adjusted collision detection and redrew menuBtn after the deviceLine so that it wouldn't be covered up.
 * </pre>
 */
public class Game extends Window {
    /**
     * The user's current level score.
     */
    private int score;

    /**
     * The level names.
     */
    private static final String[] names = {"Lake Ontario", "Lake Erie", "Lake Superior"};

    /**
     * The current level selected.
     */
    private final int level;

    /**
     * This stores the avatar's height above the water level.
     */
    private int jumpY;

    /**
     * This stores the avatar's position horizontally away from its initial position.
     */
    private int jumpX;

    /**
     * This stores whether or not the avatar is jumping.
     */
    private boolean jumping;

    /**
     * This stores the time at which the avatar jumped.
     */
    private double jumpStart;

    /**
     * This stores the time at which the avatar completed its jump.
     */
    private double jumpStop;

    /**
     * This stores the current time in nanoseconds.
     */
    private double t = 0;

    /**
     * This stores the avatar frame as it is displaying on screen.
     */
    private ImageView charFrame;

    /**
     * This stores the previous frame of the avatar's walking animation.
     */
    private ImageView lastCharFrame;

    /**
     * This stores the next frame of the avatar's walking animation.
     */
    private ImageView nextCharFrame;

    /**
     * This stores the frame of the devices where they are located on the screen.
     */
    private ImageView finalDevFrame;

    /**
     * This stores the final frame of the devices moving across the screen.
     */
    private ImageView lastFinalDevFrame;

    /**
     * This stores whether the avatar is touching a log.
     */
    private boolean logTouched;

    /**
     * This stores whether the avatar is falling.
     */
    private boolean falling;

    /**
     * This stores the point at which the frame view of the LogLine and DeviceLine images is at.
     */
    private int startX;

    /**
     * This stores whether the user's name.
     */
    private String name;

    /**
     * This stores the character location in the name String that the user input is on.
     */
    private int onChar;

    /**
     * Store the current time in nanoseconds.
     */
    private long startNanoTime;

    /**
     * This stores whether the user input for name has been saved.
     */
    private boolean saved;

    /**
     * This stores a character representation of the user's name input.
     */
    private final char[] charsName;

    /**
     * This stores the end status of the level that the user has just completed.
     */
    private int endStatus;

    /**
     * This stores the file location of the high score storing file.
     */
    private final File SCORES_FILE;

    /**
     * This stores a backup file location of the high score storing file, in case the directory of SCORES_FILE is inaccessible.
     */
    private final File SCORES_FILE_BACKUP;

    /**
     * This stores the time at which the player has reached the point where they have won, and the win animation should play.
     */
    private double won;

    /**
     * This is the class constructor. It sets initial values for the variables, and calls the super constructor of the Window class.
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
        name = "          ";
        saved = false;
        charsName = name.toCharArray();
        onChar = 0;
        endStatus = 0;
        SCORES_FILE = new File (System.getProperty("user.home") + "/Desktop/highScoresFile.wtr");
        SCORES_FILE_BACKUP = new File (System.getProperty("user.home") + "/highScoresFile.wtr");
        won = 0;
    }


    /**
     * This method puts together all the methods of this class to run a game, and play the correct win or lose result, depending on the result of the game. The methods win() and lose() are called to display the correct animations and the correct final screens.
     */
    public void run() {
        display();
        showAndWait();
        refresh();
    }


    /**
     * This method displays the result of the level if the user loses. It displays two buttons, allowing for the user to choose to return to the Main Menu or to try the same Game level again.
     */
    private void lose() {
        Sound.stopAll(); //stops all the sounds playing

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
        menu.setOnMouseEntered(e -> setCursor(1));
        tryAgain.setOnMouseEntered(e -> setCursor(1));

        // Listener for MouseExit
        menu.setOnMouseExited(e -> setCursor(0));
        tryAgain.setOnMouseExited(e -> setCursor(0));

        drawImage(back, 0, 0); //draws background
        drawImage(title, 130, 260); //draws the "YOU LOSE" text
        drawImage(menu, -390, 325); //draws the Menu button
        drawImage(tryAgain, 310, 325); //draws the Try Again button
    }

    /**
     * This method displays the result of the level if the user wins. It displays a message for them to enter their name,
     * it collects user input, and allows for them to save their score and their name. They are only able to progress to
     * the next level once they have entered their name. If they return to menu without saving their name, their score is saved with the name "USER".
     * Their name must be no longer than 10 characters, and it cannot begin with a space.
     */
    private void win() {
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
        ImageView winInst = (ImageView)(Resources.get("winInst"));

        scoreTxt.setFont(Font.font("Consolas", FontWeight.BOLD ,70));
        scoreTxt.setFill (Color.WHITE);


        // Listener for MouseClick
        menu.setOnMouseClicked(e -> {
            if (!saved){
                name = "USER"; //if user has not entered a name, their score is saved with the name "USER"
                PrintWriter output1;
                try {
                    output1 = new PrintWriter(new BufferedWriter(new FileWriter(SCORES_FILE, true))); //their score is added to a file with all the scores
                    output1.println("level played:" + level);
                    output1.println(name);
                    output1.println(score);
                    output1.close();
                } catch (IOException ignored) {
                }
            }
            click.play();
            hideStage();
        });
        nextLevel.setOnMouseClicked(e -> {
            click.play();
            endStatus = 2;
            hideStage(); //this brings them on to the next lake of the game
        });

        // Listener for MouseEnter - changes cursor image
        menu.setOnMouseEntered(e -> setCursor(1));
        saveButton.setOnMouseEntered(e -> setCursor(1));

        // Listener for MouseExit - changes cursor image
        menu.setOnMouseExited(e -> setCursor(0));
        saveButton.setOnMouseExited(e -> setCursor(0));

        drawImage(back, 0, 0);
        drawImage(title, 155, 200);
        drawImage(scoreImg, 225, 400);
        drawImage(menu, -390, 325);
        drawImage(winInst, 150, 315);
        add (scoreTxt, 90, 60);


        output.setFont(Font.font("Consolas", 50));

        drawImage (textField, 0, 185);
        drawImage (nameLabel, -262, 185);
        drawImage (saveButton, 352, 185);

        saveButton.setOnMouseClicked(event -> {
            if (!saved) {
                if (onChar > 0) { //if they have entered at least one character, their name will be saved
                    saved = true;
                    remove(textField);
                    remove(nameLabel);
                    remove(saveButton);
                    remove(output);
                    remove (winInst);
                    drawImage(savedMessage, 0, 185); //informs the user that their score has been saved

                    nextLevel.setOnMouseExited(e -> setCursor(0));
                    nextLevel.setOnMouseEntered(e -> setCursor(1));
                    if (level < 3)
                        drawImage(nextLevel, 310, 325);
                    if (!SCORES_FILE.exists()) {
                        try {
                            SCORES_FILE.createNewFile(); //if the file does not exist on the computer yet, a new one is created
                        } catch (IOException e) {
                            if (!SCORES_FILE_BACKUP.exists()) {
                                try {
                                    SCORES_FILE_BACKUP.createNewFile(); //if the file does not exist on the computer yet, a new one is created
                                } catch (IOException ignored) {
                                }
                            }
                        }
                    }
                    PrintWriter output12;
                    try {
                        output12 = new PrintWriter(new BufferedWriter(new FileWriter(SCORES_FILE, true))); //this writes their score and name to a file
                        output12.println("level played:" + level);
                        output12.println(name);
                        output12.println(score);
                        output12.close();
                    } catch (IOException e) {
                        try {
                            output12 = new PrintWriter(new BufferedWriter(new FileWriter(SCORES_FILE_BACKUP, true))); //this writes their score and name to a file
                            output12.println("level played:" + level);
                            output12.println(name);
                            output12.println(score);
                            output12.close();
                        } catch (IOException ignored) {
                        }
                    }
                }
            }
        });

        //collecting input for the user's name
        getScene().setOnKeyPressed(
                e -> {
                    if (!saved) {
                        String code = e.getCode().toString();
                        if ((code.length() == 1 || e.getCode().isDigitKey()) && onChar <= 9) { //checks if the user enters a key with a number on it
                            if (code.contains("DIGIT"))
                                charsName[onChar] = code.charAt(code.length() - 1); //allows the user to put numbers into their name
                            else
                                charsName[onChar] = code.charAt(0);
                            onChar++;
                            name = new String(charsName); //recreates the name String
                            output.setText(name);
                            remove(output);
                            add(output, 85, 185);
                        } else if (code.equals("SPACE") && onChar != 0 && onChar <= 9) { //if they enter a space, it only adds it if they have already entered at least 1 character
                            onChar++;
                            output.setText(name);
                            remove(output);
                            add(output, 85, 185);
                        } else if ((code.equals("DELETE") || code.equals("BACK_SPACE")) && onChar != 0) { //deletes the last character entered if backspace or delete is clicked
                            charsName[onChar - 1] = ' ';
                            onChar--;
                            name = new String(charsName);
                            output.setText(name);
                            remove(output);
                            add(output, 85, 185);
                        }
                    }
                });

    }


    /**
     * This method displays all the graphics for the Game window. It displays appropriate images, depending on the level being played. This contains the animation that allows the
     * user to move the avatar across the screen by clicking the space bar and making it jump. This method processes the location of devices and logs on screen to determine if the avatar is
     * touching an inefficient device, an efficient device, or a log. This method also monitors the water level during game play, dropping it throughout the animation, and dropping it
     * if an inefficient device is detected. It also detects the presence of logs, and if the avatar is not jumping and is not on a log, the avatar will fall and the game will end.
     * There is also a menu button present during this method to allow for the user to return to the main menu. This method also controls scoring. The score is subtracted from everytime the
     * user comes in contact with an inefficient device and everytime the avatar jumps. It is added to every time they come in contact with an efficient water device.
     */
    @Override
    public void display() {
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
        AnimatedImageView win = (AnimatedImageView)(Resources.get("win"));
        ImageView menuBtn = (ImageView)(Resources.get("menuBtn"));

        Water w = new Water(1);

        // Listener for MouseClick
        menuBtn.setOnMouseClicked(e -> {
            endStatus = -1;
            hideStage();
        });

        // Listener for MouseEnter
        menuBtn.setOnMouseEntered(e -> setCursor(1));
        // Listener for MouseExit
        menuBtn.setOnMouseExited(e -> setCursor(0));
        drawImage(menuBtn, 400, -330); //draws the Menu button


        avatar.setPreserveRatio(true);
        avatar.setFitHeight(260);



        if (level == 1) //sets the correct images for Lake Ontario
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
        else if (level == 2) //sets the correct images for Lake Erie
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
        else //sets the correct images for Lake Superior
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

        //checks if user is pressing down on key input
        getScene().setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                });

        //checks if user is releasing key input
        getScene().setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                    if (code.equals("SPACE") && jumping)
                        jumpStop = System.nanoTime();
                });


        startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime) {
                t = 180+(currentNanoTime - startNanoTime) / 300000000.0;

                if (jumpY < 10 && won == 0) {
                    logTouched = avatarImg.isTouchingLog(logLine, startX, jumpX);
                    if (!logTouched)
                        falling = true;
                }

                // background image clears canvas
                drawImage(lakeBackground, 0, 0);
                // draws the correct background depending on the lake being played, and correct position based on whether winning animation is playing
                if (level == 1)
                    if (won == 0)
                        drawImage(cityBack, 480 - (int) (t * 3), 249);
                    else
                        drawImage(cityBack, 480 - (int) (229.5 * 3), 249);
                else if (level == 2)
                    if (won == 0)
                        drawImage(cityBack, 580 - (int) (t * 3), 304);
                    else
                        drawImage(cityBack, 580 - (int) (229.5 * 3), 304);
                else if (won == 0)
                    drawImage(cityBack, 480 - (int) (t * 3), 20);
                else
                    drawImage(cityBack, 580 - (int) (229.5 * 3), 304);
                drawImage(dirtBack, 0, 542);
                drawImage(lake, 1, w.getYValue());

                Rectangle2D viewportRect = new Rectangle2D((int) (t * 40), 0, 1000, 75); //sets the viewport for the log image
                Rectangle2D viewportRectDevices = new Rectangle2D((int) (t * 40), 0, 1000, deviceLine.getHeight()); //sets the viewport for the device image
                if (won != 0) {
                    viewportRect = new Rectangle2D((int) (229.5 * 40), 0, 1000, 75); //sets the viewport for the log image
                    viewportRectDevices = new Rectangle2D((int) (229.5 * 40), 0, 1000, deviceLine.getHeight()); //sets the viewport for the device image
                }
                startX = (int) (t * 40);
                logImg.setViewport(viewportRect);
                deviceImg.setViewport(viewportRectDevices);
                remove(deviceImg);
                remove(logImg);
                remove(avatar);
                drawImage(logImg, 0, w.getYValue() - 370);
                drawImage(deviceImg, 0, w.getYValue() - 640);


                //character walking animation
                lastCharFrame = charFrame;
                if (nextCharFrame != null)
                    charFrame = nextCharFrame;
                if (won == 0)
                    nextCharFrame = walking.getFrame(t);
                else
                    nextCharFrame = win.getFrame(t - won);
                if (charFrame != null && won == 0)
                    drawImage(charFrame, -380 + jumpX, w.getYValue() - 490 - jumpY);
                if (won != 0)
                    drawImage(charFrame, -380 + jumpX, w.getYValue() - 490);
                remove(lastCharFrame);

                if (finalDevFrame != null)
                    lastFinalDevFrame = finalDevFrame;
                if (won == 0) {
                    finalDevFrame = finalDevice.getFrame(t);
                    remove(lastFinalDevFrame);
                    drawImage(finalDevFrame, 9500 - (int) (t * 40), w.getYValue() - 430);
                } else if (win.frame(t-won) <= 115) {
                    finalDevFrame = finalDevice.getFrame(t);
                    remove(lastFinalDevFrame);
                    drawImage(finalDevFrame, 9500 - (int) (229.5 * 40), w.getYValue() - 430);
                }else {
                    remove(lastFinalDevFrame);
                    drawImage(finalDevFrame, 9500 - (int) (229.5 * 40), w.getYValue() - 430);
                }

                //checks if user has inputted information to make the avatar jump
                if (input.contains("SPACE") && !falling && won == 0) {
                    if (!jumping && jumpY == 0 && (currentNanoTime - jumpStop) / 300000000.0 > 2) {
                        jumping = true;
                        jumpStart = t;
                        score -= 1;
                    }
                }else
                    jumping = false;

                //if avatar is jumping, move it forward and up
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
                    if (jumpX > 0 && jumpY == 0 && won == 0) //if user is not jumping, and position is not at default, decrease it to return to the default position
                        jumpX--;
                    if (falling)
                    {
                        jumpY -= 10; //if the avatar is falling, lower it down the screen
                    }
                    else if (jumpY > 0)
                        jumpY = Math.max(jumpY - ((int) (5 * ((currentNanoTime - jumpStop) / 300000000.0))), 0);
                    else
                        jumpStart = 0;
                }

                //detects whether the character is in contact with a device
                int deviceType = avatarImg.isTouchingDevice(deviceLine, startX+jumpX+120-65, w.getYValue()-640+325-jumpY+65+10, 120, 220);
                if (deviceType == 1){
                    w.changeHeight(1); //change in response to an efficient device
                    score += 5;
                }
                else if (deviceType == -1){
                    w.changeHeight(-1); //change in response to an inefficient device
                    score -= 5;
                }

                if (-380+jumpX >= 9100-(int)(t*40) && won == 0)
                    won = t;
                if (won != 0 && win.frame(t-won) >= 177 && t-won >= 2) { //stops the game movement, because the player has win
                    stop();
                    score += w.getHeight();
                    refresh();
                    win();
                }
                if (w.getYValue() >= 750 || jumpY <= -550) { //stops the game movement, because the player has lost
                    stop();
                    refresh();
                    lose();
                }

                if (endStatus == -1) { //stops the game movement, because the player has exited
                    stop();
                }
            }
        }.start();
    }

    /**
     * This method returns the value of the program's end status.
     * @return the value of the endStatus variable
     */
    public int getEndStatus()
    {
        return endStatus;
    }
}
