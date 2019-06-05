import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

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
    for (int i = startX + 90 + jump; i <= startX + 90 + jump + 60 && i < log.getLength(); i++)
    {
      if (log.isColoured(i))
        return true;
    }
    return false;
  }

  public boolean colIsColoured (Image img, int x, int y){
    PixelReader pixelReader = img.getPixelReader();
    if (!pixelReader.getColor(x, y).equals(Color.TRANSPARENT))
        return true;
    return false;
  }

  public int isTouchingDevice (DeviceLine dvcLine, int startX, int startY, int width, int height)
  {
    int type = 0;
      for (int x = startX; x <startX+width; x++) {
        try {
          type = dvcLine.deviceType(x, startY);
        } catch (ArrayIndexOutOfBoundsException e) {}
        if (type != 0)
          return type;
      }
      for (int x = startX; x <startX+width; x++) {
        try {
          type = dvcLine.deviceType(x, startY+height);
        } catch (ArrayIndexOutOfBoundsException e) {}
        if (type != 0)
          return type;
      }
      for (int y = startX; y <startY+width; y++) {
        try {
          type = dvcLine.deviceType(startX, y);
        } catch (ArrayIndexOutOfBoundsException e) {}
        if (type != 0)
          return type;
      }
      for (int y = startX; y <startY+width; y++) {
        try {
          type = dvcLine.deviceType(startX+width, y);
        } catch (ArrayIndexOutOfBoundsException e) {}
        if (type != 0)
          return type;
      }

    return type;
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
