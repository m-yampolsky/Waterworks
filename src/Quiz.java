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
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * May 16:
 * Maria wrote the general frame of the class. She added a display() method that just left the screen, to properly extend the Window class, but without the graphics having been set up yet.
 * May 19:
 * Maria added the Menu button to allow the user to return to the Main Menu from this screen.
 * May 22:
 * Maria added the general graphics for the screen. She added the background and the boxes. She also added all nine devices used in the class. She spaced out the devices on the shelves
 * to ensure that they all fit on the shelf. All these additions were made in the display() method.
 * May 23:
 * Maria added the ability of all the devices in the class to be dragged. She added an array of devices, and in a for loop, she used the .setOnMousePressed() and .setOnMouseReleased() methods to them
 * to allow them to be dragged. She also used the same .setOnMousePressed() and .setOnMouseReleased() methods to set a reaction for the background and teh boxes. She also added an AnimationTimer object
 * to redraw the objects as they are being dragged. She used the .setOnMouseDragOver() and .setOnMouseDragEntered() methods to export the x and y coordinates of the cursor and redraw the device at that location.
 * She also changed the .setOnMouseReleased() method call to send the devices back to the shelf if they are released in the wrong place.
 * May 24:
 * Maria added the "dropping" element of the drag and drop game, by using .setOnDragReleased() to check whether the coordinates were located above the box, and then redrawing the device in the box.
 * May 25:
 * Maria fixed two bugs in the class by adding the removeMouse() and resetMouse() buttons. She used the resetMouse() button to reset the ability to be clicked of devices stuck on the border when the
 * cursor left the screen very rapidly. She used the removeMouse() method to prevent objects redrawn inside of the boxes from being removed and moved.
 * May 29:
 * Maria added the Check button, which appears after the user has placed all the objects in the boxes. She also added the check() method, which would display the contents of the screen where the results
 * from this room are located. She added the background of the screen with the results, as well as for loops that display the contents of both boxes.
 * May 30:
 * Maria completed the results screen by using if statements in the check() method to determine where to place the devices, which devices the user placed correctly and which devices they placed incorrectly.
 * She also added a Play button to appear on that screen, allowing the user to move on to the third room. Additionally, she added a mark out of 9, displayed on the screen to show the user
 * how many objects they placed correctly.
 * </pre>
 */
public class Quiz extends Window {

    /**
     * This stores whether there is a device being dragged at that moment.
     */
    private boolean isDragged;

    /**
     * This stores whether a device has been released above a spot that is not a box.
     */
    private boolean lost;

    /**
     * This stores the coordinates of the cursor.
     */
    private int x, y;

    /**
     * This stores the device the user has selected at that moment.
     */
    private ImageView device;

    /**
     * This stores the list of devices the user places in the efficient box.
     */
    private ArrayList<ImageView> effDevices;

    /**
     * This stores the list of devices the user places in the inefficient box.
     */
    private ArrayList<ImageView> ineffDevices;

    /**
     * This stores the scene's background.
     */
    private ImageView background;

    /**
     * This stores the bathtub device.
     */
    private ImageView tub;

    /**
     * This stores the sink device.
     */
    private ImageView sink;

    /**
     * This stores the washing machine device.
     */
    private ImageView cWasher;

    /**
     * This stores the dish washer device.
     */
    private ImageView dWasher;

    /**
     * This stores the watering can device.
     */
    private ImageView wCan;

    /**
     * This stores the rain barrel device.
     */
    private ImageView barrel;

    /**
     * This stores the handwashing dishes image.
     */
    private ImageView dSponge;

    /**
     * This stores the hose device.
     */
    private ImageView hose;

    /**
     * This stores the shower device.
     */
    private ImageView shower;

    /**
     * This stores the scene's border.
     */
    private ImageView border;

    /**
     * This stores the efficient box.
     */
    private ImageView eBox;

