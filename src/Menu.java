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

    public int getChoice() throws Exception {
        display();
        showAndWait();
        return choice;
    }

    public void display() {

        Image menuBackground = new Image("elements/menus/background.png");
        Image menuBackgroundLog = new Image("elements/menus/backgroundLog.png");
        Image menuTitle = new Image("elements/menus/menuLogo.png");
        ImageView menuLearnBtn = new ImageView("elements/menus/menuLearnBtn.png");
        ImageView menuQuizBtn = new ImageView("elements/menus/menuQuizBtn.png");
        ImageView menuPlayBtn = new ImageView("elements/menus/menuPlayBtn.png");
        Image menuInstructionsBtn = new Image("elements/menus/menuInstructionsBtn.png");
        Image menuHighscoresBtn = new Image("elements/menus/menuHighscoresBtn.png");


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

        drawImage(menuLearnBtn, 40, 0);
        drawImage(menuQuizBtn, 0, 105);
        drawImage(menuPlayBtn, 20, 210);

        AnimatedImage standing = new AnimatedImage();
        Image[] imageArray = new Image[180];
        for (int i = 1; i <= 180; i++)
            imageArray[i - 1] = new Image("elements/standing/standing (" + i + ").png");
        standing.frames = imageArray;
        standing.duration = 0.100;

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
                drawImage(menuHighscoresBtn, 700, 700);
                drawImage(standing.getFrame(t), -90, 275);

                if (choice != 0) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }
}