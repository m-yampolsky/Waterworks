import javafx.scene.image.Image;

/**
 * The AnimatedImage Class
 * This class stores an animated image, by storing many frames of a picture. This is used in the WaterWorks program to show animations of the character moving on the spot.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class AnimatedImage
{
    /**
     * The Image array holding all the frames of the animation.
     */
    private Image[] frames;

    /**
     * The duration of the animation.
     */
    private double duration;


    /**
     * @param imgArr Value to set the instance Image array variable holding animation frames.
     * @param duratn Value to set the instance duration varible holding the time length of the animation.
     */
    public AnimatedImage (Image[] imgArr, double duratn) {
        frames = imgArr;
        duration = duratn;
    }

    /**
     * @param time The time since the beginning of the animation.
     * @return The correct image to display for the animation at the passed time value.
     */
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public int frame(double time) {
        return (int)((time % (frames.length * duration)) / duration)+1;
    }

    public Image lastFrame() {
        return frames[frames.length-1];
    }

    public Image getFrame (int f) {
        return frames[f-1];
    }
}
