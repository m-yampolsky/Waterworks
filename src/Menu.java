import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Menu class
 * This class represents the Window that has all the graphics and mechanics for the main menu of the Waterworks program. It extends the abstract Window class to use its basic mechanics methods for displaying, hiding and clearing the screen.
 * The class allows the user to press ImageView buttons to indicate which Window they wish to enter. This indication of their choice is returned to the StageManager through the getChoice method, which also manages and drives all the class methods.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * <h2>Total Time Spent: 3 hours</h2>
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 6 06.09.2019
 *
 * <pre>
 * Version History:
 * May 17:
 * Vansh created class. -- 0.2 hours
 * May 18:
 * Vansh added and implemented display method including background, and buttons, etc. -- 0.75 hours
 * May 19:
 * Vansh added and implemented exit button, and created getChoice() method to drive class and return chosen option number to StageManager. -- 0.5 hours
 * May 20:
 * Vansh changed ImageView buttons to be assigned from Resources class. -- 0.3 hours
 * May 22:
 * Vansh implemented ImageView button InstructionsBtn. -- 0.2 hours
 * Maria implemented QuizBtn ImageView button. -- 0.2 hours
 * May 23:
 * Vansh made cursor change to click hand when hovering over buttons.
 * Vansh implemented ImageView Button LearnBtn. -- 0.2 hours
 * May 27:
 * Vansh added clicking sounds for the ImageView buttons. -- 0.1 hours
 * June 3:
 * Vansh redesigned Menu button placement, and implemented a new exit ImageView button. -- 0.5 hours
 * June 6:
 * Vansh converted ImageView property settings to lambda functions. -- 0.2 hours
 * </pre>
 */
public class Menu extends Window
{
    /**
     * The chosen menu option.
     */
    private int choice;

    /**
     * The class constructor. It calls the super constructor of the Window class and sets initial values.
     * @param stg The JavaFX Stage to display to.
     */
    public Menu (Stage stg) {
        super(stg, "Main Menu");
        choice = 0;
    }

    /**
     * This method drives the class by running the appropriate methods at the right times, and returning the chosen option to StageManager.
     * @return The menu option chosen by the user.
     */
    public int getChoice() {
        display();
        showAndWait();
        refresh();
        return choice;
    }

    /**
     * This method displays all the graphics, and implements all the buttons of the Menu window.
     */
    @Override
    public void display() {
        Image menuBackground = (Image)(Resources.get("menuBackground"));
        Image menuBackgroundLog = (Image)(Resources.get("backLog"));
        Image menuTitle = (Image)(Resources.get("menuTitle"));
        ImageView menuLearnBtn = (ImageView)(Resources.get("learnBtn"));
        ImageView menuQuizBtn = (ImageView)(Resources.get("quizBtn"));
        ImageView menuPlayBtn = (ImageView)(Resources.get("playBtn"));
        ImageView menuExitBtn = (ImageView)(Resources.get("exitBtn"));
        ImageView menuInstructionsBtn = (ImageView)(Resources.get("instructionsBtn"));
        ImageView menuHighscoresBtn = (ImageView)(Resources.get("highscoresBtn"));


        // Listener for MouseClick
        menuPlayBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 3;
        });
        menuQuizBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 2;
        });
        menuInstructionsBtn.setOnMouseClicked(e -> choice = 5);
        menuHighscoresBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 4;
        });
        menuLearnBtn.setOnMouseClicked(e -> choice = 1);
        menuExitBtn.setOnMouseClicked(e -> choice = -1);
        // Listener for MouseEnter
        menuLearnBtn.setOnMouseEntered(e -> setCursor(1));
        menuQuizBtn.setOnMouseEntered(e -> setCursor(1));
        menuPlayBtn.setOnMouseEntered(e -> setCursor(1));
        menuHighscoresBtn.setOnMouseEntered(e -> setCursor(1));
        menuExitBtn.setOnMouseEntered(e -> setCursor(1));
        menuInstructionsBtn.setOnMouseEntered(e -> setCursor(1));
        // Listener for MouseExit
        menuLearnBtn.setOnMouseExited(e -> setCursor(0));
        menuQuizBtn.setOnMouseExited(e -> setCursor(0));
        menuPlayBtn.setOnMouseExited(e -> setCursor(0));
        menuHighscoresBtn.setOnMouseExited(e -> setCursor(0));
        menuExitBtn.setOnMouseExited(e -> setCursor(0));
        menuInstructionsBtn.setOnMouseExited(e -> setCursor(0));

        drawImage(menuInstructionsBtn, 20, -135);
        drawImage(menuHighscoresBtn, 40, -45);
        drawImage(menuLearnBtn, 175 , 45);
        drawImage(menuQuizBtn, 222, 135);
        drawImage(menuPlayBtn, 207, 225);
        drawImage(menuExitBtn, 435, 275);

        AnimatedImage standing = (AnimatedImage)(Resources.get("standing"));

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                // background image clears canvas
                drawImage(menuBackground, 0, 0);
                drawImage(menuBackgroundLog, 0, 690);
                drawImage(menuTitle, 50, 50);
                drawImage(standing.getFrame(t), -90, 275);

                if (choice != 0) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }
}
