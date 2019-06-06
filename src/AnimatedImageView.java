import javafx.scene.image.ImageView;

/**
 * The AnimatedImageView Class
 * This class stores an animation, by storing many frames of a picture. This is used in the WaterWorks program to show animations of the character moving on the spot.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * May 26:
 * This class was written by copying the AnimatedImage class but returning ImageView objects from the getFrame() and lastFrame() objects.
 * May 29:
 * Vansh redid the class to no longer store an array of ImageView objects, but to store a path location and a frame number. The instance methods and constructor were rewritten to accomodate for this change, and now do not return items using indexes of an array, but return items using the path location.
 *
 * </pre>
 */
public class AnimatedImageView
{

    /**
     * The path location of the files in which the frames of the animation are stored.
     */
    private String path;

    /**
     * The number of frames in the animation.
     */
    private int numFrames;

    /**
     * The number used in the url of the file storing the frame of the animation.
     */
    private int pathNum;

    /**
     * The duration of the animation.
     */
    private double duration;

    /**
     * The constructor of the AnimatedImageView class, which creates an object of the class.
     * @param path The path where the images used for the animation are located
     * @param nframes The number of frames in the animation
     * @param duratn The duration of the animation
     */
    public AnimatedImageView (String path, int nframes, double duratn) {
        this.path = path;
        duration = duratn;
        numFrames = nframes;
    }

    /**
     * The getFrame() method, which returns the frame of an animation based off of the time entered.
     * @param time The time into the animation, determines which frame the animation is on
     * @return An ImageView object that represents the frame that the animation is currently on
     */
    public ImageView getFrame(double time) {
        int index = Math.max((int)((time % (numFrames * duration)) / duration), 1);
        pathNum = index;
        return new ImageView(path + " (" + index + ").png");
    }

    /**
     * The frame() method, which returns the number of the frame of an animation based off of the time entered.
     * @param time The time into the animation, determines which frame the animation is on
     * @return An int that represents the number of the frame that the animation is on
     */
    public int frame(double time) {
        return (int)((time % (numFrames * duration)) / duration)+1;
    }

    /**
     * The lasrFrame() method, which returns the last frame of an animation.
     * @return An ImageView object that represents the last frame in an animation.
     */
    public ImageView lastFrame() {

        return new ImageView (path + " (" + (numFrames-1) + ").png");
    }

    /**
     * The getPath() method, which returns the path to a frame of the animation.
     * @return A String representing the path to the file containing the frame of the animation.
     */
    public String getPath ()
    {

        return path + " (" + pathNum + ").png";
    }
}
