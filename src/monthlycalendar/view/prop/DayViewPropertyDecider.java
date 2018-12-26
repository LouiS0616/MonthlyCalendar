package monthlycalendar.view.prop;

import monthlycalendar.model.ImmutableDate;
import monthlycalendar.utility.property.Property;
import monthlycalendar.utility.PropertyWrapper;


public abstract class DayViewPropertyDecider {
    public abstract DayViewProperty getViewProperty(ImmutableDate date);

    private static final PropertyWrapper propertyWrapper_ = new PropertyWrapper("view");

    public static DayViewPropertyDecider create() {
        String viewMode = propertyWrapper_.getProperty(
            "viewMode", "default"
        );

        switch(viewMode.toLowerCase()) {
        case "parrot":
            return Parrot.getInstance();
        case "simple":
        case "default":
            return Simple.getInstance();
        case "advanced":
            break;
        }

        throw new Property.InvalidPropertyAttributeException(viewMode);
    }
}
