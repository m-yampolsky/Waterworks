import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Splashscreen class
 * This class represents the window with an animation that plays at the start of the program.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
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

                if (splashscreen.frame(t) < 167) {
                    clean();
                    drawImage(splashscreen.getSplashFrame(t), 0, 0);
                }
                if (splashscreen.frame(t) >= 168) {
                    // load resources
                    Resources.add("standing", new AnimatedImage("elements/standing/standing", 180, 0.050));
                    Resources.add("walking", new AnimatedImageView("elements/walking/walking", 178, 0.070));

                    Resources.add("finalWasher", new AnimatedImageView("elements/washer/washer", 50, 0.050));
                    Resources.add("finalTub", new AnimatedImageView("elements/tub/tub", 50, 0.050));
                    Resources.add("finalSink", new AnimatedImageView("elements/sink/sink", 50, 0.050));

                    Resources.add("menuBackground", new Image("elements/menus/background.png"));
                    Resources.add("backLog", new Image("elements/menus/backgroundLog.png"));
                    Resources.add("menuTitle", new Image("elements/menus/menuLogo.png"));
                    Resources.add("instructionsBtn", new ImageView("elements/menus/menuInstructions.png"));
                    Resources.add("highscoresBtn", new ImageView("elements/menus/menuHighscores.png"));
                    Resources.add("learnBtn", new ImageView("elements/menus/menuLearn.png"));
                    Resources.add("quizBtn", new ImageView("elements/menus/menuQuiz.png"));
                    Resources.add("playBtn", new ImageView("elements/menus/menuPlay.png"));
                    Resources.add("exitBtn", new ImageView("elements/menus/menuExit.png"));
                    Image[] in = new Image[8];
                    in[0] = new Image("elements/menus/instructions0.png");
                    in[1] = new Image("elements/menus/instructions1.png");
                    in[2] = new Image("elements/menus/instructions2.png");
                    in[3] = new Image("elements/menus/instructions3.png");
                    in[4] = new Image("elements/menus/instructions4.png");
                    in[5] = new Image("elements/menus/instructions5.png");
                    in[6] = new Image("elements/menus/instructions6.png");
                    in[7] = new Image("elements/menus/instructions7.png");
                    for (int i = 0; i < in.length; i++)
                        Resources.add("instructions"+i, in[i]);
                    Resources.add("instructionsForward", new ImageView ( "elements/menus/instructionsForward.png" ));
                    Resources.add("instructionsBackward", new ImageView ( "elements/menus/instructionsBackward.png" ));
                    Resources.add("playTitle", new Image("elements/menus/playTitle.png"));
                    Resources.add("highscoresTitle", new Image("elements/menus/highscoresTitle.png"));
                    Resources.add("highscoresOntario", new Image("elements/menus/highscoresLakeOntario.png"));
                    Resources.add("highscoresErie", new Image("elements/menus/highscoresLakeErie.png"));
                    Resources.add("highscoresSuperior", new Image("elements/menus/highscoresLakeSuperior.png"));
                    Resources.add("lakeOntario", new ImageView("elements/menus/playLakeOntarioBtn.png"));
                    Resources.add("lakeErie", new ImageView("elements/menus/playLakeErieBtn.png"));
                    Resources.add("lakeSuperior", new ImageView("elements/menus/playLakeSuperiorBtn.png"));
                    Resources.add("backButton", new ImageView("elements/menus/backBtn.png"));
                    Resources.add("dirtBack", new Image( "elements/game/dirtBack.png" ));

                    Resources.add("loseBack", new Image( "elements/menus/loseBack.png" ));
                    Resources.add("loseTitle", new Image( "elements/menus/loseTitle.png" ));
                    Resources.add("loseMenu", new ImageView( "elements/menus/loseMenu.png" ));
                    Resources.add("loseTryAgain", new ImageView( "elements/menus/loseTryAgain.png" ));
                    Resources.add("winBack", new Image( "elements/menus/winBack.png" ));
                    Resources.add("winTitle", new Image( "elements/menus/winTitle.png" ));
                    Resources.add("winScore", new Image( "elements/menus/winScore.png" ));
                    Resources.add("winMenu", new ImageView( "elements/menus/winMenu.png" ));
                    Resources.add("winNextLevel", new ImageView( "elements/menus/winNextLevel.png" ));

                    Resources.add("ontarioLake", new Image( "elements/game/ontarioLake.png" ));
                    Resources.add("ontarioBack", new Image( "elements/game/ontarioBack.png" ));
                    Resources.add("ontarioToronto", new Image( "elements/game/ontarioToronto.png" ));
                    Resources.add("ontarioLogImg", new ImageView ( "elements/game/ontarioLogLine.png" ));
                    Resources.add("ontarioLogLine", new LogLine("elements/game/ontarioLogLine.png"));
                    Resources.add("ontarioDeviceImg", new ImageView ( "elements/game/ontarioDeviceLine.png" ));
                    Resources.add("ontarioDeviceLine", new DeviceLine("elements/game/ontarioDeviceLine.png"));

                    Resources.add("erieLake", new Image( "elements/game/erieLake.png" ));
                    Resources.add("erieBack", new Image( "elements/game/erieBack.png" ));
                    Resources.add("erieCabin", new Image("elements/game/erieWatchCabin.png"));
                    Resources.add("erieLogImg", new ImageView ( "elements/game/erieLogLine.png" ));
                    Resources.add("erieLogLine", new LogLine("elements/game/erieLogLine.png"));
                    Resources.add("erieDeviceImg", new ImageView ( "elements/game/erieDeviceLine.png" ));
                    Resources.add("erieDeviceLine", new DeviceLine("elements/game/erieDeviceLine.png"));

                    Resources.add("superiorLake", new Image( "elements/game/superiorLake.png" ));
                    Resources.add("superiorBack", new Image( "elements/game/superiorBack.png" ));
                    Resources.add("superiorTrees", new Image("elements/game/superiorTrees.png"));
                    Resources.add("superiorLogImg", new ImageView ( "elements/game/superiorLogLine.png" ));
                    Resources.add("superiorLogLine", new LogLine("elements/game/superiorLogLine.png"));
                    Resources.add("superiorDeviceImg", new ImageView ( "elements/game/superiorDeviceLine.png" ));
                    Resources.add("superiorDeviceLine", new DeviceLine("elements/game/superiorDeviceLine.png"));

                    Resources.add("avatarImg", new GameChar ("elements/game/backgroundChar.png" ));
                    Resources.add("avatar", new ImageView ((GameChar)(Resources.get("avatarImg"))));

                    Resources.add("menuBtn", new ImageView ("elements/menus/menuBtn.png"));
                    Resources.add("quizBack", new ImageView ("elements/game/quizBack.png"));

                    Resources.add("dSponge", new ImageView ("elements/game/dish.png"));
                    Resources.add("hose", new ImageView ("elements/game/hose.png"));
                    Resources.add("sink", new ImageView ("elements/game/sink.png"));
                    Resources.add("tub", new ImageView ("elements/game/tub.png"));
                    Resources.add("barrel", new ImageView ("elements/game/barrel.png"));
                    Resources.add("cWasher", new ImageView ("elements/game/washer.png"));
                    Resources.add("dWasher", new ImageView ("elements/game/dishwasher.png"));
                    Resources.add("shower", new ImageView ("elements/game/shower.png"));
                    Resources.add("wCan", new ImageView ("elements/game/watercan.png"));

                    Resources.add("boxBack", new ImageView ("elements/game/boxBack.png"));
                    Resources.add("boxBack2", new ImageView ("elements/game/boxBack.png"));
                    Resources.add("effBox", new ImageView("elements/game/boxFront1.png"));
                    Resources.add("ineffBox", new ImageView("elements/game/boxFront2.png"));
                    Resources.add("check", new ImageView("elements/game/quizCheck.png"));

                    Resources.add("learnBack", new Image ("elements/game/learnBack.png"));
                    Resources.add("learnScreen", new Image ("elements/game/learnScreen.png"));
                    Resources.add("learnLeft", new ImageView ("elements/game/learnLeft.png"));
                    Resources.add("learnRight", new ImageView ("elements/game/learnRight.png"));
                    Resources.add("learnCheck", new ImageView ("elements/game/learnCheck.png"));
                    Resources.add("learnWrong", new ImageView ("elements/game/learnWrong.png"));
                    Resources.add("learnQuiz", new ImageView ("elements/game/learnQuiz.png"));

                    Resources.add("learnHose2", new ImageView ("elements/game/learnHose.png"));
                    Resources.add("learnSink2", new ImageView ("elements/game/learnSink.png"));
                    Resources.add("learnWasher", new ImageView ("elements/game/learnWasher.png"));
                    Resources.add("learnBarrel", new ImageView ("elements/game/learnBarrel.png"));
                    Resources.add("learnCan", new ImageView ("elements/game/learnCan.png"));
                    Resources.add("learnShower", new ImageView ("elements/game/learnShower.png"));
                    Resources.add("learnIce", new ImageView ("elements/game/learnIce.png"));
                    Resources.add("learnWell", new ImageView ("elements/game/learnWell.png"));
                    Resources.add("learnTub", new ImageView ("elements/game/learnTub.png"));
                    Resources.add("learnDWasher", new ImageView ("elements/game/learnDWasher.png"));
                    Resources.add("learnDish", new ImageView ("elements/game/learnDish.png"));
                    Resources.add("learnHose", new ImageView ("elements/game/learnHose.png"));
                    Resources.add("learnSink", new ImageView ("elements/game/learnSink.png"));
                    Resources.add("learnWasher2", new ImageView ("elements/game/learnWasher.png"));
                    Resources.add("learnBarrel2", new ImageView ("elements/game/learnBarrel.png"));

                    Resources.add("washerD", new ImageView ("elements/game/descriptions/washer.png"));
                    Resources.add("barrelD", new ImageView ("elements/game/descriptions/barrel.png"));
                    Resources.add("canD", new ImageView ("elements/game/descriptions/wcan.png"));
                    Resources.add("showerD", new ImageView ("elements/game/descriptions/shower.png"));
                    Resources.add("iceD", new ImageView ("elements/game/descriptions/ice.png"));
                    Resources.add("wellD", new ImageView ("elements/game/descriptions/well.png"));
                    Resources.add("tubD", new ImageView ("elements/game/descriptions/tub.png"));
                    Resources.add("dwasherD", new ImageView ("elements/game/descriptions/dwasher.png"));
                    Resources.add("dishD", new ImageView ("elements/game/descriptions/dish.png"));
                    Resources.add("hoseD", new ImageView ("elements/game/descriptions/hose.png"));
                    Resources.add("sinkD", new ImageView ("elements/game/descriptions/sink.png"));

                    Resources.add("checkBack", new Image("elements/game/checkBack.png"));
                    Resources.add("checkPlay", new ImageView("elements/game/checkPlay.png"));
                    Resources.add("checkMark", new Image("elements/game/checkMark.png"));
                    Resources.add("wrong", new Image("elements/game/wrong.png"));

                    Resources.add("textField", new ImageView("elements/game/winTextBox.png"));
                    Resources.add("nameLabel", new ImageView ("elements/game/winName.png"));
                    Resources.add("saveButton", new ImageView("elements/game/winSave.png"));
                    Resources.add("savedMsg", new ImageView ("elements/game/winSaved.png"));
                    Resources.add("winInst", new ImageView ("elements/menus/winInstructions.png"));

                    stop();
                    hideStage();
                }
            }
        }.start();
        refresh();
    }
}
