/**
 * The Water class
 * This class represents the water of the lake from the Wateworks game. It stores the water level of the lake, the y coordinate where the top of the water will appear, as well as the level being played.
 * Depending on what lake you are playing on, the image used to display the water level of the lake will change.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 *
 * <pre>
 * Version History:
 * May 16:
 * Vansh wrote the Water class with the height Change and startTime properties, as well as the getYValue() method.
 * May 18:
 * Maria added the level property to class to change the speed the water drops at depending on the lake they are playing on.
 * Vansh modified the getHeight() method to modify the height at different rates depending on the lake level being played on.
 * </pre>
 */
class Water {
    /**
     * The number of elapsed nanoseconds since the beginning of runtime
     * up until the creation of the current Water Object.
     */
    private long startTime;

    /**
     * The amount of difference in height adjusted accordingly in the Game
     * class to the user collecting water efficient or inefficient devices.
     */
    private int heightChange;

    /**
     * The Game level currently being played, used to calculate the score.
     */
    private int level;

    /**
     * @param lvl The Game level currently being played.
     */
    public Water (int lvl) {
        level = lvl;
        startTime = System.nanoTime();
        heightChange = 0;
    }

    /**
     * @return The height of the lake in the Game, based on elapsed time and heightChange.
     */
    public int getHeight () {
        long elapsedTime = System.nanoTime() - startTime;
        return (int)(210-(elapsedTime/300000000.0)*(level*0.5))+heightChange;
    }

    /**
     * @return The proper Y-value of the lake.
     */
    public int getYValue() {
        return 750-getHeight();
    }

    /**
     * @param deviation The amount to add or subtract from the private global heightChange variable.
     */
    public void changeHeight(int deviation) {
        heightChange += deviation;
    }
}
