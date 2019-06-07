import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Instructions extends Window {
    /**
     * The choice selected for the game level that the user wishes to play.
     */
    private boolean back;
    private int page = 0;


    /**
     * @param stg The JavaFX Stage to display to.
     */
    public Instructions(Stage stg) {
        super(stg, "Highscores");
        back = false;
    }

    /**
     * This method will display all the graphics of the Highscores window
     */
    public void display()
    {
        ImageView backButton = (ImageView)(Resources.get("backButton"));
        Image[] instructions = new Image[8];
        for (int i = 0; i < instructions.length; i++)
            instructions[i] = (Image)(Resources.get("instructions"+i));
        ImageView instructionsF = (ImageView)(Resources.get("instructionsForward"));
        ImageView instructionsB = (ImageView)(Resources.get("instructionsBackward"));
        Sound click = (Sound)(Resources.get("click"));

        // Listeners for MouseClick
        backButton.setOnMouseClicked(e -> {
            click.play();
            refresh();
            back = true;
        });
        instructionsF.setOnMouseClicked(e -> {
            click.play();
            page++;
            refresh();
            drawImage(instructions[page], 0, 0);

            drawImage(backButton, -400, -180);
            if (page < instructions.length-1)
                drawImage(instructionsF, 420, 300);
            if (page > 0)
                drawImage(instructionsB, -425, 300);
        });
        instructionsB.setOnMouseClicked(e -> {
            click.play();
            page--;
            refresh();
            drawImage(instructions[page], 0, 0);

            drawImage(backButton, -400, -180);
            if (page < 3)
                drawImage(instructionsF, 420, 300);
            if (page > 0)
                drawImage(instructionsB, -425, 300);
        });
        // Listeners for MouseEnter
        backButton.setOnMouseEntered(e -> setCursor(1));
        instructionsF.setOnMouseEntered(e -> setCursor(1));
        instructionsB.setOnMouseEntered(e -> setCursor(1));
        // Listeners for MouseExit
        backButton.setOnMouseExited(e -> setCursor(0));
        instructionsF.setOnMouseExited(e -> setCursor(0));
        instructionsB.setOnMouseExited(e -> setCursor(0));

        drawImage(backButton, -400, -180);
        drawImage(instructions[0], 0, 0);
        drawImage(instructionsF, 420, 300);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
