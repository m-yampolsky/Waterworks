import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Menu class
 * This class stores an object that represents the Menu window of the Waterworks program
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class Menu extends Window
{
    /**
     * The chosen menu option.
     */
    private int choice;

    /**
     * @param stg The JavaFX Stage to display to.
     */
    public Menu (Stage stg) {
        super(stg, "Main Menu");
        choice = 0;
    }

    /**
     * @return The menu option chosen by the user.
     */
    public int getChoice() {
        display();
        showAndWait();
        refresh();
        int value = choice;
        choice = 0;
        return value;
    }

    /**
     * This method displays all the graphics of the Menu window
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

        /*drawImage(menuLearnBtn, 40, -50);
        drawImage(menuQuizBtn, 0, 50);
        drawImage(menuPlayBtn, 20, 150);
        drawImage(menuExitBtn, -40, 235);
        drawImage(menuHighscoresBtn, 330, 340);
        drawImage(menuInstructionsBtn, -235, -145);*/
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
