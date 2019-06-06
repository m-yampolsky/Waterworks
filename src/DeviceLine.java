import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;
import java.util.ArrayList;

/**
 * The DeviceLine class
 * This class stores the image of the list of efficient and inefficient devices appearing on screen during the game, it stores an array representation of the pixels of the image, storing a 0 where there is no device, a 1 for an efficient device, and a -1 for an inefficient device..
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * June 1:
 * Maria wrote the DeviceLine class. She wrote the constructor, where she identified the colours present in efficient and inefficent objects (stored in effColours and ineffColours respectively). She also created a PixelReader object to read the pixels of the Image that every Deviceline object is associated with and to store whether no object, an efficeint object, or an inefficient object is found at that pixel location.
 * June 2:
 * Maria wrote the deviceType() method, which uses the colours 2-D array to return what kind of device is at a given pixel location.
 * June 3:
 * Maria corrected a bug in this class, by marking a pixel location in the colours 2D array with 0 if a pixel is White or Transparent. Originally, it only marked it with a 0 if it was transparent, but White also indicates the presence of no specific device type.
 * </pre>
 */
public class DeviceLine extends Image{

    /**
     * This 2 dimensional array stores a pixel by pixel representation of the DeviceLine image. At each pixel location, 0 represents there being no device, 1 is an efficient device, -1 is an inefficient device
     */
    private int[][] colours;

    /**
     * This stores a list of the colours present in all the efficient devices, used to identify their presence.
     */
    private ArrayList<Color> effColours;

    /**
     * This stores a list of the colours present in all the inefficient devices, used to identify their presence.
     */
    private ArrayList<Color> ineffColours;

    /**
     * This is the class constructor, it calls the Image class constructor to create an Image to associate with a DeviceLine object. It also determines whihc pixels of the DeviceLine image stores no device, an efficient device, and an inefficient device.
     * @param path This represents the path location of the file that stores the image for the DeviceLine object.
     */
    public DeviceLine (String path){
        super (path);
        PixelReader pixelReader = this.getPixelReader();
        colours = new int [(int)(getHeight())][(int)(getWidth())];

        ineffColours = new ArrayList<Color>();
        effColours = new ArrayList<Color>();

        ineffColours.add(Color.web("#fedd59"));
        ineffColours.add(Color.web("#c8e165"));
        ineffColours.add(Color.web("#a1b554"));
        ineffColours.add(Color.web("#eeeff1"));
        ineffColours.add(Color.web("#dad7d7"));
        ineffColours.add(Color.web("#9e9d9d"));
        ineffColours.add(Color.web("#fe5757"));
        ineffColours.add(Color.web("#202eac"));
        ineffColours.add(Color.web("#b9ffff"));
        ineffColours.add(Color.web("#249a00"));
        ineffColours.add(Color.web("#96deea"));
        ineffColours.add(Color.web("#737373"));
        ineffColours.add(Color.web("#e57e7e"));
        ineffColours.add(Color.web("#5eadeb"));
        ineffColours.add(Color.web("#d1dced"));
        ineffColours.add(Color.web("#ba650f"));

        effColours.add(Color.web("#a4aabc"));
        effColours.add(Color.web("#d8d8d8"));
        effColours.add(Color.web("#aaf9f9"));
        effColours.add(Color.web("#a5a5a5"));
        effColours.add(Color.web("#747474"));
        effColours.add(Color.web("#b14828"));
        effColours.add(Color.web("#8e3420"));
        effColours.add(Color.web("#cddee3"));
        effColours.add(Color.web("#b8cbd3"));
        effColours.add(Color.web("#588287"));
        effColours.add(Color.web("#769499"));
        effColours.add(Color.web("#92a6ad"));
        effColours.add(Color.web("#a9aa9a"));
        effColours.add(Color.web("#ededed"));
        effColours.add(Color.web("#72defc"));
        effColours.add(Color.web("#febc59"));
        effColours.add(Color.web("#fea928"));
        effColours.add(Color.web("#f95353"));
        effColours.add(Color.web("#e58383"));

        for (int y = 0; y < colours.length; y++)
        {
            for (int x = 0; x < colours[0].length; x++)
            {
                if (ineffColours.contains(pixelReader.getColor(x, y))){
                    colours[y][x] = -1;
                }
                else if (effColours.contains(pixelReader.getColor(x, y))){
                    colours[y][x] = 1;
                }
                else{
                    colours[y][x] = 0;
                }
            }
        }
    }

    /**
     * This returns an int representation of what kind of device is stored at a particular pixel location in the DeviceLine image, by referencing the colours 2D array.
     * @param x The x coordinate of the pixel location being searched for
     * @param y The y coordinate of the pixel location being searched for
     * @return 0 if no device is stored at the pixel, 1 if an efficient device is stored there, and -1 if an inefficient device is stored there.
     */
    public int deviceType (int x, int y) {
        return colours[y][x];
    }

}
