import javafx.scene.image.Image;

/**
 * The AnimatedImage Class
 * This class stores an animated image, by storing many frames of a picture. This is used in the WaterWorks program to show animations of the character moving on the spot.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class AnimatedImage
{
    private String path;
    private int numFrames;

    /**
     * The duration of the animation.
     */
    private double duration;

    /**
     *
     */
    public AnimatedImage (String path, int nframes, double duratn) {
        this.path = path;
        duration = duratn;
        numFrames = nframes;
    }

    public Image getFrame(double time) {
        int index = Math.max((int)((time % (numFrames * duration)) / duration), 1);
        return new Image(path + " (" + index + ").png");
    }

    public int frame(double time) {
        return (int)((time % (numFrames * duration)) / duration)+1;
    }

    public Image lastFrame() {
        return new Image (path + " (" + (numFrames-1) + ").png");
    }
}
