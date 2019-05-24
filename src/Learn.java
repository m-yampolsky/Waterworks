import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Learn class
 * This class will store an object that represents the window where the user will be learning about dealing with water overusage.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.20.2019
 */
public class Learn extends Window {

    /**
     * The choice selected for the game level that the user wishes to play.
     */
    private boolean back;


    /**
     * @param stg The JavaFX Stage to display to.
     */
    public Learn(Stage stg) {
        super(stg, "Highscores");
        back = false;
    }

    /**
     * This method will display all the graphics of the Highscores window
     */
    public void display()
    {
        ImageView menuBtn = (ImageView)(Resources.get("menuBtn"));

        // Listener for MouseClick
        menuBtn.setOnMouseClicked(e -> {
            hideStage();
        });
        // Listener for MouseEnter
        menuBtn.setOnMouseEntered(e -> {
            setCursor(true);
        });
        // Listener for MouseExit
        menuBtn.setOnMouseExited(e -> {
            setCursor(false);
        });

        drawImage(menuBtn, 400, -330);

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
