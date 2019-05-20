import java.util.HashMap;

/**
 *
 */
public class Resources {
    /**
     * The HashMap storing the relationships between each String identifier, and its corresponding resource Object.
     */
    private static HashMap<String, Object> resources = new HashMap<String, Object>();

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
