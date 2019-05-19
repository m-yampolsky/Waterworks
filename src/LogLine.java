import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelReader;

public class LogLine extends Image
{
  boolean[][] isColoured;
  int xCoord, yCoord; //coordinates of the top left corner
  
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
}
