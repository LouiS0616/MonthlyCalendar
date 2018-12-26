package monthlycalendar.model;

import monthlycalendar.model.holiday.Holiday;
import monthlycalendar.model.DayProperty.DAY_TYPE;
import monthlycalendar.utility.PropertyWrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class DayPropertySequence implements Iterable<DayProperty> {
    /*
    private final Map<DAY_TYPE, List<DayProperty>> beanMap_ = new HashMap<>();
    {
        for(DAY_TYPE type: DAY_TYPE.values()) {
            beanMap_.put(type, new ArrayList<DayProperty>());
        }
    }
    */


    private final List<DayProperty> beans_;

    @Override
    public Iterator<DayProperty> iterator() {
        return beans_.iterator();
    }


    //
    public DayPropertySequence(ImmutableDate date) {
        beans_ = new ArrayList<>();
        date_ = date;

        if(holiday_.isHoliday(date)) {
            beans_.add(
                new DayProperty(DayProperty.DAY_TYPE.HOLIDAY, holiday_.getTag(date))
            );
        }
        else if(holiday_.isSubstituteHoliday(date)) {
            beans_.add(
                new DayProperty(DayProperty.DAY_TYPE.SUBSTITUTE, holiday_.getSubstituteTag())
            );
        }
    }

    private boolean isHoliday() {
        for(DayProperty bean: beans_) {
            if(bean.type == DayProperty.DAY_TYPE.HOLIDAY) return true;
        }

        return false;
    }
    public boolean isDayOff() {
        if(isHoliday()) {
            return true;
        }
        if(date_.dayOfWeek == Calendar.SUNDAY) {
            return true;
        }

        for(DayProperty bean: beans_) {
            if(bean.type == DayProperty.DAY_TYPE.SUBSTITUTE) {
                return true;
            }
        }

        return false;
    }

    //
    private final ImmutableDate date_;
    private static final Holiday holiday_ = Holiday.create();

    private static final PropertyWrapper propertyWrapper_ = new PropertyWrapper("model");
}
