import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;

import java.util.Arrays;

/**
 * The LogLine class
 * This class stores an object that represents the logs that the avatar will have to run across during the game
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
class LogLine extends Image
{
  /**
   * This array stores whether or not each pixel of the image has a colour at a spot or not
   */
  private boolean[][] isColoured;

  /**
   * The coordinates of the top left corner
   */
  private int xCoord, yCoord; 

  /**
   * @param pic
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
   * @param x
   * @return true if the pixel at that coordinate is coloured, or false if it is transparent
   */
  public boolean isColoured (int x) {
    for (int i = 0; i < isColoured.length; i++)
      if (isColoured[i][x]) {
        return true;
      }
    return false;
  }

  public int getLength ()
  {
    return isColoured[0].length;
  }
}
