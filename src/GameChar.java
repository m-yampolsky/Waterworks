import javafx.scene.image.Image;

/**
 * The GameChar class
 * This class stores the object that represents the in game avatar that the user will be controlling to play. It extends the Image class to store an image representation of the avatar.
 * THis class will be used to detect if the avatar is touching devices and logs. It used in the third room only, and irs methods are used in calculating the user's score and for modifying the water
 * level of the lakes.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * May 18:
 * Maria created the class with xCoord, yCoord and WIDTH properties. She made it extend the Image class and added a class constructor.
 * May 31:
 * Maria wrote the isTouchingLog() method to detect the presence of the avatar on a log. The method returns true if the avatar is on a log, and it returns false if it is not on a log.
 * She used the isColoured() method from the LogLine class to properly detect whether the avatar is on the log or not.
 * June 1:
 * Maria modified the isTouchingLog() method. She changed the for loop to calculate slightly different values, since before, the log detection was some pixels off from where it should have been.
 * She also wrote an isTouchingDevice() method, based off of the isTouchingLog() method, however, it was not working properly yet.
 * June 2:
 * Vansh changed the isTouchingDevice() method to properly detect the presence of a device in the path of the avatar. He changed the method parameters and calculated slightly
 * different values to make it work properly.
 * June 4:
 * Once again, Vansh modified the values used in calculation for the isTouchingDevice() method, as the device detection still had room for improvement.
 * </pre>
 */
class GameChar extends Image
{

  /**
   * This is the class constructor, and it creates an Image object that represents the avatr, using the super constructor from the Image class.
   * @param pic The path to the image that the GameChar object will be associated with.
   */
  public GameChar(String pic)
  {
    super(pic);
  }

  /**
   * This method will detect if in the Play room, the avatar is in contact with one of the logs of the log line in the background. This method is used to determine if the character
   * has lost by missing a log and falling into a space between the logs.
   * @param log The LogLine object representing the logs in the background of the lake level.
   * @param startX The int value that marks from which point on the LogLine image are the logs being displayed on screen.
   * @param jump The int value that represents how far horizontally the avatar is from their initial placement on screen, depending on whether or not they have jumped and moved back yet.
   * @return true if the avatar is touching a log, and false if it is not.
   */
  public boolean isTouchingLog (LogLine log, int startX, int jump)
  {
    for (int i = startX + 90 + jump; i <= startX + 90 + jump + 60 && i < log.getLength(); i++) //for loop to check pixels at avatar's feet to see if those pixels of the LogLine image are coloured
    {
      if (log.isColoured(i))
        return true;
    }
    return false;
  }

  /**
   * This method will detect if in the Play room, the avatar is in contact with one of the devices of the device line in the background. This method is used to determine if the water level
   * has dropped or increased, and if the score has dropped or increase.
   * @param dvcLine The DeviceLine object representing the devices in the background of the lake level.
   * @param startX The int value that marks from which point on the DeviceLine image are the logs being displayed on screen.
   * @param startY The int value that marks from which point the DeviceLine image should be tested for the presence of a device vertically.
   * @param width The width of the section being searched for devices.
   * @param height The height of the section being searched for devices.
   * @return true if the avatar is touching a log, and false if it is not.
   */
  public int isTouchingDevice (DeviceLine dvcLine, int startX, int startY, int width, int height)
  {
    int type = 0;
      for (int x = startX; x <startX+width; x++) {
        try {
          type = dvcLine.deviceType(x, startY); //tests the base of the avatar
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        if (type != 0) //if type is either an efficient or inefficient device
          return type;
      }
      for (int x = startX; x <startX+width; x++) {
        try {
          type = dvcLine.deviceType(x, startY+height); //tests the top of the avatar
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        if (type != 0) //if type is either an efficient or inefficient device
          return type;
      }
      for (int y = startY; y <startY+width; y++) {
        try {
          type = dvcLine.deviceType(startX, y); //tests the side of the avatar
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        if (type != 0) //if type is either an efficient or inefficient device
          return type;
      }
      for (int y = startY; y <startY+width; y++) {
        try {
          type = dvcLine.deviceType(startX+width, y); //tests the side of the avatar
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        if (type != 0) //if type is either an efficient or inefficient device
          return type;
      }

    return type;
  }

}
