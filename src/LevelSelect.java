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

    public int getChoice() throws Exception {
        display();
        showAndWait();
        return choice;
    }

    public void display()
    {
        Image background = new Image ("elements/menus/background.png");
        Image playTitle = new Image ("elements/menus/playTitle.png");
        Image menuBackgroundLog = new Image("elements/menus/backgroundLog.png");
        ImageView lakeOntario = new ImageView ("elements/menus/playLakeOntarioBtn.png");
        ImageView lakeErie = new ImageView ("elements/menus/playLakeErieBtn.png");
        ImageView lakeSuperior = new ImageView ("elements/menus/playLakeSuperiorBtn.png");
        ImageView backButton = new ImageView ("elements/menus/backBtn.png");


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
