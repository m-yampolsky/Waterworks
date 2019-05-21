import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * The Menu class
 * This class stores an object that represents the Menu window of the Waterworks program
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.20.2019
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
        Image menuInstructionsBtn = (Image)(Resources.get("instructionsBtn"));
        ImageView menuHighscoresBtn = (ImageView)(Resources.get("highscoresBtn"));


        // Listener for MouseClick
        menuLearnBtn.setOnMouseClicked(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Learn");
        });
        menuQuizBtn.setOnMouseClicked(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Quiz");
        });
        menuPlayBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 1;
        });
        menuHighscoresBtn.setOnMouseClicked(e -> {
            refresh();
            choice = 4;
        });
        menuExitBtn.setOnMouseClicked(e -> {
            choice = -1;
        });

        drawImage(menuLearnBtn, 40, -50);
        drawImage(menuQuizBtn, 0, 50);
        drawImage(menuPlayBtn, 20, 150);
        drawImage(menuExitBtn, -40, 235);
        drawImage(menuHighscoresBtn, 330, 340);

        AnimatedImage standing = (AnimatedImage)(Resources.get("standing"));

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                drawImage(menuBackground, 0, 0);
                drawImage(menuBackgroundLog, 0, 690);
                drawImage(menuTitle, 50, 50);
                drawImage(menuInstructionsBtn, 60, 200);
                drawImage(standing.getFrame(t), -90, 275);

                if (choice != 0) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }
}
