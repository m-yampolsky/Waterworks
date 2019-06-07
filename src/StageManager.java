import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
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
                    while (true) {
                        oxford.play();
                        Game g = new Game(stage, lvl);
                        g.run();
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