    /**
     * This stores the inefficient box.
     */
    private ImageView iBox;

    /**
     * This stores the menu button.
     */
    private ImageView menuBtn;

    /**
     * This stores the play button.
     */
    private ImageView play;

    /**
     * This stores the click sound.
     */
    private Sound box;

    /**
     * This stores whether or not the play button has been clicked.
     */
    private boolean playButtonClicked;

    /**
     * This is the class constructor. It calls the super constructor of the Window class and sets initial values.
     * @param stg The JavaFX stage that all the graphics will be displayed on.
     */
    public Quiz(Stage stg) {
        super(stg, "Quiz");
        playButtonClicked = false;
        isDragged = false;
        lost = false;
        device = null;
        effDevices = new ArrayList<ImageView>();
        ineffDevices = new ArrayList<ImageView>();
        background = (ImageView) (Resources.get("quizBack"));
        tub = new ImageView ("elements/game/tub.png");
        sink = new ImageView ("elements/game/sink.png");
        cWasher = new ImageView ("elements/game/washer.png");
        dWasher = new ImageView ("elements/game/dishwasher.png");
        wCan = new ImageView ("elements/game/watercan.png");
        barrel = new ImageView ("elements/game/barrel.png");
        dSponge = new ImageView ("elements/game/dish.png");
        hose = new ImageView ("elements/game/hose.png");
        shower = new ImageView ("elements/game/shower.png");
        border = new ImageView ("elements/game/border.png");
        eBox = (ImageView)(Resources.get("effBox"));
        iBox = (ImageView)(Resources.get("ineffBox"));
        menuBtn = (ImageView)(Resources.get("menuBtn"));
        play = (ImageView)(Resources.get("checkPlay"));
        box = (Sound)(Resources.get("box"));
    }

