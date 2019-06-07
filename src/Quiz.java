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
 * This class represents the Quiz window of the game where the user is tested on what they learned about water conservation. In the Quiz room, the user will find a shelf
 * with some of the objects from the Learn room. There will be two boxes: a box for efficient water devices, and a box for inefficient water devices. The user will have to
 * drag and drop the objects from the shelf to the box, by pressing the mouse on the objects, dragging the object to the box, and releasing the mouse above the correct box.
 * Once an object is placed in a box, it can’t be removed. Once all the objects are placed in boxes, a button to Check the user's answers will appear. At all times there will be
 * a Menu button which they can press to return to the Main Menu. If they press the Check button, they will be taken to a screen that shows the objects in their boxes, and the
 * correct object placements in the boxes. They will also see a count for how many of the objects out of nine they placed correctly. And, where it display which objects they
 * placed in which boxes, check marks and x's will appear underneath the correctly placed objects and incorrectly place objects, respectively. From this screen, the user can return
 * to the Main Menu or continue on to the third room To go back to the Menu, the user must click on the Menu button. To go on to the third room, they must click on the Play button.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 *
 * </pre>
 */
public class Quiz extends Window {

    private int quizScore = 0;
    private boolean isDragged = false;
    private boolean lost = false;
    private int x, y;
    private ImageView device = null;
    private ArrayList<ImageView> effDevices;
    private ArrayList<ImageView> ineffDevices;
    private ImageView background = (ImageView) (Resources.get("quizBack"));
    private ImageView tub = new ImageView ("elements/game/tub.png");
    private ImageView sink = new ImageView ("elements/game/sink.png");
    private ImageView cWasher = new ImageView ("elements/game/washer.png");
    private ImageView dWasher = new ImageView ("elements/game/dishwasher.png");
    private ImageView wCan = new ImageView ("elements/game/watercan.png");
    private ImageView barrel = new ImageView ("elements/game/barrel.png");
    private ImageView dSponge = new ImageView ("elements/game/dish.png");
    private ImageView hose = new ImageView ("elements/game/hose.png");
    private ImageView shower = new ImageView ("elements/game/shower.png");
    private ImageView border = new ImageView ("elements/game/border.png");
    private ImageView eBox = (ImageView)(Resources.get("effBox"));
    private ImageView iBox = (ImageView)(Resources.get("ineffBox"));
    private ImageView menuBtn = (ImageView)(Resources.get("menuBtn"));
    private ImageView play = (ImageView)(Resources.get("checkPlay"));
    private Sound click = (Sound)(Resources.get("click"));

    public boolean playButtonClicked = false;

    public Quiz(Stage stg) {
        super(stg, "Quiz");
        effDevices = new ArrayList<ImageView>();
        ineffDevices = new ArrayList<ImageView>();
        playButtonClicked = false;
    }

