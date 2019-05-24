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
    boolean lost = false;
    boolean onScreen = false;
    int x,y;
    ImageView tub = new ImageView ("elements/game/tub.png");
    ImageView sink = new ImageView ("elements/game/sink.png");
    ImageView cWasher = new ImageView ("elements/game/washer.png");
    ImageView dWasher = new ImageView ("elements/game/dishwasher.png");
    ImageView wCan = new ImageView ("elements/game/watercan.png");
    ImageView barrel = new ImageView ("elements/game/barrel.png");
    ImageView dSponge = new ImageView ("elements/game/dish.png");
    ImageView hose = new ImageView ("elements/game/hose.png");
    ImageView shower = new ImageView ("elements/game/shower.png");



    public Quiz(Stage stg) {
        super(stg, "Quiz");
        effDevices = new ArrayList<ImageView>();
        ineffDevices = new ArrayList<ImageView>();
    }

    public void display() {
        ImageView background = (ImageView) (Resources.get("quizBack"));
        ImageView menuBtn = (ImageView)(Resources.get("menuBtn"));

        ImageView boxBack1 = (ImageView)(Resources.get("boxBack"));
        ImageView boxBack2 = (ImageView)(Resources.get("boxBack2"));
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

        drawImage (boxBack1, -350, 187);
        drawImage (boxBack2, 360, 187);
        drawImage (eBox, -350, 200);
        drawImage (iBox, 360, 200);

        ImageView[] devices = {dSponge, sink, shower, wCan, barrel, hose, cWasher, tub, dWasher};

        for (ImageView img : devices)
        {
            resetMouse (img);
        }


        eBox.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                lost = false;
                onScreen = true;
            }
        });
        iBox.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                lost = false;
                onScreen = true;
            }
        });
        boxBack1.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                lost = false;
                onScreen = true;
            }
        });
        boxBack2.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                lost = false;
                onScreen = true;
            }
        });
        background.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                lost = false;
                onScreen = true;
            }
        });
        menuBtn.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                lost = false;
                onScreen = true;
            }
        });


        background.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
            }
        });

        eBox.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
            }
        });
        iBox.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
            }
        });
        boxBack1.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
            }
        });
        boxBack2.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
            }
        });
        menuBtn.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
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
                event.setDragDetect(false);
                lost = false;
                onScreen = true;
            }
        });
        iBox.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (device);
                drawImage (device, 360, 150);
                remove (iBox);
                drawImage (iBox, 360, 200);
                device = null;
                event.setDragDetect(false);
                lost = false;
                onScreen = true;
            }
        });
        boxBack1.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (device);
                drawImage (device, -350, 150);
                remove (eBox);
                drawImage (eBox, -350, 200);
                device = null;
                event.setDragDetect(false);
                lost = false;
                onScreen = true;
            }
        });
        boxBack2.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (device);
                drawImage (device, 360, 150);
                remove (iBox);
                drawImage (iBox, 360, 200);
                device = null;
                event.setDragDetect(false);
                lost = false;
                onScreen = true;
            }
        });

        background.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
                event.setDragDetect(false);
                onScreen = false;
            }
        });
        menuBtn.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
                event.setDragDetect(false);
                onScreen = false;
            }
        });

        menuBtn.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(true);
            }
        });
        menuBtn.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(false);
            }
        });

       final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;
                if (device != null && lost || (x >= 1000 || x < 0 || y >= 750 || y < 0) && device != null)
                {
                    remove (device);
                    if (device.equals(dSponge)){
                        dSponge = new ImageView ("elements/game/dish.png");
                        drawImage (dSponge, -110, -185 );
                        resetMouse (dSponge);
                    }
                    else if (device.equals(sink)){
                        sink = new ImageView ("elements/game/sink.png");
                        drawImage (sink,17, -179 );
                        resetMouse (sink);}
                    else if (device.equals(shower)){
                        shower = new ImageView ("elements/game/shower.png");
                        drawImage (shower, 128, -188);
                        resetMouse (shower);}
                    else if (device.equals(wCan)){
                        wCan = new ImageView ("elements/game/waterCan.png");
                        drawImage (wCan, -90, -52);
                        resetMouse (wCan);}
                    else if (device.equals(barrel)){
                        barrel = new ImageView ("elements/game/barrel.png");
                        drawImage (barrel, 37, -52);
                        resetMouse (barrel);}
                    else if (device.equals(hose)){
                        hose = new ImageView ("elements/game/hose.png");
                        drawImage (hose, 138, -48);
                        resetMouse (hose);}
                    else if (device.equals(cWasher)){
                        cWasher = new ImageView ("elements/game/washer.png");
                        drawImage (cWasher, -130, 75);
                        resetMouse (cWasher);}
                    else if (device.equals(tub)){
                        tub = new ImageView ("elements/game/tub.png");
                        drawImage (tub, 5, 82);
                        resetMouse (tub);}
                    else if (device.equals(dWasher)){
                        dWasher = new ImageView ("elements/game/dishwasher.png");
                        drawImage (dWasher, 145, 76);
                        resetMouse (dWasher);}
                    lost = false;
                    isDragged = false;
                    device = null;
                }

                if (isDragged && device != null && !lost && x < 1000 && x > 0 && y < 750 && y > 0) {
                    remove(device);
                    drawImage(device, x - 500, y - 375);
                    onScreen = false;
                }

            }
        }.start();
    }

    public void resetMouse (ImageView img)
    {
        img.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(true);
                onScreen = true;
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(false);
                onScreen = true;
            }
        });
        img.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                img.setMouseTransparent(true);
                isDragged = false;
                device = img;
                event.setDragDetect(true);
                lost = false;
                onScreen = true;
            }
        });

        img.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (device != null)
                {
                    img.setMouseTransparent(false);
                    isDragged = false;
                    device = null;
                }
                else
                {
                    lost = true;
                }
                onScreen = true;
            }
        });

        img.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                event.setDragDetect(false);
                x = (int) event.getSceneX();
                y = (int) event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
            }
        });
        img.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
                onScreen = true;
            }
        });
        img.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                img.startFullDrag();
                lost = false;
                onScreen = true;
            }
        });
        img.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
                event.setDragDetect(false);
                onScreen = true;
            }
        });
    }
}
