import javafx.scene.image.Image;

public class GameChar 
{
  int xCoord, yCoord; //coordinates of top left corner
  final int WIDTH;
  final int HEIGHT;
  
  public GameChar(String pic)
  {
    super(pic);
    WIDTH = (int)this.getWidth();
    HEIGHT = (int)this.getHeight();
  }
  
  public boolean isTouchingLog (LogLine log)
  {
    for (int i = xCoord; i < xCoord + WIDTH; i++)
    {
      if (log.isColoured[i][yCoord + HEIGHT])
        return true;
    }
    return false;
  }
  
  public void run ()
  {
  }
  
  public void jump ()
  {
  }
  
  public void fall ()
  {
  }
}
