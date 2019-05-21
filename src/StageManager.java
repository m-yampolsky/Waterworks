import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
  * The StageManager class
  * This class puts together all the windows of the program, and organises the program flow.
  * @author Maria Yampolsky and Vansh Juneja
  * @version 1 05.20.2019
  */
public class StageManager extends Application {

    /**
    * This method will load all the necessary resources into a hashmap, open all the necessary windows and will control the program's flow.
    */
    public void start(Stage stg) {
        // load resources
        Image[] imageArray = new Image[180];
        for (int i = 1; i <= 170; i++)
            imageArray[i - 1] = new Image("elements/splash/splash (" + i + ").png");
        AnimatedImage splashscreen = new AnimatedImage(imageArray, 0.100);
        imageArray = new Image[180];
        for (int i = 1; i <= 180; i++)
            imageArray[i - 1] = new Image("elements/standing/standing (" + i + ").png");
        AnimatedImage standing = new AnimatedImage(imageArray, 0.100);
        Resources.add("splashscreen", splashscreen);
        Resources.add("hotel", new Music("src/elements/hotel.mp3"));
        Resources.add("standing", standing);
        Resources.add("menuBackground", new Image("elements/menus/background.png"));
        Resources.add("backLog", new Image("elements/menus/backgroundLog.png"));
        Resources.add("menuTitle", new Image("elements/menus/menuLogo.png"));
        Resources.add("learnBtn", new ImageView("elements/menus/menuLearnBtn.png"));
        Resources.add("quizBtn", new ImageView("elements/menus/menuQuizBtn.png"));
        Resources.add("playBtn", new ImageView("elements/menus/menuPlayBtn.png"));
        Resources.add("exitBtn", new ImageView("elements/menus/menuExitBtn.png"));
        Resources.add("instructionsBtn", new Image("elements/menus/menuInstructionsBtn.png"));
        Resources.add("highscoresBtn", new Image("elements/menus/menuHighscoresBtn.png"));Image background = new Image ("elements/menus/background.png");
        Resources.add("playTitle", new Image("elements/menus/playTitle.png"));
        Resources.add("lakeOntario", new ImageView("elements/menus/playLakeOntarioBtn.png"));
        Resources.add("lakeErie", new ImageView("elements/menus/playLakeErieBtn.png"));
        Resources.add("lakeSuperior", new ImageView("elements/menus/playLakeSuperiorBtn.png"));
        Resources.add("backButton", new ImageView("elements/menus/backBtn.png"));
        Resources.add("ontarioLake", new Image( "elements/game/lake.png" ));
        Resources.add("ontarioBack", new Image( "elements/game/ontarioBack.png" ));
        Resources.add("ontarioToronto", new Image( "elements/game/ontarioToronto.png" ));
        Resources.add("ontarioLogImg", new ImageView( "elements/game/ontarioLogLine.png" ));
        Resources.add("ontarioLogLine", new LogLine("elements/game/ontarioLogLine.png"));
        Resources.add("avatarImg", new GameChar ("elements/game/backgroundChar.png" ));
        Resources.add("avatar", new ImageView ((GameChar)(Resources.get("avatarImg"))));
        Resources.add("oxfordComma", new Music("src/elements/oxfordComma.wav"));



        Stage stage = new Stage();
        stage.setTitle("Waterworks");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 750, Color.WHITE);

        Canvas canvas = new Canvas(1000, 750);
        root.getChildren().add(canvas);

        stage.setOnCloseRequest((WindowEvent event1) -> {
            System.exit(0);
        });

        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        SplashScreen s = new SplashScreen(stage);
        stage.hide();
        s.display();
        stage.showAndWait();

        Music hotel = (Music)(Resources.get("hotel"));
        hotel.play();
        hotel.loop();

        Menu m = new Menu(stage);
        int c = 0;
        while (c != -1) {
            try {
                c = m.getChoice();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (c == 1) {
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
                    hotel.pause();
                    Game g = new Game (stage, lvl);
                    try {
                        g.getScore();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        stage.close();
    }
}
