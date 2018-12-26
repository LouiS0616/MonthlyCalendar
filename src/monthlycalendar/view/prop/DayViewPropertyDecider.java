package monthlycalendar.view.prop;

import monthlycalendar.model.DayPropertySequence;
import monthlycalendar.model.ImmutableDate;
import monthlycalendar.utility.property.Property;
import monthlycalendar.utility.PropertyWrapper;
import monthlycalendar.view.prop.color.ColorDecider;


public class DayViewPropertyDecider {
    DayViewPropertyDecider(ColorDecider foregroundDecider, ColorDecider backgroundDecider) {
        foregroundDecider_ = foregroundDecider;
        backgroundDecider_ = backgroundDecider;
    }

    public DayViewProperty getViewProperty(ImmutableDate date) {
        DayPropertySequence propSequence = new DayPropertySequence(date);

        return new DayViewProperty(
            foregroundDecider_.decide(date, propSequence),
            backgroundDecider_.decide(date, propSequence),
            propSequence.getRepresentativeTag()
        );
    }

    //
    private static final PropertyWrapper propertyWrapper_ = new PropertyWrapper("view");

    public static DayViewPropertyDecider create() {
        String viewMode = propertyWrapper_.getProperty(
            "viewMode", "default"
        );

        switch(viewMode.toLowerCase()) {
        case "plain":
            return new Plain();
        case "simple":
        case "default":
            return new Simple();
        case "advanced":
            break;
        }

        throw new Property.InvalidPropertyAttributeException(viewMode);
    }

    //
    private final ColorDecider foregroundDecider_;
    private final ColorDecider backgroundDecider_;
}
