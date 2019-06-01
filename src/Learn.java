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

    int current = -1;

    boolean done = false;


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
        boolean[] states = new boolean[11];
        ImageView[] descriptions = {(ImageView)(Resources.get("washerD")), (ImageView)(Resources.get("barrelD")), (ImageView)(Resources.get("canD")), (ImageView)(Resources.get("showerD")) , (ImageView)(Resources.get("iceD")), (ImageView)(Resources.get("wellD")), (ImageView)(Resources.get("tubD")), (ImageView)(Resources.get("dwasherD")), (ImageView)(Resources.get("dishD")), (ImageView)(Resources.get("hoseD")), (ImageView)(Resources.get("sinkD"))};

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
        ImageView ice = (ImageView)(Resources.get("learnIce"));
        ImageView well = (ImageView)(Resources.get("learnWell"));
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
            if (times > 0)
                remove(descriptions[times-1]);
            if (go != 2) {
                go = 2;
                times--;
            }
            if (times > 0 && states[times-1]) {
                remove(learnCheck);
                drawImage(learnScreen, 284, 523);
            } else {
                drawImage(learnBack, 0, 385);
                drawImage(learnCheck, -15, 260);
            }
            click.play();
        });
        learnRight.setOnMouseClicked(e -> {
            if (times > 0)
                remove(descriptions[times-1]);
            if (go != 1) {
                go = 1;
                times++;
            }
            if (times < 11 && states[times-1]) {
                remove(learnCheck);
                drawImage(learnScreen, 284, 523);
            } else {
                drawImage(learnBack, 0, 385);
                drawImage(learnCheck, -15, 260);
            }
            click.play();
        });
        learnCheck.setOnMouseClicked(e -> {
            if (right) {
                remove(descriptions[current]);
                remove(learnCheck);
                descriptions[current].setPreserveRatio(true);
                descriptions[current].setFitHeight(215);
                drawImage(descriptions[current], -13, 257);
                states[current] = true;
                current = -1;
            } else {
                remove(learnCheck);
                drawImage(learnWrong, -15, 255);
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

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                boolean d = false;
                if (!done)
                    for (boolean s : states)
                        if (!s)
                            break;
                    if (d)
                        done = true;

                if (!done)
                    if (current == -1)
                        while (true) {
                            current = (int) (Math.random() * 10);
                            if (!states[current])
                                break;
                        }
                    else {
                        descriptions[current].setPreserveRatio(true);
                        descriptions[current].setFitHeight(170);
                        drawImage(descriptions[current], -13, -280);
                    }

                if (go != 0 && remove(learnWrong)) {
                    drawImage(learnCheck, -15, 260);
                }
                if (go == 1) {
                    xtime += 12;
                    if (times == 1 && xtime >= 300 || times == 2 && xtime >= 550 || times == 3 && xtime >= 850 || times == 4 && xtime >= 1170 || times == 5 && xtime >= 1550 || times == 6 && xtime >= 1910 || times == 7 && xtime >= 2310 || times == 8 && xtime >= 2750 || times == 9 && xtime >= 3050 || times == 10 && xtime >= 3310 || times == 11 && xtime >= 3600) {
                        go = 0;
                        if (states[times-1]) {
                            drawImage(descriptions[times - 1], -13, 256);
                            remove(learnCheck);
                        } else
                            drawImage(learnCheck, -15, 260);
                        if (times-1 == current)
                            right = true;
                        else
                            right = false;
                    }
                } else if (go == 2) {
                    xtime -= 12;
                    if (times == 1 && xtime <= 300 || times == 2 && xtime <= 550 || times == 3 && xtime <= 850 || times == 4 && xtime <= 1170 || times == 5 && xtime <= 1550 || times == 6 && xtime <= 1910 || times == 7 && xtime <= 2310 || times == 8 && xtime <= 2750 || times == 9 && xtime <= 3050 || times == 10 && xtime <= 3310 || times == 11 && xtime <= 3600) {
                        go = 0;
                        if (states[times-1]) {
                            drawImage(descriptions[times - 1], -13, 256);
                            remove(learnCheck);
                        } else
                            drawImage(learnCheck, -15, 260);
                        if (times-1 == current)
                            right = true;
                        else
                            right = false;
                    }
                }

                // background image clears canvas
                remove(hose2);
                remove(sink2);
                remove(washer);
                remove(barrel);
                remove(can);
                remove(shower);
                remove(ice);
                remove(well);
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
                drawImage(ice, 1540-xtime, -30);
                drawImage(well, 1900-xtime, -60);
                drawImage(tub, 2300-xtime, -30);
                drawImage(dwasher, 2740-xtime, -30);
                drawImage(dish, 3040-xtime, -30);
                drawImage(hose, 3300-xtime, 0);
                drawImage(sink, 3590-xtime, 0);
                drawImage(washer2, 4600-xtime, -50);
                drawImage(barrel2, 4850-xtime, -30);

                if (xtime >= 4310) {
                    xtime = 0;
                    times = 1;
                } else if (xtime <= -450) {
                    xtime = 3840;
                    times = 11;
                }

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
