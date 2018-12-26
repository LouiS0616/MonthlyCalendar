package monthlycalendar.model;

import monthlycalendar.model.birthday.Birthday;
import monthlycalendar.model.birthday.BirthdayModel;
import monthlycalendar.model.holiday.HolidayModel;
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

        if(holidayModel_.isHoliday(date)) {
            push(DAY_TYPE.HOLIDAY, holidayModel_.getTag(date));
        }
        else if(holidayModel_.isSubstituteHoliday(date)) {
            push(DAY_TYPE.SUBSTITUTE, holidayModel_.getSubstituteTag());
        }

        if(BirthdayModel.isSomeonesBirthday(date)) {
            for(Birthday birthday: BirthdayModel.getBirthdays(date)) {
                push(DAY_TYPE.BIRTHDAY, birthday.name);
            }
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

    public boolean isBirthday() {
        return !propertyMap_.get(DAY_TYPE.BIRTHDAY).isEmpty();
    }

    public String getRepresentativeTag() {
        if(isHoliday()) {
            return propertyMap_.get(DAY_TYPE.HOLIDAY).get(0).tag;
        }
        if(isSubstituteHoliday()) {
            return holidayModel_.getSubstituteTag();
        }

        if(isBirthday()) {
            return propertyMap_.get(DAY_TYPE.BIRTHDAY).get(0).tag;
        }

        return "";
    }


    //
    private final ImmutableDate date_;
    private static final HolidayModel holidayModel_ = HolidayModel.create();
}
