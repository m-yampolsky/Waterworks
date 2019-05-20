import javafx.scene.image.Image;

/**
 *
 */
public class GameChar extends Image
{
  /**
   *
   */
  private int xCoord, yCoord;

  /**
   *
   */
  private final int WIDTH;

  /**
   *
   */
  private final int HEIGHT;

  /**
   * @param pic
   */
  public GameChar(String pic)
  {
    super(pic);
    WIDTH = (int)this.getWidth();
    HEIGHT = (int)this.getHeight();
  }

  /**
   * @param log
   * @return
   */
  public boolean isTouchingLog (LogLine log)
  {
    for (int i = xCoord; i < xCoord + WIDTH; i++)
    {
      if (log.isColoured(i, yCoord + HEIGHT))
        return true;
    }
    return false;
  }

  /**
   *
   */
  public void run ()
  {
  }

  /**
   *
   */
  public void jump ()
  {
  }

  /**
   *
   */
  public void fall ()
  {
  }
}
