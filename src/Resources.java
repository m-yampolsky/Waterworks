import java.util.HashMap;

/**
 * The Resources class
 * This class stores a HashMap that stores all the resources used during the program, to save loading time during the game.
 *
 * <h2>Course Information:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.06.2019
 *
 * <pre>
 * Version History:
 * May 20:
 * Vansh created the class, as well as all the implementation including the global static HashMap resources as well as the static add and get methods.
 * </pre>
 */
class Resources {
    /**
     * The HashMap storing the relationships between each String identifier, and its corresponding resource Object.
     */
    private static HashMap<String, Object> resources = new HashMap<String, Object>();

    /**
     * This method is used to add Objects to the static resources Hashmap with a String identifier, to be retrieved later on.
     * @param name The String identifier for the resource Object.
     * @param obj The resource Object to load and store in the resources HashMap.
     */
    public static void add (String name, Object obj) {
        resources.put(name, obj);
    }

    /**
     * This method is used to retrieve an Obejct added to the Hashmap during loading with its String identifier.
     * @param name The String identifier of a corresponding resource Object.
     * @return The corresponding resource Object from the private resources HashMap.
     */
    public static Object get (String name) {
        return resources.get(name);
    }
}
