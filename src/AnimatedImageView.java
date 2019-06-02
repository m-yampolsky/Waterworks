import javafx.scene.image.ImageView;

/**
 * The AnimatedImage Class
 * This class stores an animated image, by storing many frames of a picture. This is used in the WaterWorks program to show animations of the character moving on the spot.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
public class AnimatedImageView
{
    private String path;
    private int numFrames;
    private int pathNum;

    /**
     * The duration of the animation.
     */
    private double duration;

    /**
     *
     */
    public AnimatedImageView (String path, int nframes, double duratn) {
        this.path = path;
        duration = duratn;
        numFrames = nframes;
    }

    public ImageView getFrame(double time) {
        int index = Math.max((int)((time % (numFrames * duration)) / duration), 1);
        pathNum = index;
        return new ImageView(path + " (" + index + ").png");
    }

    public int frame(double time) {
        return (int)((time % (numFrames * duration)) / duration)+1;
    }

    public ImageView lastFrame() {
        return new ImageView (path + " (" + (numFrames-1) + ").png");
    }

    public String getPath ()
    {
        return path + " (" + pathNum + ").png";
    }
}
