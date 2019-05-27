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

    private int xtime = 0;

    private int times = 0;

    private boolean go = false;


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
        Image menuBack = (Image)(Resources.get("menuBackground"));
        Image learnBack = (Image)(Resources.get("learnBack"));

        ImageView washer = (ImageView)(Resources.get("learnWasher"));
        ImageView barrel = (ImageView)(Resources.get("learnBarrel"));
        ImageView can = (ImageView)(Resources.get("learnCan"));
        ImageView shower = (ImageView)(Resources.get("learnShower"));
        ImageView tub = (ImageView)(Resources.get("learnTub"));
        ImageView dwasher = (ImageView)(Resources.get("learnDWasher"));
        ImageView dish = (ImageView)(Resources.get("learnDish"));
        ImageView hose = (ImageView)(Resources.get("learnHose"));
        ImageView sink = (ImageView)(Resources.get("learnSink"));
        ImageView washer2 = (ImageView)(Resources.get("learnWasher2"));
        ImageView barrel2 = (ImageView)(Resources.get("learnBarrel2"));
        ImageView learnNext = (ImageView)(Resources.get("learnNext"));

        // Listeners for MouseClick
        menuBtn.setOnMouseClicked(e -> {
            hideStage();
        });
        learnNext.setOnMouseClicked(e -> {
            if (!go) {
                go = true;
                times++;
            }
        });
        // Listeners for MouseEnter
        menuBtn.setOnMouseEntered(e -> {
            setCursor(1);
        });
        learnNext.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listeners for MouseExit
        menuBtn.setOnMouseExited(e -> {
            setCursor(0);
        });
        learnNext.setOnMouseExited(e -> {
            setCursor(0);
        });

        drawImage(menuBack, 0, 0);
        drawImage(learnBack, 0, 400);
        drawImage(menuBtn, 400, -330);
        drawImage(learnNext, 222, 338);

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);


                if (go) {
                    xtime += 6;
                    if (times == 1 && xtime >= 300 || times == 2 && xtime >= 550 || times == 3 && xtime >= 850 || times == 4 && xtime >= 1170 || times == 5 && xtime >= 1610 || times == 6 && xtime >= 2050 || times == 7 && xtime >= 2350 || times == 8 && xtime >= 2610 || times == 9 && xtime >= 2900)
                        go = false;
                }

                // background image clears canvas
                remove(washer);
                remove(barrel);
                remove(can);
                remove(shower);
                remove(tub);
                remove(dwasher);
                remove(dish);
                remove(hose);
                remove(sink);
                remove(washer2);
                remove(barrel2);
                drawImage(washer, 290-xtime, -50);
                drawImage(barrel, 540-xtime, -30);
                drawImage(can, 840-xtime, -30);
                drawImage(shower, 1160-xtime, -30);
                drawImage(tub, 1600-xtime, -30);
                drawImage(dwasher, 2040-xtime, -30);
                drawImage(dish, 2340-xtime, -30);
                drawImage(hose, 2600-xtime, 0);
                drawImage(sink, 2890-xtime, 0);
                drawImage(washer2, 3900-xtime, -50);
                drawImage(barrel2, 4150-xtime, -30);

                if (xtime == 3606) {
                    xtime = 0;
                    times = 1;
                }

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