    /**
     * This method displays the dragging and dropping game, and it runs the animation of items being redrawn as they are dragged. It also has a Menu button that allows the user to return
     * to the Main Menu. It stores which objects are placed into the two different boxes, and it shows the Check button when all of them have been placed in the boxes. If the button is pressed
     * the check() method is called to then display the results screen.
     */
    public void display() {
        Canvas thisCan = getCanvas();
        ImageView boxBack1 = (ImageView)(Resources.get("boxBack"));
        ImageView boxBack2 = (ImageView)(Resources.get("boxBack2"));

        menuBtn.setOnMouseClicked(e -> { //the menu button allowing you to return to MAin Menu
            hideStage();
        });

        //draw all the elements
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

        //reset the mouse detection for all the images
        for (ImageView img : devices)
        {
            resetMouse (img);
        }

        //border reaction
        border.setOnMouseDragEntered(event -> lost = true);
        border.setOnMouseDragOver((EventHandler<MouseEvent>) event -> lost = true);
        border.setOnMouseDragExited(event -> lost = true);
        border.setOnMouseEntered(event -> lost = true);
        border.setOnMouseDragReleased(event -> {
            lost = true;
            event.setDragDetect(false);
        });


        //detect if a Mouse Drag has been entered
        eBox.setOnMouseDragEntered(event -> {
            isDragged = true;
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            lost = false;
        });
        iBox.setOnMouseDragEntered(event -> {
            isDragged = true;
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            lost = false;
        });
        boxBack1.setOnMouseDragEntered(event -> {
            isDragged = true;
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            lost = false;

        });
        boxBack2.setOnMouseDragEntered(event -> {
            isDragged = true;
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            lost = false;

        });
        background.setOnMouseDragEntered(event -> {
            isDragged = true;
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            lost = false;

        });
        menuBtn.setOnMouseDragEntered(event -> {
            isDragged = true;
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            lost = false;

        });

        //detect if a Mouse Drag passes over the object
        background.setOnMouseDragOver(event -> {
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            isDragged = true;
            lost = false;

        });

        eBox.setOnMouseDragOver(event -> {
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            isDragged = true;
            lost = false;

        });
        iBox.setOnMouseDragOver(event -> {
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            isDragged = true;
            lost = false;

        });
        boxBack1.setOnMouseDragOver(event -> {
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            isDragged = true;
            lost = false;

        });
        boxBack2.setOnMouseDragOver(event -> {
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            isDragged = true;
            lost = false;

        });
        menuBtn.setOnMouseDragOver(event -> {
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            isDragged = true;
            lost = false;

        });

        //detect if a Mouse Drag has been released
        eBox.setOnMouseDragReleased(event -> {
            if (device != null){
                box.play();
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

        });
        iBox.setOnMouseDragReleased(event -> {
            setCursor(0);
            if (device != null){
                box.play();
                remove (device);
                drawImage (device, 360, 150);
                removeMouse (device);
                remove (iBox);
                drawImage (iBox, 360, 200);
                ineffDevices.add(device);
                device = null;
                event.setDragDetect(false);
                lost = false;}

        });
        boxBack1.setOnMouseDragReleased(event -> {
            setCursor(0);
            if (device != null){
                box.play();
                remove (device);
                drawImage (device, -350, 150);
                removeMouse (device);
                remove (eBox);
                drawImage (eBox, -350, 200);
                effDevices.add(device);
                device = null;
                event.setDragDetect(false);
                lost = false;}

        });
        boxBack2.setOnMouseDragReleased(event -> {
            setCursor(0);
            if (device != null){
                box.play();
                remove (device);
                drawImage (device, 360, 150);
                removeMouse (device);
                remove (iBox);
                drawImage (iBox, 360, 200);
                ineffDevices.add(device);
                device = null;
                event.setDragDetect(false);
                lost = false;}

        });

        background.setOnMouseDragReleased(event -> {
            setCursor(0);
            lost = true;
            event.setDragDetect(false);
        });
        menuBtn.setOnMouseDragReleased(event -> {
            setCursor(0);
            lost = true;
            event.setDragDetect(false);
        });

        //Detect if the Mouse has entered the Menu button
        menuBtn.setOnMouseEntered(event -> setCursor(1));

        //Detect if the Mouse has exited the Menu button
        menuBtn.setOnMouseExited(event -> setCursor(0));

        //animation to redraw the devices as they move across screen
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                if (isDragged && device != null && !lost) { //redraw devices as it is dragged
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
                            drawImage (dSponge, -110, -185 );//redraws devices in the correct location on the shelf
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

                if (device != null && lost) //returns the device to the shelf since it has been lost
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

                if (ineffDevices.size() + effDevices.size() >= 9) { //all devices have been placed, so animation stops and the Check button is displayed
                    stop();
                    ImageView check = (ImageView)(Resources.get("check"));
                    drawImage(check, 20, -60);
                    check.setOnMouseClicked(e -> {
                        refresh();
                        check();
                    });
                    check.setOnMouseEntered(e -> setCursor(1));
                    check.setOnMouseExited(e -> setCursor(0));
                }
            }
        }.start();
    }

    /**
     * This method resets the Mouse detection of the ImageView object passed.
     * @param img The ImageView object whose mouse detection is being reset.
     */
    public void removeMouse (ImageView img)
    {
        img.setOnMouseEntered(event -> setCursor(0));
        img.setOnMouseExited(event -> setCursor(0));
        img.setOnMousePressed(event -> device = null);

        img.setOnMouseReleased(event -> {

        });

        img.setOnMouseDragged(event -> {
            event.setDragDetect(false);
            x = (int) event.getSceneX();
            y = (int) event.getSceneY();
            isDragged = true;
            lost = false;

        });
        img.setOnMouseDragOver(event -> {
            x = (int)event.getSceneX();
            y = (int)event.getSceneY();
            isDragged = true;
            lost = false;

        });
        img.setOnDragDetected(event -> {

        });
        img.setOnMouseDragReleased(event -> {


        });
    }

