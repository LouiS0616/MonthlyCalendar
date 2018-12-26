package monthlycalendar.model;

import monthlycalendar.model.birthday.Birthday;
import monthlycalendar.model.birthday.BirthdayModel;
import monthlycalendar.model.holiday.HolidayModel;
import monthlycalendar.model.DayProperty.DAY_TYPE;
import monthlycalendar.utility.MappedSequence;

import java.util.Calendar;


public class DayPropertySequence extends MappedSequence<DAY_TYPE, DayProperty> {
    private void push(DAY_TYPE type, String tag) {
        push(type, new DayProperty(type, tag));
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
        return contains(DAY_TYPE.HOLIDAY);
    }
    private boolean isSubstituteHoliday() {
        return contains(DAY_TYPE.SUBSTITUTE);
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
        return contains(DAY_TYPE.BIRTHDAY);
    }

    public String getRepresentativeTag() {
        if(isHoliday()) {
            return get(DAY_TYPE.HOLIDAY).get(0).tag;
        }
        if(isSubstituteHoliday()) {
            return holidayModel_.getSubstituteTag();
        }

        if(isBirthday()) {
            return get(DAY_TYPE.BIRTHDAY).get(0).tag;
        }

        return "";
    }


    //
    private final ImmutableDate date_;
    private static final HolidayModel holidayModel_ = HolidayModel.create();
}
