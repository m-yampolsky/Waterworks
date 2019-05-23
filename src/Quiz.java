import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.input.*;
import javafx.event.EventHandler;

/**
 * The Quiz class
 * This class represents the Quiz window of the game where the user is tested on what they learned about water conservation
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.20.2019
 */
public class Quiz extends Window {

    ImageView device = null;
    ArrayList<ImageView> effDevices;
    ArrayList<ImageView> ineffDevices;
    boolean isDragged = false;
    boolean reachedBox = false;
    int x,y;
    MouseEvent event;

    public Quiz(Stage stg) {
        super(stg, "Quiz");
        effDevices = new ArrayList<ImageView>();
        ineffDevices = new ArrayList<ImageView>();
    }

    public void display() {

        ImageView background = (ImageView) (Resources.get("quizBack"));
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
        Image dSpongeImg = new Image ("elements/game/dish.png");

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


        ImageView[] devices = {dSponge, sink, shower, wCan, barrel, hose, cWasher, tub, dWasher};

        for (ImageView img : devices)
        {
            img.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    img.setMouseTransparent(true);
                    isDragged = false;
                    device = img;
                    event.setDragDetect(true);
                }
            });

            img.setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    img.setMouseTransparent(false);
                    isDragged = false;
                    device = null;
                }
            });

            img.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    event.setDragDetect(false);
                    x = (int) event.getSceneX();
                    y = (int) event.getSceneY();
                    isDragged = true;
                }
            });

            img.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    img.startFullDrag();
                }
            });
        }


        eBox.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
            }
        });

        background.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
            }
        });

        eBox.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
            }
        });

        eBox.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (device);
                drawImage (device, -350, 150);
                remove (eBox);
                drawImage (eBox, -350, 200);
                device = null;
            }
        });

        background.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (device);
                drawImage (device,-110, -185  );
            }
        });
        
        eBox.setOnMouseDragExited(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
            }
        });




       final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                if (isDragged && device != null) {
                    remove(device);
                    drawImage(device, x - 500, y - 375);
                }
            }
        }.start();
    }
}
