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

    public boolean isDayOff() {
        if(contains(DAY_TYPE.HOLIDAY)) {
            return true;
        }
        if(contains(DAY_TYPE.SUBSTITUTE)) {
            return true;
        }

        return date_.dayOfWeek == Calendar.SUNDAY;
    }

    public String getRepresentativeTag() {
        if(contains(DAY_TYPE.HOLIDAY)) {
            return getAnyElem(DAY_TYPE.HOLIDAY).tag;
        }
        if(contains(DAY_TYPE.SUBSTITUTE)) {
            return holidayModel_.getSubstituteTag();
        }

        if(contains(DAY_TYPE.BIRTHDAY)) {
            return getAnyElem(DAY_TYPE.BIRTHDAY).tag;
        }

        return "";
    }


    //
    private final ImmutableDate date_;
    private static final HolidayModel holidayModel_ = HolidayModel.create();
}
