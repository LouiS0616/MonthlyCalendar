package monthlycalendar.model;

import monthlycalendar.model.holiday.Holiday;
import monthlycalendar.model.DayProperty.DAY_TYPE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DayPropertySequence implements Iterable<DayProperty> {
    private final Map<DAY_TYPE, List<DayProperty>> propertyMap_ = new HashMap<>();
    {
        for(DAY_TYPE type: DAY_TYPE.values()) {
            propertyMap_.put(type, new ArrayList<DayProperty>());
        }
    }

    @Override
    public Iterator<DayProperty> iterator() {
        List<DayProperty> work = new ArrayList<>();
        for(Collection<DayProperty> properties: propertyMap_.values()) {
            work.addAll(properties);
        }

        return work.iterator();
    }

    private void push(DayProperty property) {
        propertyMap_.get(property.type).add(property);
    }
    private void push(DAY_TYPE type, String tag) {
        push(new DayProperty(type, tag));
    }


    //
    public DayPropertySequence(ImmutableDate date) {
        date_ = date;

        if(holiday_.isHoliday(date)) {
            push(DAY_TYPE.HOLIDAY, holiday_.getTag(date));
        }
        else if(holiday_.isSubstituteHoliday(date)) {
            push(DAY_TYPE.SUBSTITUTE, holiday_.getSubstituteTag());
        }
    }

    private boolean isHoliday() {
        return !propertyMap_.get(DAY_TYPE.HOLIDAY).isEmpty();
    }
    private boolean isSubstituteHoliday() {
        return !propertyMap_.get(DAY_TYPE.SUBSTITUTE).isEmpty();
    }
    public boolean isDayOff() {
        if(isHoliday()) {
            return true;
        }
        if(isSubstituteHoliday()) {
            return true;
        }

        return date_.dayOfWeek == Calendar.SUNDAY;
    }


    //
    private final ImmutableDate date_;
    private static final Holiday holiday_ = Holiday.create();
}
