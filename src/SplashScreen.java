import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Splashscreen class
 * This class represents the window with an animation that plays at the start of the program with the Pistachio Labs logo, and then loads program Resources to avoid lag during runtime.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * <h2>Total Time Spent: 4.5 hours</h2>
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 6 06.09.2019
 *
 * <pre>
 * Version History:
 * May 17:
 * Vansh created Splashscreen, and implemented the animation inside the constructor. -- 1.5 hours
 * May 18:
 * Vansh moved the animation into a display() method. -- 0.1 hours
 * May 23:
 * Vansh added the loading screen implementation. -- 1 hour
 * May 26:
 * Vansh added Resource loading for large animations into the loading screen section. -- 0.25 hours
 * June 2:
 * Vansh moved all of the Resource loading from StageManager (before Window is ever shown), to the loading screen section. -- 0.5 hours
 * June 4:
 * Vansh fixed bug where Splashscreen would sometimes skip the loading screen and get stuck in an animation loop. -- 0.5 hours
 * Maria added Resource loading for new win screen elements and added DeviceLine Resource loading. -- 0.25 hours
 * June 5:
 * Maria added Resource loading for new win screen instruction text. -- 0.1 hours
 * Vansh added Resource loading for new instructions Images. -- 0.1 hours
 * June 8:
 * Vansh added Resource loading for the Learn Window's right and wrong sounds, clicking sounds, the Quiz Window's box sound and the Lake Erie + Superior Music. -- 0.20 hours
 * </pre>
 */
public class SplashScreen extends Window
{

    /**
     * This is the class constructor. It calls the super constructor of the Window class.
     * @param stg The JavaFX Stage to display to.
     */
    public SplashScreen (Stage stg) {
        super(stg, "Splash Screen");
    }

