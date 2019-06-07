import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Menu class
 * This class represents the Window that has all the graphics and mechanics for the main menu of the Waterworks program. It extends the abstract Window class to use its basic mechanics methods for displaying, hiding and clearing the screen.
 * The class allows the user to press ImageView buttons to indicate which Window they wish to enter. This indication of their choice is returned to the StageManager through the getChoice method, which also manages and drives all the class methods.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.06.2019
 *
 * Version History:
 * May 17:
 * Vansh created class.
 * May 18:
 * Vansh added and implemented display method including background, and buttons, etc.
 * May 19:
 * Vansh added and implemented exit button, and created getChoice() method to drive class and return chosen option number to StageManager.
 * May 20:
 * Vansh changed ImageView buttons to be assigned from Resources class.
 * May 22:
 * Vansh implemented InstructionsBtn ImageView button.
 * Maria implemented QuizBtn ImageView button.
 * May 23:
 * Vansh made ccursor change to click hand when hovering over buttons.
 * Vansh imeplemted LearnBtn ImageView class.
 * May 27:
 * Vansh added clicking sounds for the ImageView buttons.
 * June 3:
 * Vansh redesigned Menu button placement, and implemented a new exit ImageView button.
 * June 6:
 * Vansh converted ImageView property settings to lambda functions.
 */
public class Menu extends Window
{
    /**
     * The chosen menu option.
     */
    private int choice = 0;

    /**
     * The class constructor. It calls the super constructor of the Window class.
     * @param stg The JavaFX Stage to display to.
     */
    public Menu (Stage stg) {
        super(stg, "Main Menu");
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
        Sound click = (Sound)(Resources.get("click"));


        // Listener for MouseClick
        menuPlayBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 3;
            click.play();
        });
        menuQuizBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 2;
            click.play();
        });
        menuInstructionsBtn.setOnMouseClicked(e -> {
            choice = 5;
            click.play();
        });
        menuHighscoresBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 4;
            click.play();
        });
        menuLearnBtn.setOnMouseClicked(e -> {
            choice = 1;
            click.play();
        });
        menuExitBtn.setOnMouseClicked(e -> {
            choice = -1;
            click.play();
        });
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
