import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The StageManager class
 * This class puts together all the windows of the program, and organises the program flow. It does this by loading the splashscreen AnimatedImage as well as the Sound objects used in the class.
 * The class then sets-up the JavaFX Stage, and runs the proper methods of specific classes to show and hide Windows, whilst stopping and playing the music Sound Objects.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * <h2>Total Time Spent: 7 hours</h2>
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 6 06.09.2019
 *
 * <pre>
 * Version History:
 * May 17:
 * Vansh created StageManager class and implemented Stage setup + Menu and Splashscreen. -- 1.5 hours
 * May 19:
 * Maria implemented LevelSelect and Game Windows. -- 0.25 hours
 * May 20:
 * Vansh implemented Resource Loading and Menu Music. -- 0.5 hours
 * May 21:
 * Vansh implemented Highscores Window. -- 0.25 hours
 * Maria implemented the back buttons to go back to Menu from other rooms and added resources for LogLines. -- 0.25 hours
 * May 22:
 * Vansh moved the playing of Game level music, into StageManager and implemented the Instructions Window (as well as its Resources). -- 0.75 hours
 * Maria implemented the Quiz Window and Quiz + more Game Resources. -- 0.5 hours
 * May 23:
 * Vansh implemented Learn Window + Learn Resources. -- 0.25 hours
 * Maria added Quiz Resources. -- 0.25 hours
 * May 24:
 * Vansh added Stage constraints so that the Window cannot be resized, and fixed bug where border would show up around window when Window set to unresizable. -- 1.5 hour
 * May 27:
 * Vansh added a click Sound to Resources. -- 0.1 hours
 * May 29:
 * Maria added Resources for the Quiz Check screen. -- 0.25 hours
 * May 31:
 * Vansh added 2 new Learn screen Device graphics to Resources. -- 0.15 hours
 * June 1:
 * Vansh added Lake Superior and Lake Erie Game graphics to Resources. -- 0.25 hours
 * June 2:
 * Vansh moved all Resource loading to the Splashscreen Window's loading screen -- 0.25 hours
 * </pre>
 */
public class StageManager extends Application {

    /**
     * This method will load all the necessary Sound resources into the Resources class, open all the necessary windows and will control the program's flow.
     * @param stg The JavaFX Stage to display to.
     */
    public void start(Stage stg) {
        // load resources
        Resources.add("oxfordComma", new Sound("elements/oxfordComma.mp3"));
        Resources.add("hotel", new Sound("elements/hotel.mp3"));
        Resources.add("right", new Sound("elements/right.mp3"));
        Resources.add("wrong", new Sound ("elements/wrong.mp3"));
        Resources.add("box", new Sound ("elements/box.mp3"));
        Resources.add("click", new Sound ("elements/click.mp3"));

        // Stage, Stage title, and sizing contraints
        Stage stage = new Stage();
        stage.setTitle("Waterworks");
        stage.setMinHeight(790);
        stage.setMinWidth(1016);
        stage.setMaxHeight(790);
        stage.setMaxWidth(1016);

        // Pane on which to add ImageView Objects
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 750, Color.WHITE);


        Canvas canvas = new Canvas(1000, 750);
        root.getChildren().add(canvas);

        stage.setOnCloseRequest((WindowEvent event1) -> System.exit(0));
        stage.getIcons().add(new Image(Resources.getPath("elements/icon.png")));
        stage.setScene(scene);


        SplashScreen s = new SplashScreen(stage);
        stage.hide();
        s.display();
        stage.showAndWait();

        Sound hotel = (Sound)(Resources.get("hotel"));
        Sound oxford = (Sound)(Resources.get("oxfordComma"));


        int c = 0; // choice variable
        while (c != -1) {
            Menu m = new Menu(stage);
            hotel.play();
            c = m.getChoice();
            if (c == 1) {
                Learn l = new Learn(stage);
                l.display();
                stage.showAndWait();
                if (l.getQuizButtonClicked())
                    c = 2;
            }
            if (c == 2) {
                Quiz q = new Quiz (stage);
                q.display();
                q.showAndWait();
                if (q.getPlayButtonClicked())
                    c = 3;
            }
            if (c == 3) {
                LevelSelect l = new LevelSelect(stage);
                int lvl = 0;
                lvl = l.getChoice();
                if (lvl >= 1 && lvl <= 3) {
                    hotel.stop();
                    while (true) {
                        oxford.play();
                        Game g = new Game(stage, lvl);
                        g.run();
                        if (g.getEndStatus() == 0 || g.getEndStatus() == -1)
                            break;
                        else if (g.getEndStatus() == 2)
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
