import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;

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
    int x, y;
    ImageView tub = new ImageView ("elements/game/tub.png");
    ImageView sink = new ImageView ("elements/game/sink.png");
    ImageView cWasher = new ImageView ("elements/game/washer.png");
    ImageView dWasher = new ImageView ("elements/game/dishwasher.png");
    ImageView wCan = new ImageView ("elements/game/watercan.png");
    ImageView barrel = new ImageView ("elements/game/barrel.png");
    ImageView dSponge = new ImageView ("elements/game/dish.png");
    ImageView hose = new ImageView ("elements/game/hose.png");
    ImageView shower = new ImageView ("elements/game/shower.png");
    ImageView border = new ImageView ("elements/game/border.png");


    public Quiz(Stage stg) {
        super(stg, "Quiz");
        effDevices = new ArrayList<ImageView>();
        ineffDevices = new ArrayList<ImageView>();
    }

    public void display() {
        Canvas thisCan = getCanvas();

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

        drawImage (border, 0,0);

        ImageView[] devices = {dSponge, sink, shower, wCan, barrel, hose, cWasher, tub, dWasher};

        for (ImageView img : devices)
        {
            resetMouse (img);
        }

        //border reaction
        border.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
            }
        });
        border.setOnMouseDragOver(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                lost = true;
            }
        });
        border.setOnMouseDragExited(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
            }
        });
        border.setOnMouseEntered(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                lost = true;
            }
        });
        border.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
                event.setDragDetect(false);
            }
        });



        eBox.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                isDragged = true;
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                lost = false;
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
                
            }
        });

        background.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
                event.setDragDetect(false);
            }
        });
        menuBtn.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
                event.setDragDetect(false);
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

                if (isDragged && device != null && !lost) {
                    remove(device);

                    if (device.equals(dSponge)){
                        dSponge = new ImageView ("elements/game/dish.png");
                        device = dSponge;
                    }
                    else if (device.equals(sink)){
                        sink = new ImageView ("elements/game/sink.png");
                        device = sink;}
                    else if (device.equals(shower)){
                        shower = new ImageView ("elements/game/shower.png");
                        device = shower;}
                    else if (device.equals(wCan)){
                        wCan = new ImageView ("elements/game/waterCan.png");
                        device = wCan;}
                    else if (device.equals(barrel)){
                        barrel = new ImageView ("elements/game/barrel.png");
                        device = barrel;}
                    else if (device.equals(hose)){
                        hose = new ImageView ("elements/game/hose.png");
                        device = hose;}
                    else if (device.equals(cWasher)){
                        cWasher = new ImageView ("elements/game/washer.png");
                        device = cWasher;}
                    else if (device.equals(tub)){
                        tub = new ImageView ("elements/game/tub.png");
                        device = tub;}
                    else if (device.equals(dWasher)){
                        dWasher = new ImageView ("elements/game/dishwasher.png");
                        device = dWasher;}

                    if (x < thisCan.getWidth() && x > 0 && y > 0 && y < thisCan.getHeight()) {
                        resetMouse (device);
                        drawImage(device, x - (int)thisCan.getWidth()/2, y - (int)thisCan.getHeight()/2);
                    }
                    else{
                        resetMouse (device);
                        if (device.equals(dSponge)){
                            drawImage (dSponge, -110, -185 );
                        }
                        else if (device.equals(sink)){
                            drawImage (sink,17, -179 );}
                        else if (device.equals(shower)){
                            drawImage (shower, 128, -188);}
                        else if (device.equals(wCan)){
                            drawImage (wCan, -90, -52);}
                        else if (device.equals(barrel)){
                            drawImage (barrel, 37, -52);}
                        else if (device.equals(hose)){
                            drawImage (hose, 138, -48);}
                        else if (device.equals(cWasher)){
                            drawImage (cWasher, -130, 75);}
                        else if (device.equals(tub)){
                            drawImage (tub, 5, 82);}
                        else if (device.equals(dWasher)){
                            drawImage (dWasher, 145, 76);}
                        device = null;
                        lost = false;
                        isDragged = false;
                    }
                }

                if (device != null && lost)
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

            }
        }.start();
    }

    public void resetMouse (ImageView img)
    {
        img.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(true);
                
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(false);
                
            }
        });
        img.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                img.setMouseTransparent(true);
                isDragged = false;
                device = img;
                event.setDragDetect(true);
                lost = false;
                
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
                
            }
        });

        img.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                event.setDragDetect(false);
                x = (int) event.getSceneX();
                y = (int) event.getSceneY();
                isDragged = true;
                lost = false;
                
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
                
            }
        });
        img.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                img.startFullDrag();
                lost = false;
                
            }
        });
        img.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                lost = true;
                event.setDragDetect(false);
                
            }
        });
    }
}
