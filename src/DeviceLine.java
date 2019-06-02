import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;
import java.util.ArrayList;

/**
 * The DeviceLine class
 * This class stores the image of the list of efficient and inefficient devices appearing on screen during the game.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 1 05.27.2019
 */
public class DeviceLine extends Image{

    // 0 is no device, 1 is a good device, -1 is a bad device
    private int[][] colours;

    private ArrayList<Color> effColours;
    private ArrayList<Color> ineffColours;

    public DeviceLine (String path){
        super (path);
        PixelReader pixelReader = this.getPixelReader();
        colours = new int [(int)(getHeight())][(int)(getWidth())];

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
                if (pixelReader.getColor(x, y).equals(Color.TRANSPARENT)){
                    colours[y][x] = 0;
                }
                else if (effColours.contains(pixelReader.getColor(x, y))){
                    colours[y][x] = 1;
                }
                else{
                    colours[y][x] = -1;
                }
            }
        }
    }
    public int deviceType (int x, int y) {
        return colours[y][x];
    }

    public int getLength ()
    {
        return colours[0].length;
    }
}
