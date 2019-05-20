import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SplashScreen extends Window
{

    public SplashScreen (Stage stg) {
        super(stg, "Splash Screen");
    }

    public void display () {
        gc().setFill(Color.WHITE);

        AnimatedImage splashscreen = ((AnimatedImage)Resources.get("splashscreen"));

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                gc().fillRect(0, 0, 1000, 750);
                gc().drawImage(splashscreen.getFrame(t), 0, 0);

                if (t > 19) {
                    stop();
                    hideStage();
                }
            }
        }.start();
        refresh();
    }
}