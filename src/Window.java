import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 */
public abstract class Window {
    /**
     * The Window's JavaFX Stage.
     */
    private Stage stage;

    /**
     * The JavaFX Stage's StackPane on which to add components like ImageView Objects, Canvases, etc.
     */
    private StackPane root;

    /**
     * The Canvas added to the StackPane, containing the used GraphicContext.
     */
    private Canvas canvas;

    /**
     * The JavaFX Canvas's GraphicContext on which to draw static images, text, etc.
     */
    private GraphicsContext gc;

    /**
     * @param stg The JavaFX Stage to display to.
     * @param name The current Window's name
     */
    public Window (Stage stg, String name) {
        stage = stg;

        stage.setTitle("Waterworks - " + name);

        root = new StackPane();
        Scene scene = new Scene(root, 1000, 750, Color.WHITE);

        canvas = new Canvas(1000, 750);
        root.getChildren().add(canvas);

        stage.setScene(scene);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
    }

    /**
     * Draw a white rectangle covering entire GraphicContext.
     */
    public void clean() {
        gc.fillRect(0, 0, 1000, 750);
    }

    /**
     *
     */
    public void hideStage() {
        stage.hide();
    }

    /**
     *
     */
    public void showAndWait () {
        stage.hide();
        stage.showAndWait();
    }

    /**
     * @param img
     * @param x
     * @param y
     */
    public void drawImage (ImageView img, int x, int y) {
        img.setTranslateX(x);
        img.setTranslateY(y);
        root.getChildren().add(img);
    }

    /**
     * @param img
     * @param x
     * @param y
     */
    public void drawImage (Image img, int x, int y) {
        gc.drawImage(img, x, y);
    }

    /**
     *
     */
    public void refresh () {
        root.getChildren().clear();
        root.getChildren().add(canvas);
    }

    /**
     *
     */
    public abstract void display ();
}
