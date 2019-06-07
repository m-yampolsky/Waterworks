import javafx.scene.image.Image;

/**
 * The AnimatedImage Class
 * This class stores an animated image, by storing the path to the location where the frames of the animation are stored. This is used in the WaterWorks program for the Splashscreen animation.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * May 17:
 * Vansh wrote the class to store an array of Image objects and a duration variable to represent length of time that an animation lasts. He wrote a getFrame() method that returned the correct frame depending on the time and the duration of the animation.
 * May 23:
 * Vansh changed the getFrame() method to return the frame the animation was on based off of a parameter passed, and not the time. He also wrote the frame() method which returns a frame based off of the time passed by the uer. He also wrote the lastFrame() method, which returns the last frame of the animation.
 * May 29:
 * Vansh redid the class to no longer store an array of Images, but to store a path location and a frame number. The instance methods and constructor were rewritten to accomodate for this change, and now do not return items using indexes of an array, but return items using the path location.
 * June 4:
 * Vansh wrote the getSplashFrame() and splashFrame() methods to return the correct frame for the Splashscreen animation and to return the number of the frame that the Splashscreen animation is on, respectively.
 * </pre>
 */
class AnimatedImage
{
    /**
     * The path location of the files in which the frames of the animation are stored.
     */
    private final String path;

    /**
     * The number of frames in the animation.
     */
    private final int numFrames;

    /**
     * The duration of the animation.
     */
    private final double duration;

    /**
     * The constructor of the AnimatedImage class, which creates an object of the class.
     * @param path The path where the images used for the animation are located
     * @param nframes The number of frames in the animation
     * @param duratn The duration of the animation
     */
    public AnimatedImage (String path, int nframes, double duratn) {
        this.path = path;
        duration = duratn;
        numFrames = nframes;
    }

    /**
     * The getFrame() method, which returns the frame of an animation based off of the time entered.
     * @param time The time into the animation, determines which frame the animation is on
     * @return An Image object that represents the frame that the animation is currently on
     */
    public Image getFrame(double time) {
        return new Image(path + " (" + Math.max((int)((time % (numFrames * duration)) / duration), 1) + ").png");
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
     * The getSplashFrame() method, which returns the frame of an animation based off of the time entered.
     * @param time The time into the animation, determines which frame the Splashscreen animation is on
     * @return An Image object that represents the frame that the animation is currently on
     */
    public Image getSplashFrame(double time) {
        if (time >= 16) {
            return lastFrame();
        }
        return new Image(path + " (" + Math.max((int)((time % (numFrames * duration)) / duration), 1) + ").png");
    }

    /**
     * The lasrFrame() method, which returns the last frame of an animation.
     * @return An Image object that represents the last frame in an animation.
     */
    private Image lastFrame() {
        return new Image (path + " (" + (numFrames-1) + ").png");
    }
}
