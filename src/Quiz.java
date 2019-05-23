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

        
        //mouse pressed actions
        /*dSponge.setOnMouseClicked(e -> {
            device = dSponge;
            e.setDragDetect (true);
            isDragged = true;
        });

        dSponge.setOnDragDetected(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                // drag was detected, start drag-and-drop gesture
                System.out.println("onDragDetected");
                device = dSponge;
                // allow any transfer mode
                Dragboard db = dSponge.startDragAndDrop(TransferMode.ANY);

                // put a string on dragboard
                ClipboardContent content = new ClipboardContent();
                content.putImage(dSpongeImg);
                db.setContent(content);

                event.consume();
            }
        });


        eBox.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // data is dragged over the target
                System.out.println("onDragOver");

                // accept it only if it is  not dragged from the same node and if it has a string data
                if (event.getGestureSource() != eBox &&
                        event.getDragboard().hasImage()) {
                    // allow for both copying and moving, whatever user chooses
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                remove (device);
                drawImage (device, -350, 200);

                event.consume();
            }
        });


        eBox.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // the drag-and-drop gesture entered the target
                System.out.println("onDragEntered");
                // show to the user that it is an actual gesture target
                if (event.getGestureSource() != eBox &&
                        event.getDragboard().hasImage()) {
                    remove (device);
                }

                event.consume();
            }
        });

        eBox.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // mouse moved away, remove the graphical cues
                drawImage (dSponge, -110, -185 );

                event.consume();
            }
        });

        eBox.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // data dropped
                System.out.println("onDragDropped");
                // if there is a string data on dragboard, read it and use it
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    remove (device);
                    success = true;
                    drawImage (device, -350, 200);
                }
                // let the source know whether the string was successfully transferred and used
                event.setDropCompleted(success);

                event.consume();
            }
        });

        dSponge.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                // the drag-and-drop gesture ended
                System.out.println("onDragDone");
                // if the data was successfully moved, clear it
                if (event.getTransferMode() == TransferMode.MOVE) {
                    remove (dSponge);
                }

                event.consume();
            }
        });*/


        dSponge.setOnMousePressed(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                dSponge.setMouseTransparent(true);
                System.out.println ("Event on Source: mouse pressed");
                event.setDragDetect(true);
            }
        });

        dSponge.setOnMouseReleased(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                dSponge.setMouseTransparent(false);
                System.out.println("Event on Source: mouse released");
            }
        });

        dSponge.setOnMouseDragged(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                System.out.println("Event on Source: mouse dragged");
                event.setDragDetect(false);
            }
        });

        dSponge.setOnDragDetected(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                dSponge.startFullDrag();
                System.out.println("Event on Source: drag detected");
            }
        });



        eBox.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (dSponge);
                drawImage (dSponge, (int)event.getSceneX(), (int)event.getSceneY());
                System.out.println("Event on Target: mouse dragged");
            }
        });

        eBox.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (dSponge);
                drawImage (dSponge, (int)event.getSceneX(), (int)event.getSceneY());
               System.out.println("Event on Target: mouse drag over");
            }
        });

        eBox.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                remove (dSponge);
                drawImage (dSponge, -350, 200);
                remove (eBox);
                drawImage (eBox, -350, 200);
                System.out.println("Event on Target: mouse drag released");
            }
        });

        eBox.setOnMouseDragExited(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                System.out.println("Event on Target: mouse drag exited");
            }
        });




       /* final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

               if (device != null && isDragged)
                {
                    remove (device);
                    drawImage (device, x, y);
                }

                if (!reachedBox && device != null && !isDragged)
                {
                    if (device.equals(dSponge))
                        drawImage (dSponge, -110, -185 );
                    else if (device.equals(sink))
                        drawImage (sink,17, -179 );
                    else if (device.equals(shower))
                        drawImage (shower, 128, -188);
                    else if (device.equals(wCan))
                        drawImage (wCan, -90, -52);
                    else if (device.equals(barrel))
                        drawImage (barrel, 37, -52);
                    else if (device.equals(hose))
                        drawImage (hose, 138, -48);
                    else if (device.equals(cWasher))
                        drawImage (cWasher, -130, 75);
                    else if (device.equals(tub))
                        drawImage (tub, 5, 82);
                    else if (device.equals(dWasher))
                        drawImage (dWasher, 145, 76);
                }

            }
        }.start();*/
    }
}
