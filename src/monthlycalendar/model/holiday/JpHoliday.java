package monthlycalendar.model.holiday;

import monthlycalendar.model.ImmutableDate;
import monthlycalendar.utility.Range;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


class JpHoliday extends Holiday {
    //
    static private final Map<String, String> fixedHolidays_ = new HashMap<>();
    static {
        fixedHolidays_.put("0101", "元日");
        fixedHolidays_.put("0211", "建国記念の日");
        fixedHolidays_.put("0429", "昭和の日");
        fixedHolidays_.put("0503", "憲法記念日");
        fixedHolidays_.put("0504", "みどりの日");
        fixedHolidays_.put("0505", "子供の日");
        fixedHolidays_.put("0811", "山の日");
        fixedHolidays_.put("1103", "文化の日");
        fixedHolidays_.put("1123", "勤労感謝の日");
        fixedHolidays_.put("1223", "天皇誕生日");
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
        happyMondays_.put("01#2", "成人の日");
        happyMondays_.put("07#3", "海の日");
        happyMondays_.put("09#3", "敬老の日");
        happyMondays_.put("10#2", "体育の日");
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
    private static boolean isSpringEquinoxDay(ImmutableDate date) {
        if(date.month != Calendar.MARCH) {
            return false;
        }

        int y = date.year;
        if(new Range(1851, 1900).contains(y)) {
            int d = (int)(19.8277 + 0.242194 * (y - 1980) - ((y - 1983) / 4));
            return d == date.day;
        }
        if(new Range(1900, 1980).contains(y)) {
            int d = (int)(20.8357 + 0.242194 * (y - 1980) - ((y - 1983) / 4));
            return d == date.day;
        }
        if(new Range(1980, 2100).contains(y)) {
            int d = (int)(20.8431 + 0.242194 * (y - 1980) - ((y - 1980) / 4));
            return d == date.day;
        }
        if(new Range(2100, 2151).contains(y)) {
            int d = (int)(21.8510 + 0.242194 * (y - 1980) - ((y - 1980) / 4));
            return d == date.day;
        }

        return date.day == 21;
    }
    private static boolean isAutumnalEquinoxDay(ImmutableDate date) {
        if(date.month != Calendar.SEPTEMBER) {
            return false;
        }

        int y = date.year;
        if(new Range(1851, 1900).contains(y)) {
            int d = (int)(22.2588 + 0.242194 * (y - 1980) - ((y - 1983) / 4));
            return d == date.day;
        }
        if(new Range(1900, 1980).contains(y)) {
            int d = (int)(23.2588 + 0.242194 * (y - 1980) - ((y - 1983) / 4));
            return d == date.day;
        }
        if(new Range(1980, 2100).contains(y)) {
            int d = (int)(23.2488 + 0.242194 * (y - 1980) - ((y - 1980) / 4));
            return d == date.day;
        }
        if(new Range(2100, 2151).contains(y)) {
            int d = (int)(24.2488 + 0.242194 * (y - 1980) - ((y - 1980) / 4));
            return d == date.day;
        }

        return date.day == 23;
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
        if(isSpringEquinoxDay(date)) {
            return "春分の日";
        }
        if(isAutumnalEquinoxDay(date)) {
            return "秋分の日";
        }

        //
        return null;
    }
    @Override
    public String getSubstituteTag() {
        return "振替休日";
    }

    // singleton
    private JpHoliday() {}
    static JpHoliday getInstance() {
        return self_;
    }

    private static final JpHoliday self_ = new JpHoliday();
}
