import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;

/**
 * The LogLine class
 * This class stores an object that represents the logs that the avatar will have to run across during the game. It stores a 2 dimensional array representation of the pixels of the Log image.
 * At each pixel, a boolean value is stored, indicating whether that pixel is transparent or coloured. The coloured pixels are located where the logs are in the image. The transparent pixels
 * identify where there are no logs. This class is used in the third room to help the avatar detect whether it is on a log or not.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * May 18:
 * Maria wrote the class and created the isColoured variable, as well as the class constructor. She used PixelReader to loop through the pixels of the image and to create the
 * isColoured array with the correct values.
 * May 25:
 * Maria wrote the isColoured() and length() methods to use them in the methods of the GameChar class to allow the avatar to detect whether it is touching a log.
 * </pre>
 */
class LogLine extends Image
{
  /**
   * This array stores whether or not each pixel of the image has a colour at a pixel or not.
   */
  private final boolean[][] isColoured;

  /**
   * This is the constructor for this class. It calls the super() constructor from the Image class to create a new image to display. It also initializes the 2 dimensional array that
   * stores its pixels. The constructor uses a PixelReader to find the pixels of the image. It then loops through the PixelReader to check which pixels are coloured and which are transparent.
   * The transparent pixels are then assigned false in the isColoured array, and the coloured pixels are asssigned true.
   * @param pic The path to the image file from which the LogLine will be created.
   */
  public LogLine (String pic)
  {
    super (pic);
    PixelReader pixelReader = this.getPixelReader(); 
    isColoured = new boolean [(int)(getHeight())][(int)(getWidth())];
    for (int y = 0; y < isColoured.length; y++)
    {
      for (int x = 0; x < isColoured[0].length; x++)
      {
        if (!pixelReader.getColor(x, y).equals(Color.TRANSPARENT))
          isColoured[y][x] = true;
      }
    }
  }

  /**
   * This method returns whether or not one of the pixels in the LogLine with the x coordinate passed by the user is coloured. The method searches a column of the isColoured array,
   * and if it finds at least one pixel which is coloured, it returns true.
   * @param x
   * @return true if the pixel at that coordinate is coloured, or false if it is transparent
   */
  public boolean isColoured (int x) {
    for (boolean[] booleans : isColoured)
      if (booleans[x]) {
        return true;
      }
    return false;
  }

  /**
   * This method returns the number of columns in the array representing the pixels of the image(the width of the image).
   * @return the length of a row in the isColoured 2 dimensional array.
   */
  public int getLength ()
  {
    return isColoured[0].length;
  }
}
