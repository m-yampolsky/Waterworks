import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The LevelSelect class
 * This class represents the LevelSelect window where the user will choose which level they want to play. Three different ImageView Object buttons showing previews of all 3 levels are displayed.
 * Clicking on any of these, will take ou to this level in the Game class. There is also an ImageView button under the title to go back to the menu, backButton. There is an AnimatedImage of
 * Bayou (the cactus) standing happily in the bottom left on the background log, just like the Menu window.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.06.2019
 *
 * <pre>
 * Version History:
 * May 17:
 * Vansh created the class.
 * May 19:
 * Maria implemented the title, menu ImageView button, level ImageView buttons, and Bayou's standing animation.
 * May 23:
 * Vansh implemented change of cursor to click hand when hovering over buttons.
 * </pre>
 */
public class LevelSelect extends Window {

    /**
     * The choice selected for the game level that the user wishes to play.
     */
    private int choice;

    /**
     * This is the class constructor. It calls the super constructor of the Window class, and sets initial values.
     * @param stg The JavaFX Stage to display to.
     */
    public LevelSelect(Stage stg) {
        super(stg, "Level Select");
        choice = 0;
    }

    /**
     * This is the method that shows the levelSelect graphics, gets the user's choice, and returns it to the StageManager
     * @return The level that the user has chosen to play.
     */
    public int getChoice() {
        display();
        showAndWait();
        refresh();
        return choice;
    }

    /**
     * This method will display all the graphics of the LevelSelect window. It allows the user to click one of the levels shown, or the back button to return back to the Menu Window.
     */
    public void display()
    {
        Image background = (Image)(Resources.get("menuBackground"));
        Image playTitle = (Image)(Resources.get("playTitle"));
        Image menuBackgroundLog = (Image)(Resources.get("backLog"));
        ImageView lakeOntario = (ImageView)(Resources.get("lakeOntario"));
        ImageView lakeErie = (ImageView)(Resources.get("lakeErie"));
        ImageView lakeSuperior = (ImageView)(Resources.get("lakeSuperior"));
        ImageView backButton = (ImageView)(Resources.get("backButton"));

        // Listeners for MouseClicked
        backButton.setOnMouseClicked(e -> {
            refresh();
            choice = -1;
        });
        lakeOntario.setOnMouseClicked(e -> {
            refresh();
            choice = 1;
        });
        lakeErie.setOnMouseClicked(e -> {
            refresh();
            choice = 2;
        });
        lakeSuperior.setOnMouseClicked(e -> {
            refresh();
            choice = 3;
        });
        // Listeners for MouseEntered
        backButton.setOnMouseEntered(e -> setCursor(1));
        lakeOntario.setOnMouseEntered(e -> setCursor(1));
        lakeErie.setOnMouseEntered(e -> setCursor(1));
        lakeSuperior.setOnMouseEntered(e -> setCursor(1));
        // Listeners for MouseExited
        backButton.setOnMouseExited(e -> setCursor(0));
        lakeOntario.setOnMouseExited(e -> setCursor(0));
        lakeErie.setOnMouseExited(e -> setCursor(0));
        lakeSuperior.setOnMouseExited(e -> setCursor(0));

        // draw level buttons and back button
        drawImage(lakeOntario, -30, 100);
        drawImage(lakeErie, 170, 100);
        drawImage(lakeSuperior, 370, 100);
        drawImage(backButton, -380, -160);

        // Bayou standing animation
        AnimatedImage standing = (AnimatedImage)(Resources.get("standing"));

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                // background image clears canvas
                drawImage(background, 0, 0);
                // background log, standing animation, and title
                drawImage(menuBackgroundLog, 0, 690);
                drawImage(playTitle, 40, 30);
                drawImage(standing.getFrame(t), -90, 275);

                // stop AnimationTimer and execute hideStage method if Window class to trigger stage.showAndWait() in StageManager to continue.
                if (choice != 0) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
