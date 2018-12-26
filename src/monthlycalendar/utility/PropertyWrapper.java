package monthlycalendar.utility;

import monthlycalendar.utility.property.Property;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PropertyWrapper {
    private static final Path root = Paths.get("./properties");
    static {
        try {
            Files.createDirectories(root);
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public PropertyWrapper(String propName) {
        property_ = Property.getInstance(
            root.resolve(propName + ".properties")
        );
    }

    public String getProperty(String key, String defaultVal) {
        try {
            return property_.getProperty(key);
        }
        catch(Property.PropertyNotFoundException e) {
            System.err.println(e.getMessage());
            System.err.println("Use default: " + defaultVal);

            return defaultVal;
        }
    }
    public Color getColorProperty(String key, Color defaultColor) {
        try {
            String colorName = property_.getProperty(key);
            if(colorName.equals("default")) {
                return defaultColor;
            }

            return (Color)Color.class.getField(colorName).get(null);
        }
        catch(Property.PropertyNotFoundException e) {
            System.err.println(e.getMessage());
        }
        catch(NoSuchFieldException | IllegalAccessException e) {
            System.err.println(e);
        }

        return defaultColor;
    }

    //
    private final Property property_;
}
