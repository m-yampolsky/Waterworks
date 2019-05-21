import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The Splashscreen class
 * This class represents the window with an animation that plays at the start of the program.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.20.2019
 */
public class SplashScreen extends Window
{

    /**
     * @param stg The JavaFX Stage to display to.
     */
    public SplashScreen (Stage stg) {
        super(stg, "Splash Screen");
    }

    /**
     * This method displays the animation for a period of time, and then moves on to the Main Menu
     */
    public void display () {

        AnimatedImage splashscreen = ((AnimatedImage)Resources.get("splashscreen"));

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                clean();
                drawImage(splashscreen.getFrame(t), 0, 0);

                if (t > 19) {
                    stop();
                    hideStage();
                }
            }
        }.start();
        refresh();
    }
}
