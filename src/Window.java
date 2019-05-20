import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class Window {
    private Stage stage;
    private GraphicsContext gc;
    private StackPane root;
    private Canvas canvas;

    public Window (Stage stg, String name) {
        stage = stg;

        stage.setTitle("Waterworks - " + name);

        root = new StackPane();
        Scene scene = new Scene(root, 1000, 750, Color.WHITE);

        canvas = new Canvas(1000, 750);
        root.getChildren().add(canvas);

        stage.setScene(scene);
        gc = canvas.getGraphicsContext2D();
    }

    public GraphicsContext gc() {
        return gc;
    }

    public void hideStage() {
        stage.hide();
    }

    public void showAndWait () {
        stage.hide();
        stage.showAndWait();
    }

    public void drawImage (ImageView i, int x, int y) {
        i.setTranslateX(x);
        i.setTranslateY(y);
        root.getChildren().add(i);
    }

    public void drawImage (Image i, int x, int y) {
        gc.drawImage(i, x, y);
    }

    public void refresh () {
        root.getChildren().clear();
        root.getChildren().add(canvas);
    }

    public abstract void display ();
}
