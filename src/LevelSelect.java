import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class LevelSelect extends Window {

    private int choice;

    public LevelSelect(Stage stg) {
        super(stg, "Main Menu");
        choice = 0;
    }

    public int getChoice() {
        display();
        showAndWait();
        refresh();
        return choice;
    }

    public void display()
    {
        Image background = (Image)(Resources.get("menuBackground"));
        Image playTitle = (Image)(Resources.get("playTitle"));
        Image menuBackgroundLog = (Image)(Resources.get("backLog"));
        ImageView lakeOntario = (ImageView)(Resources.get("lakeOntario"));
        ImageView lakeErie = (ImageView)(Resources.get("lakeErie"));
        ImageView lakeSuperior = (ImageView)(Resources.get("lakeSuperior"));
        ImageView backButton = (ImageView)(Resources.get("backButton"));


        backButton.setOnMouseClicked(e -> {
            refresh();
            choice = -1;
        });
        lakeOntario.setOnMouseClicked(e -> {
            refresh();
            choice = 1;
        });
        lakeErie.setOnMouseClicked(e -> {
            refresh();
            choice = 2;
        });
        lakeSuperior.setOnMouseClicked(e -> {
            refresh();
            choice = 3;
        });

        drawImage(lakeOntario, -30, 100);
        drawImage(lakeErie, 170, 100);
        drawImage(lakeSuperior, 370, 100);
        drawImage(backButton, -380, -160);

        AnimatedImage standing = (AnimatedImage)(Resources.get("standing"));

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                drawImage(background, 0, 0);
                drawImage(menuBackgroundLog, 0, 690);
                drawImage(playTitle, 40, 30);
                drawImage(standing.getFrame(t), -90, 275);

                if (choice != 0) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
