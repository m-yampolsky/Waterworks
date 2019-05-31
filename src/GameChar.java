import javafx.scene.image.Image;

/**
 * The GameChar class
 * This class stores the object that represents the in game avatar that the user will be controlling to play.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class GameChar extends Image
{
  /**
   * The coordinates of the avatars location.
   */
  private int xCoord, yCoord;

  /**
   * The width of the avatar's image.
   */
  private final int WIDTH;

  /**
   * The height of the avatar's image.
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
   * @return true if the avatar is touching a log, and false if it is not
   */
  public boolean isTouchingLog (LogLine log, int startX, int jump)
  {
    for (int i = startX + 125 + jump; i <= startX + 125 + jump + 75 && i < log.getLength(); i++)
    {
      if (log.isColoured(i))
        return true;
    }
    return false;
  }

  /**
   * this method will show the avatar running
   */
  public void run ()
  {
  }

  /**
   * this method will show the avatar jumping
   */
  public void jump ()
  {
  }

  /**
   * this method will show the avatar falling
   */
  public void fall ()
  {
  }

  public void setX(int x)
  {
    xCoord = x;
  }
}
