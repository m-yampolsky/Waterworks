public class Water {
    private long startTime;
    private int heightChange;
    private int level;

    public Water (int lvl) {
        level = lvl;
        startTime = System.nanoTime();
        heightChange = 0;
    }

    public int getHeight () {
        long elapsedTime = System.nanoTime() - startTime;
        return (int)(210-(elapsedTime/300000000.0)*level);
    }

    public int getYValue() {
        return 750-getHeight();
    }
}
