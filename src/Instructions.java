import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Instructions extends Window {
    /**
     * The choice selected for the game level that the user wishes to play.
     */
    private boolean back;


    /**
     * @param stg The JavaFX Stage to display to.
     */
    public Instructions(Stage stg) {
        super(stg, "Highscores");
        back = false;
    }

    /**
     * This method will display all the graphics of the Highscores window
     */
    public void display()
    {
        Image background = (Image)(Resources.get("menuBackground"));
        Image playTitle = (Image)(Resources.get("highscoresTitle"));
        Image menuBackgroundLog = (Image)(Resources.get("backLog"));
        Image highscoresOntario = (Image)(Resources.get("highscoresOntario"));
        Image highscoresErie = (Image)(Resources.get("highscoresErie"));
        Image highscoresSuperior = (Image)(Resources.get("highscoresSuperior"));
        ImageView backButton = (ImageView)(Resources.get("backButton"));


        backButton.setOnMouseClicked(e -> {
            refresh();
            back = true;
        });

        drawImage(backButton, -380, -160);;

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
                drawImage(highscoresOntario, 360, 300);
                drawImage(highscoresErie, 575, 300);
                drawImage(highscoresSuperior, 790, 300);

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
