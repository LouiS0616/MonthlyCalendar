package monthlycalendar.view.prop.color;

import monthlycalendar.model.DayPropertySequence;
import monthlycalendar.model.ImmutableDate;
import monthlycalendar.utility.property.PropertyWrapper;

import java.awt.Color;
import java.util.Calendar;


/**
 * Property: color
 *      - dayOff    (default: red)
 *      - saturday  (default: blue)
 */
public class FirstColorDecider implements ColorDecider {
    private static final Color dayOffColor_;
    private static final Color saturdayColor_;
    static {
        final PropertyWrapper property_ = new PropertyWrapper("color");

        dayOffColor_ = property_.getColorProperty("dayOff", Color.RED);
        saturdayColor_ = property_.getColorProperty("saturday", Color.BLUE);
    }

    @Override
    public Color decide(ImmutableDate date, DayPropertySequence propSequence) {
        if(propSequence.isDayOff()) {
            return dayOffColor_;
        }
        if(date.dayOfWeek == Calendar.SATURDAY) {
            return saturdayColor_;
        }

        return null;
    }
}