    /**
     * This method displays the animation for a period of time, shows the loading screen (last frame of animation), and then moves on to the Main Menu.
     */
    @Override
    public void display () {

        AnimatedImage splashscreen = new AnimatedImage("elements/splash/splash", 170, 0.100);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                // if the frame being displayed is less than the 167th, then clean the screen, and draw it
                if (splashscreen.frame(t) < 167) {
                    clean();
                    drawImage(splashscreen.getSplashFrame(t), 0, 0);
                } else { // otherwise, load resources (because the loading screen is being displayed), then stop the AnimationTimer and hide the stage.
                    // load resources
                    Resources.add("standing", new AnimatedImage("elements/standing/standing", 180, 0.050));
                    Resources.add("walking", new AnimatedImageView("elements/walking/walking", 178, 0.070));
                    Resources.add("win", new AnimatedImageView("elements/win/win", 179, 0.10));

                    Resources.add("finalWasher", new AnimatedImageView("elements/washer/washer", 50, 0.050));
                    Resources.add("finalTub", new AnimatedImageView("elements/tub/tub", 50, 0.050));
                    Resources.add("finalSink", new AnimatedImageView("elements/sink/sink", 50, 0.050));

                    Resources.add("menuBackground", new Image(Resources.getPath("elements/menus/background.png")));
                    Resources.add("backLog", new Image(Resources.getPath("elements/menus/backgroundLog.png")));
                    Resources.add("menuTitle", new Image(Resources.getPath("elements/menus/menuLogo.png")));
                    Resources.add("instructionsBtn", new ImageView(new Image(Resources.getPath("elements/menus/menuInstructions.png"))));
                    Resources.add("highscoresBtn", new ImageView(new Image(Resources.getPath("elements/menus/menuHighscores.png"))));
                    Resources.add("learnBtn", new ImageView(new Image(Resources.getPath("elements/menus/menuLearn.png"))));
                    Resources.add("quizBtn", new ImageView(new Image(Resources.getPath("elements/menus/menuQuiz.png"))));
                    Resources.add("playBtn", new ImageView(new Image(Resources.getPath("elements/menus/menuPlay.png"))));
                    Resources.add("exitBtn", new ImageView(new Image(Resources.getPath("elements/menus/menuExit.png"))));
                    Image[] in = new Image[8];
                    in[0] = new Image(Resources.getPath("elements/menus/instructions0.png"));
                    in[1] = new Image(Resources.getPath("elements/menus/instructions1.png"));
                    in[2] = new Image(Resources.getPath("elements/menus/instructions2.png"));
                    in[3] = new Image(Resources.getPath("elements/menus/instructions3.png"));
                    in[4] = new Image(Resources.getPath("elements/menus/instructions4.png"));
                    in[5] = new Image(Resources.getPath("elements/menus/instructions5.png"));
                    in[6] = new Image(Resources.getPath("elements/menus/instructions6.png"));
                    in[7] = new Image(Resources.getPath("elements/menus/instructions7.png"));
                    for (int i = 0; i < in.length; i++)
                        Resources.add("instructions"+i, in[i]);
                    Resources.add("instructionsForward", new ImageView (new Image(Resources.getPath("elements/menus/instructionsForward.png" ))));
                    Resources.add("instructionsBackward", new ImageView ( new Image(Resources.getPath("elements/menus/instructionsBackward.png" ))));
                    Resources.add("playTitle", new Image(Resources.getPath("elements/menus/playTitle.png")));
                    Resources.add("highscoresTitle", new Image(Resources.getPath("elements/menus/highscoresTitle.png")));
                    Resources.add("highscoresOntario", new Image(Resources.getPath("elements/menus/highscoresLakeOntario.png")));
                    Resources.add("highscoresErie", new Image(Resources.getPath("elements/menus/highscoresLakeErie.png")));
                    Resources.add("highscoresSuperior", new Image(Resources.getPath("elements/menus/highscoresLakeSuperior.png")));
                    Resources.add("lakeOntario", new ImageView(new Image(Resources.getPath("elements/menus/playLakeOntarioBtn.png"))));
                    Resources.add("lakeErie", new ImageView(new Image(Resources.getPath("elements/menus/playLakeErieBtn.png"))));
                    Resources.add("lakeSuperior", new ImageView(new Image(Resources.getPath("elements/menus/playLakeSuperiorBtn.png"))));
                    Resources.add("backButton", new ImageView(new Image(Resources.getPath("elements/menus/backBtn.png"))));
                    Resources.add("dirtBack", new Image( Resources.getPath("elements/game/dirtBack.png" )));

                    Resources.add("loseBack", new Image( Resources.getPath("elements/menus/loseBack.png" )));
                    Resources.add("loseTitle", new Image( Resources.getPath("elements/menus/loseTitle.png" )));
                    Resources.add("loseMenu", new ImageView( new Image(Resources.getPath("elements/menus/loseMenu.png" ))));
                    Resources.add("loseTryAgain", new ImageView( new Image(Resources.getPath("elements/menus/loseTryAgain.png" ))));
                    Resources.add("winBack", new Image( Resources.getPath("elements/menus/winBack.png" )));
                    Resources.add("winTitle", new Image( Resources.getPath("elements/menus/winTitle.png" )));
                    Resources.add("winScore", new Image(Resources.getPath( "elements/menus/winScore.png" )));
                    Resources.add("winMenu", new ImageView( new Image(Resources.getPath("elements/menus/winMenu.png" ))));
                    Resources.add("winNextLevel", new ImageView(new Image(Resources.getPath("elements/menus/winNextLevel.png" ))));

                    Resources.add("ontarioLake", new Image( Resources.getPath("elements/game/ontarioLake.png" )));
                    Resources.add("ontarioBack", new Image( Resources.getPath("elements/game/ontarioBack.png" )));
                    Resources.add("ontarioToronto", new Image( Resources.getPath("elements/game/ontarioToronto.png" )));
                    Resources.add("ontarioLogImg", new ImageView ( new Image(Resources.getPath("elements/game/ontarioLogLine.png" ))));
                    Resources.add("ontarioLogLine", new LogLine(Resources.getPath("elements/game/ontarioLogLine.png")));
                    Resources.add("ontarioDeviceImg", new ImageView ( new Image(Resources.getPath("elements/game/ontarioDeviceLine.png" ))));
                    Resources.add("ontarioDeviceLine", new DeviceLine(Resources.getPath("elements/game/ontarioDeviceLine.png")));

                    Resources.add("erieLake", new Image( Resources.getPath("elements/game/erieLake.png" )));
                    Resources.add("erieBack", new Image( Resources.getPath("elements/game/erieBack.png" )));
                    Resources.add("erieCabin", new Image(Resources.getPath("elements/game/erieWatchCabin.png")));
                    Resources.add("erieLogImg", new ImageView ( new Image(Resources.getPath("elements/game/erieLogLine.png" ))));
                    Resources.add("erieLogLine", new LogLine(Resources.getPath("elements/game/erieLogLine.png")));
                    Resources.add("erieDeviceImg", new ImageView ( new Image(Resources.getPath("elements/game/erieDeviceLine.png" ))));
                    Resources.add("erieDeviceLine", new DeviceLine(Resources.getPath("elements/game/erieDeviceLine.png")));

                    Resources.add("superiorLake", new Image( Resources.getPath("elements/game/superiorLake.png" )));
                    Resources.add("superiorBack", new Image( Resources.getPath("elements/game/superiorBack.png" )));
                    Resources.add("superiorTrees", new Image(Resources.getPath("elements/game/superiorTrees.png")));
                    Resources.add("superiorLogImg", new ImageView ( new Image(Resources.getPath("elements/game/superiorLogLine.png" ))));
                    Resources.add("superiorLogLine", new LogLine(Resources.getPath("elements/game/superiorLogLine.png")));
                    Resources.add("superiorDeviceImg", new ImageView (new Image(Resources.getPath("elements/game/superiorDeviceLine.png" ))));
                    Resources.add("superiorDeviceLine", new DeviceLine(Resources.getPath("elements/game/superiorDeviceLine.png")));

                    Resources.add("avatarImg", new GameChar (Resources.getPath("elements/game/backgroundChar.png" )));
                    Resources.add("avatar", new ImageView ((GameChar)(Resources.get("avatarImg"))));

                    Resources.add("menuBtn", new ImageView (new Image(Resources.getPath("elements/menus/menuBtn.png"))));
                    Resources.add("quizBack", new ImageView (new Image(Resources.getPath("elements/game/quizBack.png"))));

                    Resources.add("dSponge", new ImageView (new Image(Resources.getPath("elements/game/dish.png"))));
                    Resources.add("hose", new ImageView (new Image(Resources.getPath("elements/game/hose.png"))));
                    Resources.add("sink", new ImageView (new Image(Resources.getPath("elements/game/sink.png"))));
                    Resources.add("tub", new ImageView (new Image(Resources.getPath("elements/game/tub.png"))));
                    Resources.add("barrel", new ImageView (new Image(Resources.getPath("elements/game/barrel.png"))));
                    Resources.add("cWasher", new ImageView (new Image(Resources.getPath("elements/game/washer.png"))));
                    Resources.add("dWasher", new ImageView (new Image(Resources.getPath("elements/game/dishwasher.png"))));
                    Resources.add("shower", new ImageView (new Image(Resources.getPath("elements/game/shower.png"))));
                    Resources.add("wCan", new ImageView (new Image(Resources.getPath("elements/game/watercan.png"))));

                    Resources.add("boxBack", new ImageView (new Image(Resources.getPath("elements/game/boxBack.png"))));
                    Resources.add("boxBack2", new ImageView (new Image(Resources.getPath("elements/game/boxBack.png"))));
                    Resources.add("effBox", new ImageView(new Image(Resources.getPath("elements/game/boxFront1.png"))));
                    Resources.add("ineffBox", new ImageView(new Image(Resources.getPath("elements/game/boxFront2.png"))));
                    Resources.add("check", new ImageView(new Image(Resources.getPath("elements/game/quizCheck.png"))));

                    Resources.add("learnBack", new Image (Resources.getPath("elements/game/learnBack.png")));
                    Resources.add("learnScreen", new Image (Resources.getPath("elements/game/learnScreen.png")));
                    Resources.add("learnLeft", new ImageView (new Image(Resources.getPath("elements/game/learnLeft.png"))));
                    Resources.add("learnRight", new ImageView (new Image(Resources.getPath("elements/game/learnRight.png"))));
                    Resources.add("learnCheck", new ImageView (new Image(Resources.getPath("elements/game/learnCheck.png"))));
                    Resources.add("learnWrong", new ImageView (new Image(Resources.getPath("elements/game/learnWrong.png"))));
                    Resources.add("learnQuiz", new ImageView (new Image(Resources.getPath("elements/game/learnQuiz.png"))));

                    Resources.add("learnAquifer2", new ImageView (new Image(Resources.getPath("elements/game/learnAquifer.png"))));
                    Resources.add("learnSink2", new ImageView (new Image(Resources.getPath("elements/game/learnSink.png"))));
                    Resources.add("learnWasher", new ImageView (new Image(Resources.getPath("elements/game/learnWasher.png"))));
                    Resources.add("learnBarrel", new ImageView (new Image(Resources.getPath("elements/game/learnBarrel.png"))));
                    Resources.add("learnCan", new ImageView (new Image(Resources.getPath("elements/game/learnCan.png"))));
                    Resources.add("learnShower", new ImageView (new Image(Resources.getPath("elements/game/learnShower.png"))));
                    Resources.add("learnIce", new ImageView (new Image(Resources.getPath("elements/game/learnIce.png"))));
                    Resources.add("learnWell", new ImageView (new Image(Resources.getPath("elements/game/learnWell.png"))));
                    Resources.add("learnTub", new ImageView (new Image(Resources.getPath("elements/game/learnTub.png"))));
                    Resources.add("learnDWasher", new ImageView (new Image(Resources.getPath("elements/game/learnDWasher.png"))));
                    Resources.add("learnDish", new ImageView (new Image(Resources.getPath("elements/game/learnDish.png"))));
                    Resources.add("learnHose", new ImageView (new Image(Resources.getPath("elements/game/learnHose.png"))));
                    Resources.add("learnAquifer", new ImageView (new Image(Resources.getPath("elements/game/learnAquifer.png"))));
                    Resources.add("learnSink", new ImageView (new Image(Resources.getPath("elements/game/learnSink.png"))));
                    Resources.add("learnWasher2", new ImageView (new Image(Resources.getPath("elements/game/learnWasher.png"))));
                    Resources.add("learnBarrel2", new ImageView (new Image(Resources.getPath("elements/game/learnBarrel.png"))));

                    Resources.add("washerD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/washer.png"))));
                    Resources.add("barrelD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/barrel.png"))));
                    Resources.add("canD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/wcan.png"))));
                    Resources.add("showerD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/shower.png"))));
                    Resources.add("iceD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/ice.png"))));
                    Resources.add("wellD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/well.png"))));
                    Resources.add("tubD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/tub.png"))));
                    Resources.add("dwasherD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/dwasher.png"))));
                    Resources.add("dishD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/dish.png"))));
                    Resources.add("hoseD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/hose.png"))));
                    Resources.add("aquiferD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/aquifer.png"))));
                    Resources.add("sinkD", new ImageView (new Image(Resources.getPath("elements/game/descriptions/sink.png"))));

                    Resources.add("checkBack", new Image(Resources.getPath("elements/game/checkBack.png")));
                    Resources.add("checkPlay", new ImageView(new Image(Resources.getPath("elements/game/checkPlay.png"))));
                    Resources.add("checkMark", new Image(Resources.getPath("elements/game/checkMark.png")));
                    Resources.add("checkWrong", new Image(Resources.getPath("elements/game/wrong.png")));

                    Resources.add("textField", new ImageView(new Image(Resources.getPath("elements/game/winTextBox.png"))));
                    Resources.add("nameLabel", new ImageView (new Image(Resources.getPath("elements/game/winName.png"))));
                    Resources.add("saveButton", new ImageView(new Image(Resources.getPath("elements/game/winSave.png"))));
                    Resources.add("savedMsg", new ImageView (new Image(Resources.getPath("elements/game/winSaved.png"))));
                    Resources.add("winInst", new ImageView (new Image(Resources.getPath("elements/menus/winInstructions.png"))));

                    stop();
                    hideStage();
                }
            }
        }.start();
        refresh();
    }
}
