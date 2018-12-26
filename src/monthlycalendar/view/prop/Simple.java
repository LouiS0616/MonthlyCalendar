package monthlycalendar.view.prop;

import monthlycalendar.model.DayProperty;
import monthlycalendar.model.DayPropertySequence;
import monthlycalendar.model.ImmutableDate;
import monthlycalendar.utility.PropertyWrapper;

import java.awt.Color;
import java.util.Calendar;


class Simple extends DayViewPropertyDecider {
    private static final PropertyWrapper propertyWrapper_ = new PropertyWrapper("view");

    private static final Color dayOffColor_;
    private static final Color saturdayColor_;
    private static final Color backGround_ = null;

    static {
        dayOffColor_ = propertyWrapper_.getColorProperty("dayOffColor", Color.RED);
        saturdayColor_ = propertyWrapper_.getColorProperty("saturdayColor", Color.BLUE);
    }

    @Override
    public DayViewProperty getViewProperty(ImmutableDate date) {
        DayPropertySequence prop = new DayPropertySequence(date);

        Color foreGround = null;
        String tag = "";

        if(prop.isDayOff()) {
            foreGround = dayOffColor_;
            tag = prop.getRepresentativeTag();
        }
        else if(date.dayOfWeek == Calendar.SATURDAY) {
            foreGround = saturdayColor_;
        }

        return new DayViewProperty(
            foreGround, backGround_, tag
        );
    }

    // singleton
    private Simple() {}
    static Simple getInstance() {
        return self_;
    }

    private static final Simple self_ = new Simple();
}
