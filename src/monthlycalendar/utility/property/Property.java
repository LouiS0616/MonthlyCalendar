package monthlycalendar.utility.property;

import monthlycalendar.utility.StringJoiner;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


abstract public class Property {
    private static final Map<Path, Property> propertyMap_ = new HashMap<>();
    public static Property getInstance(Path p) {
        String propName = getPropName(p);

        if(propertyMap_.containsKey(p)) {
            return propertyMap_.get(p);
        }

        Property prop;
        try {
            prop = new ValidProperty(propName, p);
        }
        catch(PropertyFileNotFoundException e) {
            System.err.println(e.getMessage());
            prop = new Stub(propName);
        }

        propertyMap_.put(p, prop);
        return prop;
    }

    public abstract String getProperty(String key) throws PropertyNotFoundException;

    //
    private static String getPropName(Path p) {
        return p.getFileName().toString().split("\\.")[0];
    }

    //
    static class PropertyFileNotFoundException extends Exception {
        PropertyFileNotFoundException(String propName) {
            super(String.format(
                "Cannot read property file named '%s'.", propName
            ));
        }
    }
    static class PropertyNotFoundException extends Exception {
        PropertyNotFoundException(String propName, String key) {
            super(String.format(
                "There is no property named '%s'.",
                new StringJoiner(".").add(propName, key)
            ));
        }
    }
    public static class InvalidPropertyAttributeException extends RuntimeException {
        public InvalidPropertyAttributeException(String attr) {
            super(String.format(
                "Attribute '%s' is not supported.", attr
            ));
        }
    }
}
