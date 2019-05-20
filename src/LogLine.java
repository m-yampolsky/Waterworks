import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;

/**
 *
 */
public class LogLine extends Image
{
  /**
   *
   */
  private boolean[][] isColoured;

  /**
   *
   */
  private int xCoord, yCoord; //coordinates of the top left corner

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
   * @param y
   * @return
   */
  public boolean isColoured (int x, int y) {
    return isColoured[x][y];
  }
}
