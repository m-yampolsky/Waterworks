import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The Window class
 * This abstract class is the superclass to which all the independent screens of the program belong. It contains the necessary methods and instance variables for displaying, opening,
 * closing and erasing the screens of a JavaFX Application. The classes Game, Highscores, Instructions, Learn, LevelSelect, Menu, Splashscreen and Quiz extend this class, as those are all
 * the various screens of the game.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * Version History:
 * May 13:
 * Vansh created the class and create its instance variables, the constructor, the clean(), hideStage(), showAndWait() and refresh() methods. He also wrote the abstract display() method.
 * May 19:
 * Maria wrote the drawImage() methods to display Image objects and ImageView objects on the screen.
 * May 22:
 * Vansh wrote the remove(Node e) method to delete and remove an object being displayed on screen.
 * May 23:
 * Vansh wrote the setCursor() method to modify the cursor and display different icons for the cursor.
 * May 24:
 * Maria wrote the getCanvas() and getScene() methods to access the private canvas and scene fields in other classes.
 * May 26:
 * Vansh added an additional option to the setCursor() method by adding another if statement. This one checks if the cursor should be displaying a closed hand, which indicates that an item is being dragged.
 * June 3:
 * Maria added the add() method to draw Node objects on to the screen. She wrote it based off of the drawImage() method, but generalising it, so that any Node object could be drawn with it.
 * She needed this method to draw Text objects on the Highscore and win screens in the Game.
 * June 4:
 * Vansh wrote the displayVideo() method to play mp4 files and other video files on the window.
 * June 5:
 * Vansh removed the displayVideo() method, as it became unnecessary after we modified the lose and win screens of the program.
 */
abstract class Window {
    /**
     * The Window's JavaFX Stage.
     */
    private final Stage stage;

    /**
     * The JavaFX Stage's StackPane on which to add components like ImageView Objects, Canvases, etc.
     */
    private final StackPane root;

    /**
     * The Canvas added to the StackPane, containing the used GraphicContext.
     */
    private final Canvas canvas;

    /**
     * The JavaFX Canvas's GraphicContext on which to draw static images, text, etc.
     */
    private final GraphicsContext gc;

    /**
     * This is the class constructor, and it uses a JavaFX Stage to set up the screen with all of its components. It adds a Canvas to the screen and makes it a blank white Canvas.
     * @param stg The JavaFX Stage to display to.
     * @param name The current Window's name
     */
    Window(Stage stg, String name) {
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
     * This method modifies the cursor being displayed on screen, and it is used throughout the program to show that some items can be clicked. It also shows when an item is being
     * dragged.
     * @param state Determines the state that the cursor is in. 1 represents a "CLICKABLE" icon, 2 represents a "DRAGGING" icon, and any other value represents a regular cursor arrow.
     */
    void setCursor(int state) {
        if (state == 1)
            stage.getScene().setCursor(Cursor.HAND);
        else if (state == 2)
            stage.getScene().setCursor(Cursor.CLOSED_HAND);
        else
            stage.getScene().setCursor(Cursor.DEFAULT);
    }

    /**
     * Draw a white rectangle covering entire GraphicContext. This method is used to clear the screen and erase all the graphics that are on it.
     */
    void clean() {
        gc.fillRect(0, 0, 1000, 750);
    }

    /**
     * This method hides the JavaFX Window Stage.
     */
    void hideStage() {
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
     * This method draws an ImageView object on to the screen. It passes the x and y coordinates of the ImageView object to draw them in the correct place on screen.
     * @param x The X position of the ImageView Object in the window.
     * @param y The Y position of the ImageView Object in the window.
     * @param img The ImageView Object to display.
     */
    void drawImage(ImageView img, int x, int y) {
        if (!root.getChildren().contains(img)) {
            img.setTranslateX(x);
            img.setTranslateY(y);
            root.getChildren().add(img);
        }
    }

    /**
     * This method draws an Image object on to the screen. It passes the x and y coordinates of the Image object to draw them in the correct place on screen.
     * @param x The X position of the Image Object in the window.
     * @param y The Y position of the Image Object in the window.
     * @param img The Image Object to display.
     */
    void drawImage(Image img, int x, int y) {
        gc.drawImage(img, x, y);
    }

    void add(Node node, int x, int y){
        if (!root.getChildren().contains(node)) {
            node.setTranslateX(x);
            node.setTranslateY(y);
            root.getChildren().add(node);
        }
    }

    /**
     * Clear Stage StackPane of all components, then re-add the Canvas. This clears and resets the screen completely.
     */
    void refresh() {
        root.getChildren().clear();
        root.getChildren().add(canvas);
    }

    /**
     * Removes an individual component from the screen.
     * @param e The Node object to be removed from the screen.
     */
    boolean remove(Node e) {
        if (root.getChildren().contains(e)) {
            root.getChildren().remove(e);
            return true;
        }
        return false;
    }

    Canvas getCanvas(){
        return canvas;
    }

    /**
     * This method returns the Window's Scene.
     * @return The window's Scene object.
     */
    Scene getScene() {
        return stage.getScene();
    }

}
