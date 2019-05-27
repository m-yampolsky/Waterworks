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
        Image playTitle = (Image)(Resources.get("instructionsTitle"));
        Image menuBackgroundLog = (Image)(Resources.get("backLog"));
        ImageView backButton = (ImageView)(Resources.get("backButton"));
        ImageView instructions1 = (ImageView)(Resources.get("instructions1"));
        ImageView instructions2 = (ImageView)(Resources.get("instructions2"));
        ImageView instructionsF = (ImageView)(Resources.get("instructionsForward"));
        ImageView instructionsB = (ImageView)(Resources.get("instructionsBackward"));


        // Listeners for MouseClick
        backButton.setOnMouseClicked(e -> {
            refresh();
            back = true;
        });
        instructionsF.setOnMouseClicked(e -> {
            remove(instructions1);
            remove(instructionsF);
            drawImage(instructions2, 130, 50);
            drawImage(instructionsB, 400, 330);
        });
        instructionsB.setOnMouseClicked(e -> {
            remove(instructions2);
            remove(instructionsB);
            drawImage(instructions1, 130, 50);
            drawImage(instructionsF, 400, 330);
        });
        // Listeners for MouseEnter
        backButton.setOnMouseEntered(e -> {
            setCursor(1);
        });
        instructionsF.setOnMouseEntered(e -> {
            setCursor(1);
        });
        instructionsB.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listeners for MouseExit
        backButton.setOnMouseExited(e -> {
            setCursor(0);
        });
        instructionsF.setOnMouseExited(e -> {
            setCursor(0);
        });
        instructionsB.setOnMouseExited(e -> {
            setCursor(0);
        });

        drawImage(backButton, -380, -160);
        drawImage(instructions1, 130, 50);
        drawImage(instructionsF, 400, 330);

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

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
