import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

public class Menu extends Window
{
    private int choice;

    public Menu (Stage stg) {
        super(stg, "Main Menu");
        choice = 0;
    }

    public int getChoice() {
        display();
        showAndWait();
        refresh();
        return choice;
    }

    public void display() {

        Image menuBackground = (Image)(Resources.get("menuBackground"));
        Image menuBackgroundLog = (Image)(Resources.get("backLog"));
        Image menuTitle = (Image)(Resources.get("menuTitle"));
        ImageView menuLearnBtn = (ImageView)(Resources.get("learnBtn"));
        ImageView menuQuizBtn = (ImageView)(Resources.get("quizBtn"));
        ImageView menuPlayBtn = (ImageView)(Resources.get("playBtn"));
        ImageView menuExitBtn = (ImageView)(Resources.get("exitBtn"));
        Image menuInstructionsBtn = (Image)(Resources.get("instrcutionsBtn"));
        Image menuHighscoresBtn = (Image)(Resources.get("highscoresBtn"));


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
        menuExitBtn.setOnMouseClicked(e -> {
            choice = -1;
        });

        drawImage(menuLearnBtn, 40, -50);
        drawImage(menuQuizBtn, 0, 50);
        drawImage(menuPlayBtn, 20, 150);
        drawImage(menuExitBtn, -40, 235);

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
                drawImage(menuHighscoresBtn, 670, 700);
                drawImage(standing.getFrame(t), -90, 275);

                if (choice != 0) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }
}