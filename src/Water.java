/**
 *
 */
public class Water {
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
        return (int)(210-(elapsedTime/300000000.0)*level)+heightChange;
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
