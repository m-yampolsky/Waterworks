public class LogLine 
{
  boolean[][] isColoured;
  int xCoord, yCoord; //coordinates of the top left corner
  
  public LogLine (String pic, int height, int width)
  {
    super (pic);
    PixelReader pixelReader = this.getPixelReader(); 
    isColoured = new boolean [height][width];
    for (int y = 0; y < height; y++)
    {
      for (int x = 0; x < width; x++)
      {
        if (!pixelReader.getColor(x, y).equals(Color.TRANSPARENT))
          isColoured[y][x] = true;
      }
    }
  }
}
