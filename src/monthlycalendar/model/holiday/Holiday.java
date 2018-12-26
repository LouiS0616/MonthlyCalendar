package monthlycalendar.model.holiday;

import monthlycalendar.model.ImmutableDate;
import monthlycalendar.utility.PropertyWrapper;
import monthlycalendar.utility.property.Property;

import java.util.Calendar;


public abstract class Holiday {
    public final boolean isHoliday(ImmutableDate date) {
        return getTag(date) != null;
    }
    public abstract String getTag(ImmutableDate date);

    public boolean isSubstituteHoliday(ImmutableDate date) {
        ImmutableDate work = date.prev();
        while(isHoliday(work)) {
            if(work.dayOfWeek == Calendar.SUNDAY) {
                return true;
            }

            work = work.prev();
        }

        return false;
    }
    public abstract String getSubstituteTag();

    //
    public static Holiday create() {
        String countryName = propertyWrapper_.getProperty("country", "default");

        switch(countryName.toLowerCase()) {
        case "jp": case "jpn": case "japan":
        case "default":
            return JpHoliday.getInstance();
        case "us": case "usa":
            return USHoliday.getInstance();
        }

        throw new Property.InvalidPropertyAttributeException(countryName);
    }

    private static final PropertyWrapper propertyWrapper_ = new PropertyWrapper("model");
}
