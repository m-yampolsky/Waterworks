import java.util.HashMap;

/**
 * The Resources class
 * This class stores a HashMap that stores all the resources used during the program, to save loading time during the game.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 2 05.27.2019
 */
class Resources {
    /**
     * The HashMap storing the relationships between each String identifier, and its corresponding resource Object.
     */
    private static final HashMap<String, Object> resources = new HashMap<>();

    /**
     * @param name The String identifier for the resource Object.
     * @param obj The resource Object to load and store in the resources HashMap.
     */
    public static void add (String name, Object obj) {
        resources.put(name, obj);
    }

    /**
     * @param name The String identifier of a corresponding resource Object.
     * @return The corresponding resource Object from the private resources HashMap.
     */
    public static Object get (String name) {
        return resources.get(name);
    }
}
