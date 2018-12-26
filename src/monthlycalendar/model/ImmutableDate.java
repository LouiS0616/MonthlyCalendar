package monthlycalendar.model;

import java.util.Calendar;


public final class ImmutableDate {
    public ImmutableDate(Calendar calendar) {
        year             = calendar.get(Calendar.YEAR);
        month            = calendar.get(Calendar.MONTH);
        day              = calendar.get(Calendar.DATE);
        dayOfWeek        = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);

        calendar_ = calendar;
    }

    public ImmutableDate prev() {
        Calendar work = (Calendar)calendar_.clone();
        work.add(Calendar.DATE, -1);

        return new ImmutableDate(work);
    }
    public String dayStr() {
        return Integer.toString(day);
    }

    public final int year;
    public final int month;
    public final int day;
    public final int dayOfWeek;
    public final int dayOfWeekInMonth;

    private final Calendar calendar_;
}
