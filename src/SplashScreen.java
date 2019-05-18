import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class SplashScreen
{
    public SplashScreen(GraphicsContext gc, StackPane root) {
        gc.setFill(Color.WHITE);

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

                gc.fillRect(0, 0, 1000, 750);
                gc.drawImage(splashscreen.getFrame(t), 0, 0);

                if (t > 19) {
                    stop();
                    new Menu(gc, root);
                }
            }
        }.start();
    }
}