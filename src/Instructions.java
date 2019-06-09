import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Instruction class
 * This class represents the window that has all the graphics and mechanics of the instructions Window.It extends the abstract Window class to use its basic mechanics methods for displaying, hiding and clearing the screen.
 * The Window starts on the first page of the instructions, and can navigate through the pages (current page stored in the global integer page) with the left and right ImageView arrow buttons that appear at the bottom of
 * the Window. These arrows appear and disappear automatically. For example, if you are on the first page, the left back arrow won't show up, and if you are on the last page, the right forward arrow won't.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.06.2019
 *
 * <pre>
 * Version History:
 * May 22:
 * Vansh created the class and implemented the 2 instructions pages.
 * May 23:
 * Vansh fixed the title so it says Instructions instead of Highscores, and made the cursor change to a click hand when buttons are hovered over.
 * May 27:
 * Vansh added clicking sounds when buttons are pressed.
 * June 2:
 * Vansh removed old instructions, and implemented new 3 page instructions.
 * June 4:
 * Maria changed the back buttons to menu buttons to avoid confusion.
 * June 5:
 * Vansh removed all instructions again, and implemented much more detailed and visually-appealing 8-page instructions.
 * Maria implemented new Menu button with new placement, that works better with new instructions design.
 * June 6:
 * Vansh got rid of unnecessary time, x, and y variables in the AnimationTimer, and converted the clicking button property settings to lambda functions.
 * </pre>
 */
public class Instructions extends Window {
    /**
     * The choice selected for the game level that the user wishes to play.
     */
    private boolean back;

    /**
     * The page that the user is currently on of the instructions.
     */
    private int page;


    /**
     * This is the class constructor. It calls the super constructor of the Window class, and sets initial values.
     * @param stg The JavaFX Stage to display to.
     */
    public Instructions(Stage stg) {
        super(stg, "Highscores");
        back = false;
        page = 0;
    }

    /**
     * This method will display all the graphics of the Highscores window, and implement the mechanics of the left and right arrows, as well as the back button to the menu.
     */
    public void display()
    {
        // buttons
        ImageView backButton = (ImageView)(Resources.get("backButton"));
        ImageView instructionsF = (ImageView)(Resources.get("instructionsForward"));
        ImageView instructionsB = (ImageView)(Resources.get("instructionsBackward"));
        // instructions pages
        Image[] instructions = new Image[8];
        for (int i = 0; i < instructions.length; i++)
            instructions[i] = (Image)(Resources.get("instructions"+i));
        // click sound
        Sound flip = (Sound)(Resources.get("box"));

        // Listeners for MouseClicked
        backButton.setOnMouseClicked(e -> {
            refresh();
            back = true;
        });
        instructionsF.setOnMouseClicked(e -> {
            flip.play();
            page++;
            refresh();
            drawImage(instructions[page], 0, 0);

            drawImage(backButton, -410, -195);
            if (page < instructions.length-1)
                drawImage(instructionsF, 420, 300);
            if (page > 0)
                drawImage(instructionsB, -425, 300);
        });
        // update graphics to go back 1 page in the instructions (add needed)
        instructionsB.setOnMouseClicked(e -> {
            flip.play();
            page--;
            refresh();
            drawImage(instructions[page], 0, 0);

            drawImage(backButton, -410, -195);
            if (page < instructions.length-1)
                drawImage(instructionsF, 420, 300);
            if (page > 0)
                drawImage(instructionsB, -425, 300);
        });
        // Listeners for MouseEntered
        backButton.setOnMouseEntered(e -> setCursor(1));
        instructionsF.setOnMouseEntered(e -> setCursor(1));
        instructionsB.setOnMouseEntered(e -> setCursor(1));
        // Listeners for MouseExited
        backButton.setOnMouseExited(e -> setCursor(0));
        instructionsF.setOnMouseExited(e -> setCursor(0));
        instructionsB.setOnMouseExited(e -> setCursor(0));

        // draw buttons
        drawImage(backButton, -410, -195);
        drawImage(instructions[0], 0, 0);
        drawImage(instructionsF, 420, 300);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // if back button pressed, stop AnimationTimer, and hide the stage to trigger Stage.showAndWait to continue in StageManager
                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