    public void display() {
        Canvas thisCan = getCanvas();

        ImageView boxBack1 = (ImageView)(Resources.get("boxBack"));
        ImageView boxBack2 = (ImageView)(Resources.get("boxBack2"));



        menuBtn.setOnMouseClicked(e -> {
            click.play();
            hideStage();
        });

        drawImage(background, 0, 0);
        drawImage(menuBtn, 400, -330);

        drawImage (border, 0,0);

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
                if (device != null){
                    setCursor(0);
                    remove (device);
                    drawImage (device, -350, 150);
                    removeMouse (device);
                    remove (eBox);
                    drawImage (eBox, -350, 200);
                    effDevices.add(device);
                    device = null;
                    event.setDragDetect(false);
                    lost = false;
                }

            }
        });
        iBox.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                setCursor(0);
                if (device != null){
                    remove (device);
                    drawImage (device, 360, 150);
                    removeMouse (device);
                    remove (iBox);
                    drawImage (iBox, 360, 200);
                    ineffDevices.add(device);
                    device = null;
                    event.setDragDetect(false);
                    lost = false;}

            }
        });
        boxBack1.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                setCursor(0);
                if (device != null){
                    remove (device);
                    drawImage (device, -350, 150);
                    removeMouse (device);
                    remove (eBox);
                    drawImage (eBox, -350, 200);
                    effDevices.add(device);
                    device = null;
                    event.setDragDetect(false);
                    lost = false;}

            }
        });
        boxBack2.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                setCursor(0);
                if (device != null)
                {remove (device);
                    drawImage (device, 360, 150);
                    removeMouse (device);
                    remove (iBox);
                    drawImage (iBox, 360, 200);
                    ineffDevices.add(device);
                    device = null;
                    event.setDragDetect(false);
                    lost = false;}

            }
        });

        background.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                setCursor(0);
                lost = true;
                event.setDragDetect(false);
            }
        });
        menuBtn.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                setCursor(0);
                lost = true;
                event.setDragDetect(false);
            }
        });

        menuBtn.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(1);
            }
        });
        menuBtn.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(0);
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
                        wCan = new ImageView ("elements/game/watercan.png");
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

                    if (x <= 1000 && x >= 0 && y >= 0 && y <= 750) {
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
                        wCan = new ImageView ("elements/game/watercan.png");
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

                if (ineffDevices.size() + effDevices.size() >= 9) {
                    stop();
                    ImageView check = (ImageView)(Resources.get("check"));
                    drawImage(check, 20, -60);
                    check.setOnMouseClicked(e -> {
                        refresh();
                        click.play();
                        check();
                    });
                    check.setOnMouseEntered(e -> {
                        setCursor(1);
                    });
                    check.setOnMouseExited(e -> {
                        setCursor(0);
                    });
                }
            }
        }.start();
    }

    private void removeMouse(ImageView img)
    {
        img.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(0);
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(0);

            }
        });
        img.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                device = null;

            }
        });

        img.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

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

            }
        });
        img.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {


            }
        });
    }

    private void resetMouse(ImageView img)
    {
        img.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(1);

            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                setCursor(0);
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
                if (device != null && !device.equals(img))
                {
                    event.setDragDetect(false);
                    x = (int) event.getSceneX();
                    y = (int) event.getSceneY();
                    isDragged = true;
                    lost = false;
                }
            }
        });
        img.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                setCursor(2);
                if (device != null && !device.equals(img))
                {
                    x = (int)event.getSceneX();
                    y = (int)event.getSceneY();
                    isDragged = true;
                    lost = false;
                }

            }
        });
        img.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setCursor(2);
                img.startFullDrag();
                lost = false;
            }
        });
        img.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event) {
                setCursor(0);
                if (device.equals(img)) {
                    if (x >= 48 && x <= 252 && y >= 465 && y <= 670) //efficient box
                    {
                        remove(device);
                        drawImage(device, -350, 150);
                        removeMouse (device);
                        remove(eBox);
                        drawImage(eBox, -350, 200);
                        effDevices.add(device);
                        device = null;
                        event.setDragDetect(false);
                        lost = false;
                    } else if (x >= 748 && x <= 962 && y >= 465 && y <= 670) //inefficient box
                    {
                        remove(device);
                        drawImage(device, 360, 150);
                        removeMouse (device);
                        remove(iBox);
                        drawImage(iBox, 360, 200);
                        ineffDevices.add(device);
                        device = null;
                        event.setDragDetect(false);
                        lost = false;
                    }
                    else {
                        lost = true;
                    }
                }
                else {
                    lost = true;
                }
            }

        });
    }

    private void check()
    {
        refresh();
        Image checkBack = (Image)(Resources.get("checkBack"));
        Image checkMark = (Image)(Resources.get("checkMark"));
        Image wrong = (Image)(Resources.get("wrong"));
        ArrayList<ImageView> correctEff = new ArrayList<ImageView>();
        ArrayList<ImageView> correctIneff = new ArrayList<ImageView>();

        correctEff.add (wCan);
        correctEff.add (shower);
        correctEff.add (barrel);
        correctEff.add (dWasher);
        correctIneff.add (hose);
        correctIneff.add (tub);
        correctIneff.add (sink);
        correctIneff.add (cWasher);
        correctIneff.add (dSponge);

        drawImage(play, 400, 325);
        drawImage (checkBack, 0, 0);
        drawImage (menuBtn, 400, -330);
        menuBtn.setOnMouseClicked(e -> {
            click.play();
            hideStage();
        });
        int coord = 0;
        for (int i = 0; i< effDevices.size(); i++)
        {
            if (i == 0){
                coord = -335;
            }
            if (effDevices.get(i).equals(barrel))
                coord += 35;
            if (effDevices.get(i).equals(dSponge))
                coord += 44;
            if (effDevices.get(i).equals(dWasher))
                coord += 34;
            if (effDevices.get(i).equals(hose))
                coord += 36;
            if (effDevices.get(i).equals(shower))
                coord += 39;
            if (effDevices.get(i).equals(sink))
                coord += 51;
            if (effDevices.get(i).equals(cWasher))
                coord += 33;
            if (effDevices.get(i).equals(tub))
                coord += 77;
            if (effDevices.get(i).equals(wCan))
                coord += 55;

            if (i != 0 && effDevices.get(i - 1).equals(barrel))
                coord += 35;
            if (i != 0 && effDevices.get(i - 1).equals(dSponge))
                coord += 44;
            if (i != 0 && effDevices.get(i - 1).equals(dWasher))
                coord += 34;
            if (i != 0 && effDevices.get(i - 1).equals(hose))
                coord += 36;
            if (i != 0 && effDevices.get(i - 1).equals(shower))
                coord += 39;
            if (i != 0 && effDevices.get(i - 1).equals(sink))
                coord += 51;
            if (i != 0 && effDevices.get(i - 1).equals(cWasher))
                coord += 33;
            if (i != 0 && effDevices.get(i - 1).equals(tub))
                coord += 77;
            if (i != 0 && effDevices.get(i - 1).equals(wCan))
                coord += 55;
            effDevices.get(i).setPreserveRatio(true);
            effDevices.get(i).setFitHeight(75);
            drawImage (effDevices.get(i), coord,-240);
            if (correctEff.contains(effDevices.get(i)))
            {
                quizScore ++;
                drawImage (checkMark, coord + 489, 180);
            }
            else {
                drawImage (wrong, coord + 490, 180);
            }
        }
        coord = 0;
        for (int i = 0; i< ineffDevices.size(); i++)
        {
            if (i == 0){
                coord = -335;
            }
            if (ineffDevices.get(i).equals(barrel))
                coord += 35;
            if (ineffDevices.get(i).equals(dSponge))
                coord += 46;
            if (ineffDevices.get(i).equals(dWasher))
                coord += 34;
            if (ineffDevices.get(i).equals(hose))
                coord += 36;
            if (ineffDevices.get(i).equals(shower))
                coord += 39;
            if (ineffDevices.get(i).equals(sink))
                coord += 51;
            if (ineffDevices.get(i).equals(cWasher))
                coord += 33;
            if (ineffDevices.get(i).equals(tub))
                coord += 77;
            if (ineffDevices.get(i).equals(wCan))
                coord += 55;

            if (i != 0 && ineffDevices.get(i - 1).equals(barrel))
                coord += 35;
            if (i != 0 && ineffDevices.get(i - 1).equals(dSponge))
                coord += 44;
            if (i != 0 && ineffDevices.get(i - 1).equals(dWasher))
                coord += 34;
            if (i != 0 && ineffDevices.get(i - 1).equals(hose))
                coord += 36;
            if (i != 0 && ineffDevices.get(i - 1).equals(shower))
                coord += 39;
            if (i != 0 && ineffDevices.get(i - 1).equals(sink))
                coord += 51;
            if (i != 0 && ineffDevices.get(i - 1).equals(cWasher))
                coord += 33;
            if (i != 0 && ineffDevices.get(i - 1).equals(tub))
                coord += 77;
            if (i != 0 && ineffDevices.get(i - 1).equals(wCan))
                coord += 55;
            ineffDevices.get(i).setPreserveRatio(true);
            ineffDevices.get(i).setFitHeight(75);
            drawImage (ineffDevices.get(i), coord,-100);
            if (correctIneff.contains(ineffDevices.get(i)))
            {
                quizScore ++;
                drawImage (checkMark, coord + 489, 320);
            }
            else {
                drawImage (wrong, coord + 490, 320);
            }
        }
        Image number = new Image ("elements/game/digits/" + quizScore + ".png");
        drawImage (number, 300, 650);

        play.setOnMouseClicked(e -> {
            playButtonClicked = true;
            click.play();
            hideStage();
        });
        play.setOnMouseEntered(e -> {
            setCursor(1);
        });
        play.setOnMouseExited(e -> {
            setCursor(0);
        });
        menuBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                click.play();
                hideStage();
            }
        });
    }

}
