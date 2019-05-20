import java.util.HashMap;

public class Resources {
    private static HashMap<String, Object> resources = new HashMap<String, Object>();

    public static void add (String name, Object obj) {
        resources.put(name, obj);
    }

    public static Object get (String name) {
        return resources.get(name);
    }
}
