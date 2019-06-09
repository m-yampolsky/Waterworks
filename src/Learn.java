import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Learn class
 * This class represents the Window that has all the graphics and the mechanics for the first room of the Waterworks program. It extends the abstract Window class to use its basic mechanics methods for displaying, hiding and clearing the screen.
 * This class starts in an empty spot on a conveyor belt, with all devices to the right. There are devices drawn on the left, to give the illusion that the belt is circular, but the xtime variable that controls the belt position wraps
 * around making it seem like there are actually devices on the left side. The same (reversed), is done when the user presses on the learnLeft and learnRight ImageView buttons to navigate to the farthest right devices on the belt.
 * When the room is entered, there is also a water device description a the top of the window. This description corresponds with the nth device on the belt, where n is represented by the global integer current.
 * The current value starts at -1, representing that no current description has been found as of yet. When the class sees that the current variable is -1 and the user is not done, it finds a current description at random from the local
 * descriptions ImageView array. Once a current description is shown, the user must navigate to the corresponding device and click the down-facing ImageView arrow learnCheck. The global boolean right is true when the current description
 * matches the device currently selected (center of screen). If the device does correspond, the description will move down to the screen and the user can move onto matching the next description if they are not done. If they match is incorrect,
 * the learnCheck ImageView button will be removed and a red 'x' ImageView Object learnWrong will be draw in its place. This will be removed and replaced again by the learnCheck ImageView when a nav arrow is clicked. The global numDone
 * integer represents the number of descriptions matched, and the local boolean Array states stores which specific devices have been matched with their corresponding description (updated every time that a description is matched).
 * When navigating through previously matched devices (the states array element in the device number's index is true), their descriptions are re-drawn to the screen, and removed when navigating a to a different device. Once, the numDone integer
 * reaches 11, the done boolean is set to true. This triggers the learnQuiz ImageView button to be drawn in the bottom-left which stops the AnimationTimer and sets the global public boolean quizButtonClicked to be set to true. The stage is then
 * hidden in order to trigger the Stage.showAndWait() call in the StageManager class to move forward.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.06.2019
 *
 * <pre>
 * Version History:
 * May 17:
 * Vansh created the class.
 * May 20:
 * Vansh implemented Learn as an AniamtedImage where an arrow is pressed to move forward in the animation, and a menu button to go back.
 * May 24:
 * Vansh removed the AnimatedImage completely, and remade the animation in JavaFX using Image view Objects for the washer, barrel, watering can, shower head, tub, dish washer, hand washing, hose, and the sink. If statements and variables were
 * also made to allow wrapping around when moving too far to the right of the conveyor belt.
 * May 25:
 * Vansh made the Event properties to make the cursor change to a click hand when hovering over the right arrow button.
 * May 27:
 * Vansh added clicking sounds when buttons are pressed.
 * May 29:
 * Vansh completely revamped the Learn window to make it more interactive with description matching, and made arrows to navigate left as well as right.
 * May 31:
 * Vansh implemented new well and icecap images into the conveyor belt, and fixed bugs with description image sizing when matching with the correct device.
 * June 1:
 * Vansh fixed bug where Window crashed when finishing matching descriptions and pressing certain button.
 * June 2:
 * Vansh fixed bug where done boolean became true before user finished matching all descriptions, and added button to go to Quiz when finished matching descriptions.
 * June 8:
 * Vansh added sounds for right, wrong, and device navigation.
 * </pre>
 */
public class Learn extends Window {
    /**
     * This stores the x-axis movement of the conveyor belt, where 0 is the starting position.
     */
    private int xtime;

    /**
     * This stores the item number currently selected (center-screen), where 1 is the first item and in ascending order.
     */
    private int times;

    /**
     * This stores whether the conveyor belt should be moving, and in which direction. 0 represents not moving, more than 0 represents moving left, and less than 0 represents moving right.
     */
    private int go;

    /**
     * This stores whether the currently selected device (center-screen) is the correct match for the current description to be matched.
     */
    private boolean correct;

    /**
     * This stores whether a description is being shown that must be matched, and if so then which one. more than 0 represents the nth device description, and less than 0  represents no current description to be matched.
     */
    private int current;

    /**
     * This stores whether the user is done -- all descriptions have been matched with their proper devices.
     */
    private boolean done;

    /**
     * This stores whether the quiz button has been clicked.
     */
    private boolean quizButtonClicked;

    /**
     * This stores how many descriptions have been matched with their proper devices.
     */
    private int numDone;

    /**
     * This is the class constructor. It calls the super constructor of the Window class and sets initial values.
     * @param stg The JavaFX Stage to display to.
     */
    public Learn(Stage stg) {
        super(stg, "Highscores");
        xtime = 0;
        times = 0;
        go = 0;
        correct = false;
        current = -1;
        done = false;
        quizButtonClicked = false;
        numDone = 0;
    }

    /**
     * This method will display all the graphics, and manage all the mechanics of the Window including navigating the conveyor belt, matching descriptions with devices, and moving to the Quiz Window at the end.
     */
    public void display()
    {
        // This is a boolean Array storing which devices have had their descriptions matched properly
        boolean[] states = new boolean[11];
        // This is an ImageView array holding the descriptions of all the devices.
        ImageView[] descriptions = {(ImageView)(Resources.get("washerD")), (ImageView)(Resources.get("barrelD")), (ImageView)(Resources.get("canD")), (ImageView)(Resources.get("showerD")) , (ImageView)(Resources.get("iceD")), (ImageView)(Resources.get("wellD")), (ImageView)(Resources.get("tubD")), (ImageView)(Resources.get("dwasherD")), (ImageView)(Resources.get("dishD")), (ImageView)(Resources.get("hoseD")), (ImageView)(Resources.get("sinkD"))};

        // buttons
        ImageView menuBtn = (ImageView)(Resources.get("menuBtn"));
        Image menuBack = (Image)(Resources.get("menuBackground"));
        Image learnBack = (Image)(Resources.get("learnBack"));
        Image learnScreen = (Image)(Resources.get("learnScreen"));
        ImageView learnLeft = (ImageView)(Resources.get("learnLeft"));
        ImageView learnRight = (ImageView)(Resources.get("learnRight"));
        ImageView learnCheck = (ImageView)(Resources.get("learnCheck"));
        ImageView learnWrong = (ImageView)(Resources.get("learnWrong"));
        ImageView quiz = (ImageView)(Resources.get("learnQuiz"));

        // conveyor belt item Images
        ImageView hose2 = (ImageView)(Resources.get("learnHose2"));
        ImageView sink2 = (ImageView)(Resources.get("learnSink2"));
        ImageView washer = (ImageView)(Resources.get("learnWasher"));
        ImageView barrel = (ImageView)(Resources.get("learnBarrel"));
        ImageView can = (ImageView)(Resources.get("learnCan"));
        ImageView shower = (ImageView)(Resources.get("learnShower"));
        ImageView ice = (ImageView)(Resources.get("learnIce"));
        ImageView well = (ImageView)(Resources.get("learnWell"));
        ImageView tub = (ImageView)(Resources.get("learnTub"));
        ImageView dwasher = (ImageView)(Resources.get("learnDWasher"));
        ImageView dish = (ImageView)(Resources.get("learnDish"));
        ImageView hose = (ImageView)(Resources.get("learnHose"));
        ImageView sink = (ImageView)(Resources.get("learnSink"));
        ImageView washer2 = (ImageView)(Resources.get("learnWasher2"));
        ImageView barrel2 = (ImageView)(Resources.get("learnBarrel2"));

        // click sound
        Sound click = (Sound)(Resources.get("click"));
        Sound right = (Sound)(Resources.get("right"));
        Sound wrong = (Sound)(Resources.get("wrong"));

        // Listeners for MouseClicked
        menuBtn.setOnMouseClicked(e -> {
            hideStage();
        });
        learnLeft.setOnMouseClicked(e -> {
            if (times > 0 && times < 12)
                remove(descriptions[times-1]);
            if (go != -1) {
                go = -1;
                times--;
            }
            if (times > 0 && states[times-1]) {
                remove(learnCheck);
                drawImage(learnScreen, 284, 523);
            } else {
                drawImage(learnBack, 0, 385);
                drawImage(learnCheck, -15, 260);
            }
            click.play();
        });
        learnRight.setOnMouseClicked(e -> {
            if (times > 0 && times < 12)
                remove(descriptions[times-1]);
            if (go != 1) {
                go = 1;
                times++;
            }
            if (times < 11 && states[times-1]) {
                remove(learnCheck);
                drawImage(learnScreen, 284, 523);
            } else {
                drawImage(learnBack, 0, 385);
                drawImage(learnCheck, -15, 260);
            }
            click.play();
        });
        learnCheck.setOnMouseClicked(e -> {
            if (correct) {
                right.play();
                remove(descriptions[current]);
                remove(learnCheck);
                descriptions[current].setPreserveRatio(true);
                descriptions[current].setFitHeight(215);
                drawImage(descriptions[current], -13, 257);
                states[current] = true;
                numDone++;
                if (numDone >= 11) {
                    current = -2;
                } else
                    current = -1;
            } else {
                wrong.play();
                remove(learnCheck);
                drawImage(learnWrong, -15, 255);
            }
        });
        // Listeners for MouseEntered
        menuBtn.setOnMouseEntered(e -> setCursor(1));
        learnLeft.setOnMouseEntered(e -> setCursor(1));
        learnRight.setOnMouseEntered(e -> setCursor(1));
        learnCheck.setOnMouseEntered(e -> setCursor(1));
        // Listeners for MouseExited
        menuBtn.setOnMouseExited(e -> setCursor(0));
        learnLeft.setOnMouseExited(e -> setCursor(0));
        learnRight.setOnMouseExited(e -> setCursor(0));
        learnCheck.setOnMouseExited(e -> setCursor(0));

        // draw all of background and buttons
        drawImage(menuBack, 0, 0);
        drawImage(learnBack, 0, 385);
        drawImage(menuBtn, 400, -330);
        drawImage(learnLeft, -265, 260);
        drawImage(learnRight, 240, 260);
        drawImage(learnCheck, -15, 260);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // if done variable is not true, then check if it should be (set to true if every element of states array are true)
                if (!done) {
                    boolean d = true;
                    for (boolean s : states)
                        if (!s) {
                            d = false;
                            break;
                        }
                    if (d) {
                        done = true;
                    }
                }

                // if not done...
                if (!done) {
                    // ...while a current description is not set, set one at random until the one selected is not of state done.
                    while (current == -1 && numDone < 11) {
                        int tmp = (int) (Math.random() * 11);
                        if (!states[tmp])
                            current = tmp;
                    }
                    // ...and if a description is set, draw the description at the proper position with the proper dimensions.
                    if (current != -1 && current != -2) {
                        descriptions[current].setPreserveRatio(true);
                        descriptions[current].setFitHeight(170);
                        drawImage(descriptions[current], -13, -280);
                    }
                }

                // if conveyor belt is moving, and a wrong symbol can be (and now has been) removed, draw a check button in its place.
                if (go != 0 && remove(learnWrong)) {
                    drawImage(learnCheck, -15, 260);
                }
                // if conveyor belt is moving left, draw the proper images in the correct spots, and remove any that are not needed.
                if (go == 1) {
                    xtime += 12;
                    if (times == 1 && xtime >= 300 || times == 2 && xtime >= 550 || times == 3 && xtime >= 850 || times == 4 && xtime >= 1170 || times == 5 && xtime >= 1550 || times == 6 && xtime >= 1910 || times == 7 && xtime >= 2310 || times == 8 && xtime >= 2750 || times == 9 && xtime >= 3050 || times == 10 && xtime >= 3310 || times == 11 && xtime >= 3600) {
                        go = 0;
                        if (states[times-1]) {
                            drawImage(descriptions[times - 1], -13, 256);
                            remove(learnCheck);
                        } else
                            drawImage(learnCheck, -15, 260);
                        correct = times - 1 == current;
                    }
                // if conveyor belt is moving right, draw the proper images in the right spots, and remove any that are not needed.
                } else if (go == -1) {
                    xtime -= 12;
                    if (times == 1 && xtime <= 300 || times == 2 && xtime <= 550 || times == 3 && xtime <= 850 || times == 4 && xtime <= 1170 || times == 5 && xtime <= 1550 || times == 6 && xtime <= 1910 || times == 7 && xtime <= 2310 || times == 8 && xtime <= 2750 || times == 9 && xtime <= 3050 || times == 10 && xtime <= 3310 || times == 11 && xtime <= 3600) {
                        go = 0;
                        if (states[times-1]) {
                            drawImage(descriptions[times - 1], -13, 256);
                            remove(learnCheck);
                        } else
                            drawImage(learnCheck, -15, 260);
                        correct = times - 1 == current;
                    }
                }

                // remove all device images, and redraw them at the right position.
                remove(hose2);
                remove(sink2);
                remove(washer);
                remove(barrel);
                remove(can);
                remove(shower);
                remove(ice);
                remove(well);
                remove(tub);
                remove(dwasher);
                remove(dish);
                remove(hose);
                remove(sink);
                remove(washer2);
                remove(barrel2);
                drawImage(hose2, -990-xtime, 0);
                drawImage(sink2, -700-xtime, 0);
                drawImage(washer, 290-xtime, -50);
                drawImage(barrel, 540-xtime, -30);
                drawImage(can, 840-xtime, -30);
                drawImage(shower, 1160-xtime, -30);
                drawImage(ice, 1540-xtime, -30);
                drawImage(well, 1900-xtime, -40);
                drawImage(tub, 2300-xtime, -30);
                drawImage(dwasher, 2740-xtime, -30);
                drawImage(dish, 3040-xtime, -30);
                drawImage(hose, 3300-xtime, 0);
                drawImage(sink, 3590-xtime, 0);
                drawImage(washer2, 4600-xtime, -50);
                drawImage(barrel2, 4850-xtime, -30);

                // wrap around when moving conveyor belt to the left.
                if (xtime >= 4310) {
                    xtime = 0;
                    times = 1;
                // wrap around when moving conveyor belt to the right.
                } else if (xtime <= -450) {
                    xtime = 3840;
                    times = 11;
                }

                // if done matching all descriptions, show button to move on to quiz screen.
                if (done) {
                    drawImage(quiz, 400, 325);

                    quiz.setOnMouseClicked(e -> {
                        quizButtonClicked = true;
                        hideStage();
                    });
                    quiz.setOnMouseEntered(e -> setCursor(1));
                    quiz.setOnMouseExited(e -> setCursor(0));
                }
            }
        }.start();
    }

    /**
     * This method returns the value of the quizButtonClickedProperty, determining if the Quiz button has been clicked.
     * @return true if the Quix button has been clicked, and false if it has not
     */
    public boolean getQuizButtonClicked()
    {
        return quizButtonClicked;
    }
}