    /**
     * This method removes the Mouse detection of the ImageView object passed.
     * @param img The ImageView object whose mouse detection is being removed.
     */
    public void resetMouse (ImageView img)
    {
        img.setOnMouseEntered(event -> setCursor(1));
        img.setOnMouseExited(event -> setCursor(0));
        img.setOnMousePressed(event -> {
            img.setMouseTransparent(true);
            isDragged = false;
            device = img;
            event.setDragDetect(true);
            lost = false;
        });

        img.setOnMouseReleased(event -> {
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

        });

        img.setOnMouseDragged(event -> {
            if (device != null && !device.equals(img))
            {
                event.setDragDetect(false);
                x = (int) event.getSceneX();
                y = (int) event.getSceneY();
                isDragged = true;
                lost = false;
            }
        });
        img.setOnMouseDragOver(event -> {
            setCursor(2);
            if (device != null && !device.equals(img))
            {
                x = (int)event.getSceneX();
                y = (int)event.getSceneY();
                isDragged = true;
                lost = false;
            }

        });
        img.setOnDragDetected(event -> {
            setCursor(2);
            img.startFullDrag();
            lost = false;
        });
        img.setOnMouseDragReleased(event -> {
            setCursor(0);
            if (device.equals(img)) {
                if (x >= 48 && x <= 252 && y >= 465 && y <= 670) //efficient box
                {
                    box.play();
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
                    box.play();
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
        });
    }

    /**
     * This method displays the screen with the results from the drag and drop game. It shows the items that the user placed in the boxes, along with check marks and crosses to
     * indicate whether the devices were placed correctly or incorrectly. From this screen, the user can continue to the third room, by pressing the Play button, or they can return
     * to the Main Menu by clicking the Menu button. This method compares the lists that stores the answers of the user to the lists that store the correct answers to determine whether
     * a device is placed correctly or incorrectly.
     */
    public void check ()
    {
        refresh();
        int quizScore = 0;
        Image checkBack = (Image)(Resources.get("checkBack"));
        Image checkMark = (Image)(Resources.get("checkMark"));
        Image wrong = (Image)(Resources.get("checkWrong"));
        ArrayList<ImageView> correctEff = new ArrayList<ImageView>(); //creates a list of efficient devices
        ArrayList<ImageView> correctIneff = new ArrayList<ImageView>(); //creates a list of inefficient devices

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
        menuBtn.setOnMouseClicked(e -> { //return to the Main Menu
            hideStage();
        });
        int coord = 0;
        for (int i = 0; i< effDevices.size(); i++) //draw the devices in their efficient box
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
                drawImage (checkMark, coord + 489, 180); //draw the check marks
            }
            else {
                drawImage (wrong, coord + 490, 180); //draw the x's
            }
        }
        coord = 0;
        for (int i = 0; i< ineffDevices.size(); i++) //draw the devices in their inefficient box
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
            if (correctIneff.contains(ineffDevices.get(i))) //draw the check marks
            {
                quizScore ++;
                drawImage (checkMark, coord + 489, 320);
            }
            else {
                drawImage (wrong, coord + 490, 320); //draw the x's
            }
        }
        Image number = new Image ("elements/game/digits/" + quizScore + ".png"); //display how many devices the user place correctly
        drawImage (number, 300, 650);

        play.setOnMouseClicked(e -> { //take the user to the play level
            playButtonClicked = true;
            hideStage();
        });
        play.setOnMouseEntered(e -> setCursor(1));
        play.setOnMouseExited(e -> setCursor(0));
        menuBtn.setOnMouseClicked(event -> {
            hideStage();
        });
    }

    /**
     * This method returns the value of the playButtonClicked variable, which stores whether or not the Play button to move on to the next room has been clicked.
     * @return true if the play Button has been clicked, and false if it has not been
     */
    public boolean getPlayButtonClicked()
    {
        return playButtonClicked;
    }

}
