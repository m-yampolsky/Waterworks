import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Quiz class
 * This class represents the Quiz window of the game where the user is tested on what they learned about water conservation
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.20.2019
 */
public class Quiz extends Window {

    public Quiz(Stage stg) {
        super(stg, "Quiz");
    }

    public void display() {
        Image background = (Image) (Resources.get("quizBack"));
        ImageView menuBtn = (ImageView)(Resources.get("menuBtn"));

        ImageView tub = (ImageView)(Resources.get("tub"));
        ImageView sink = (ImageView)(Resources.get("sink"));
        ImageView cWasher = (ImageView)(Resources.get("cWasher"));
        ImageView dWasher = (ImageView)(Resources.get("dWasher"));
        ImageView wCan = (ImageView)(Resources.get("wCan"));
        ImageView barrel = (ImageView)(Resources.get("barrel"));
        ImageView dSponge = (ImageView)(Resources.get("dSponge"));
        ImageView hose = (ImageView)(Resources.get("hose"));
        ImageView shower = (ImageView)(Resources.get("shower"));

        ImageView eBox = (ImageView)(Resources.get("effBox"));
        ImageView iBox = (ImageView)(Resources.get("ineffBox"));

        menuBtn.setOnMouseClicked(e -> {
            hideStage();
        });

        drawImage(background, 0, 0);
        drawImage(menuBtn, 400, -330);

        drawImage (dSponge, -110, -185 );
        drawImage (sink,17, -179 );
        drawImage (shower, 128, -188);

        drawImage (wCan, -90, -52);
        drawImage (barrel, 37, -52);
        drawImage (hose, 138, -48);

        drawImage (cWasher, -130, 75);
        drawImage (tub, 5, 82);
        drawImage (dWasher, 145, 76);

        drawImage (eBox, -350, 200);
        drawImage (iBox, 360, 200);
    }
}
