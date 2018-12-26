package monthlycalendar.model.holiday;


import monthlycalendar.model.ImmutableDate;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


class USHoliday extends Holiday {
    //
    private static final Map<String, String> fixedHolidays_ = new HashMap<>();
    static {
        fixedHolidays_.put("0101", "New Year's Day");
        fixedHolidays_.put("0704", "Independence Day");
        fixedHolidays_.put("1111", "Veterans' Day");
        fixedHolidays_.put("1225", "Christmas Day");
    }
    private static boolean isFixedHoliday(ImmutableDate date) {
        return getFixedHolidayTag(date) != null;
    }
    private static String getFixedHolidayTag(ImmutableDate date) {
        return fixedHolidays_.get(
            String.format("%02d%02d", date.month+1, date.day)
        );
    }

    //
    private static final Map<String, String> happyMondays_ = new HashMap<>();
    static {
        happyMondays_.put("01#3", "Martin Luther King Day");
        happyMondays_.put("02#3", "Presidents Day");
        happyMondays_.put("09#1", "Labor Day");
        happyMondays_.put("10#2", "Columbus Day");
    }
    private static boolean isHappyMonday(ImmutableDate date) {
        return getHappyMondayTag(date) != null;
    }
    private static String getHappyMondayTag(ImmutableDate date) {
        if(date.dayOfWeek != Calendar.MONDAY) {
            return null;
        }

        return happyMondays_.get(
            String.format("%02d#%d", date.month+1, date.dayOfWeekInMonth)
        );
    }

    //
    private static boolean isThanksgivingDay(ImmutableDate date) {
        if(date.month != Calendar.NOVEMBER) {
            return false;
        }
        if(date.dayOfWeek != Calendar.THURSDAY) {
            return false;
        }

        return date.dayOfWeekInMonth == 4;
    }
    private static boolean isMemorialDay(ImmutableDate date) {
        if(date.month != Calendar.MAY) {
            return false;
        }
        if(date.dayOfWeek != Calendar.MONDAY) {
            return false;
        }

        return date.day + 7 > 31;
    }


    //
    @Override
    public String getTag(ImmutableDate date) {
        if(isFixedHoliday(date)) {
            return getFixedHolidayTag(date);
        }
        if(isHappyMonday(date)) {
            return getHappyMondayTag(date);
        }
        if(isMemorialDay(date)) {
            return "Memorial Day";
        }
        if(isThanksgivingDay(date)) {
            return "Thanksgiving Day";
        }

        return null;
    }
    @Override
    public String getSubstituteTag() {
        return "Substitute Holiday";
    }

    // singleton
    private USHoliday() {}
    static USHoliday getInstance() {
        return self_;
    }

    private static final USHoliday self_ = new USHoliday();
}
