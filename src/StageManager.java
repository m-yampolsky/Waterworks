import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaException;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The StageManager class
 * This class puts together all the windows of the program, and organises the program flow.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class StageManager extends Application {

    /**
     * This method will load all the necessary resources into a hashmap, open all the necessary windows and will control the program's flow.
     */
    public void start(Stage stg) {
        // load resources
        AnimatedImage splashscreen = new AnimatedImage("elements/splash/splash", 170, 0.100);
        Resources.add("splashscreen", splashscreen);
        Resources.add("menuBackground", new Image("elements/menus/background.png"));
        Resources.add("backLog", new Image("elements/menus/backgroundLog.png"));
        Resources.add("menuTitle", new Image("elements/menus/menuLogo.png"));
        Resources.add("learnBtn", new ImageView("elements/menus/menuLearnBtn.png"));
        Resources.add("quizBtn", new ImageView("elements/menus/menuQuizBtn.png"));
        Resources.add("playBtn", new ImageView("elements/menus/menuPlayBtn.png"));
        Resources.add("exitBtn", new ImageView("elements/menus/menuExitBtn.png"));
        Resources.add("instructionsBtn", new ImageView("elements/menus/menuInstructionsBtn.png"));
        Resources.add("instructionsTitle", new Image("elements/menus/instructionsTitle.png"));
        Resources.add("highscoresBtn", new ImageView("elements/menus/menuHighscoresBtn.png"));
        Resources.add("instructions1", new ImageView ( "elements/menus/instructions1.png" ));
        Resources.add("instructions2", new ImageView ( "elements/menus/instructions2.png" ));
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

        Resources.add("superiorLake", new Image( "elements/game/superiorLake.png" ));
        Resources.add("superiorBack", new Image( "elements/game/superiorBack.png" ));
        Resources.add("superiorTrees", new Image("elements/game/superiorTrees.png"));
        Resources.add("superiorLogImg", new ImageView ( "elements/game/superiorLogLine.png" ));
        Resources.add("superiorLogLine", new LogLine("elements/game/superiorLogLine.png"));

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

        Resources.add("oxfordComma", new Sound("elements/oxfordComma.mp3"));
        Resources.add("hotel", new Sound("elements/hotel.mp3"));
        Resources.add("click", new Sound ("elements/click.mp3"));

        Stage stage = new Stage();
        stage.setTitle("Waterworks");
        stage.setMinHeight(790);
        stage.setMinWidth(1016);
        stage.setMaxHeight(790);
        stage.setMaxWidth(1016);

        int quizScore = 0;

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 750, Color.WHITE);


        Canvas canvas = new Canvas(1000, 750);
        root.getChildren().add(canvas);

        stage.setOnCloseRequest((WindowEvent event1) -> {
            System.exit(0);
        });

        stage.setScene(scene);


        SplashScreen s = new SplashScreen(stage);
        stage.hide();
        s.display();
        stage.showAndWait();

        Sound hotel = (Sound)(Resources.get("hotel"));
        Sound oxford = (Sound)(Resources.get("oxfordComma"));
       oxford.loop();
       hotel.loop();


        int c = 0;
        while (c != -1) {
            Menu m = new Menu(stage);
           hotel.play();
            c = 0;
            try {
                c = m.getChoice();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (c == 1) {
                Learn l = new Learn(stage);
                l.display();
                stage.showAndWait();
                if (l.quizButtonClicked)
                    c = 2;
            }
            if (c == 2) {
                Quiz q = new Quiz (stage);
                q.display();
                q.showAndWait();
                quizScore = q.getScore();
                if (q.playButtonClicked)
                    c = 3;
            }
            if (c == 3) {
                LevelSelect l = new LevelSelect(stage);
                int lvl = 0;
                try {
                    lvl = l.getChoice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (lvl == -1)
                    continue;
                else if (lvl >= 1 && lvl <= 3) {
                   hotel.stop();
                   oxford.play();
                    while (true) {
                        Game g = new Game(stage, lvl);
                        g.getScore();
                        if (g.endStatus == 0 || g.endStatus == -1)
                            break;
                        else if (g.endStatus == 2)
                            lvl = Math.min(3, lvl+1);
                    }
                 oxford.stop();
                }
            } else if  (c == 4) {
                Highscores h = new Highscores(stage);
                h.display();
                stage.showAndWait();
            } else if  (c == 5) {
                Instructions i = new Instructions(stage);
                i.display();
                stage.showAndWait();


            }
            else if (c == -1)
                break;
        }
        stage.close();

    }
}
