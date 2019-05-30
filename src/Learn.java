import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Learn class
 * This class will store an object that represents the window where the user will be learning about dealing with water overusage.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class Learn extends Window {

    /**
     * The choice selected for the game level that the user wishes to play.
     */
    private boolean back;

    private int xtime = 0;

    private int times = 0;

    private int go = 0;

    private boolean right = false;


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
        Image learnScreen = (Image)(Resources.get("learnScreen"));
        ImageView learnLeft = (ImageView)(Resources.get("learnLeft"));
        ImageView learnRight = (ImageView)(Resources.get("learnRight"));
        ImageView learnCheck = (ImageView)(Resources.get("learnCheck"));
        ImageView learnWrong = (ImageView)(Resources.get("learnWrong"));

        ImageView hose2 = (ImageView)(Resources.get("learnHose2"));
        ImageView sink2 = (ImageView)(Resources.get("learnSink2"));
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
        Sound click = (Sound)(Resources.get("click"));

        // Listeners for MouseClick
        menuBtn.setOnMouseClicked(e -> {
            hideStage();
            click.play();
        });
        learnLeft.setOnMouseClicked(e -> {
            if (go != 2) {
                go = 2;
                times--;
            }
            click.play();
        });
        learnRight.setOnMouseClicked(e -> {
            if (go != 1) {
                go = 1;
                times++;
            }
            click.play();
        });
        learnCheck.setOnMouseClicked(e -> {
            if (right) {
                remove(learnCheck);
                drawImage(learnScreen, 284, 523);
            } else {
                remove(learnCheck);
                drawImage(learnWrong, -15, 260);
            }
        });
        // Listeners for MouseEnter
        menuBtn.setOnMouseEntered(e -> {
            setCursor(1);
        });
        learnLeft.setOnMouseEntered(e -> {
            setCursor(1);
        });
        learnRight.setOnMouseEntered(e -> {
            setCursor(1);
        });
        learnCheck.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listeners for MouseExit
        menuBtn.setOnMouseExited(e -> {
            setCursor(0);
        });
        learnLeft.setOnMouseExited(e -> {
            setCursor(0);
        });
        learnRight.setOnMouseExited(e -> {
            setCursor(0);
        });
        learnCheck.setOnMouseExited(e -> {
            setCursor(0);
        });

        drawImage(menuBack, 0, 0);
        drawImage(learnBack, 0, 385);
        drawImage(menuBtn, 400, -330);
        drawImage(learnLeft, -265, 260);
        drawImage(learnRight, 240, 260);
        drawImage(learnCheck, -15, 260);

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                if (go != 0 && remove(learnWrong)) {
                    drawImage(learnCheck, -15, 260);
                }
                if (go == 1) {
                    xtime += 6;
                    if (times == 1 && xtime >= 300 || times == 2 && xtime >= 550 || times == 3 && xtime >= 850 || times == 4 && xtime >= 1170 || times == 5 && xtime >= 1610 || times == 6 && xtime >= 2050 || times == 7 && xtime >= 2350 || times == 8 && xtime >= 2610 || times == 9 && xtime >= 2900)
                        go = 0;
                } else if (go == 2) {
                    xtime -= 6;
                    if (times == 1 && xtime <= 300 || times == 2 && xtime <= 550 || times == 3 && xtime <= 850 || times == 4 && xtime <= 1170 || times == 5 && xtime <= 1610 || times == 6 && xtime <= 2050 || times == 7 && xtime <= 2350 || times == 8 && xtime <= 2610 || times == 9 && xtime <= 2900)
                        go = 0;
                }

                // background image clears canvas
                remove(hose2);
                remove(sink2);
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
                drawImage(hose2, -990-xtime, 0);
                drawImage(sink2, -700-xtime, 0);
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

                if (xtime >= 3606) {
                    xtime = 0;
                    times = 1;
                } else if (xtime <= -450) {
                    xtime = 3135;
                    times = 9;
                }

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
