import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StageManager extends Application {

    public void start(Stage stg) {
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
        s.display();
        stage.showAndWait();

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
                l.display();
                stage.showAndWait();
                int lvl = 0;
                try {
                    lvl = l.getChoice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (lvl == -1)
                    continue;
                else if (lvl >= 1 && lvl <= 3) {
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
