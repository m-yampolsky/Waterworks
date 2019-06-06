import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

/**
 * The Window class
 * This abstract class is the superclass to which all the independent screens of the program belong.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
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

    public void setCursor (int state) {
        if (state == 1)
            stage.getScene().setCursor(Cursor.HAND);
        else if (state == 2)
            stage.getScene().setCursor(Cursor.CLOSED_HAND);
        else
            stage.getScene().setCursor(Cursor.DEFAULT);
    }

    /**
     * Draw a white rectangle covering entire GraphicContext.
     */
    public void clean() {
        gc.fillRect(0, 0, 1000, 750);
    }

    /**
     * Hide the JavaFX Window Stage.
     */
    public void hideStage() {
        stage.hide();
    }

    /**
     * Hide the Stage in case it isn't already hidden and then show it, and wait
     * until it is hidden once again before continuing with the current Thread.
     */
    public void showAndWait () {
        stage.hide();
        stage.showAndWait();
    }

    /**
     * @param x The X-pos translation of the ImageView Object in the window.
     * @param y The Y-pos tanslation of the ImageView Object in the window.
     * @param img The ImageView Object to display.
     */
    public void drawImage (ImageView img, int x, int y) {
        if (!root.getChildren().contains(img)) {
            img.setTranslateX(x);
            img.setTranslateY(y);
            root.getChildren().add(img);
        }
    }

    public void drawRect(int startX, int startY, int width, int height) {
        gc.setFill(Color.RED);
        gc.fillRect(startX, startY, width, height);
    }

    /**
     * @param x The X-pos translation of the Image Object in the window.
     * @param y The Y-pos tanslation of the Image Object in the window.
     * @param img The Image Object to display.
     */
    public void drawImage (Image img, int x, int y) {
        gc.drawImage(img, x, y);
    }

    public void add (Node node, int x, int y){
        if (!root.getChildren().contains(node)) {
            node.setTranslateX(x);
            node.setTranslateY(y);
            root.getChildren().add(node);
        }
    }

    /**
     * Clear Stage StackPane of all components, then re-add the Canvas
     */
    public void refresh () {
        root.getChildren().clear();
        root.getChildren().add(canvas);
    }

    /**
     * Clear Stage StackPane of all components, then re-add the Canvas
     */
    public boolean remove (Node e) {
        if (root.getChildren().contains(e)) {
            root.getChildren().remove(e);
            return true;
        }
        return false;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public Scene getScene() {
        return stage.getScene();
    }

    /**
     * Display method that must be implemented by all subclasses containing the Window content.
     */
    public abstract void display ();
}
