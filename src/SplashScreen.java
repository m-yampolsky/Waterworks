import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SplashScreen extends Window
{

    public SplashScreen (Stage stg) {
        super(stg, "Splash Screen");
    }

    public void display () {
        gc().setFill(Color.WHITE);

        AnimatedImage splashscreen = new AnimatedImage();
        Image[] imageArray = new Image[180];
        for (int i = 1; i <= 170; i++)
            imageArray[i - 1] = new Image("elements/splash/splash (" + i + ").png");
        splashscreen.frames = imageArray;
        splashscreen.duration = 0.100;

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
    }
}