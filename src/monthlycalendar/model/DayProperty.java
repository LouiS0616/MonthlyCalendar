package monthlycalendar.model;


public class DayProperty {
    public enum DAY_TYPE {
        HOLIDAY, SUBSTITUTE, TRADITIONAL, BIRTHDAY, ANNIVERSARY, SCHEDULE
    }

    DayProperty(DAY_TYPE type, String tag) {
        this.type = type;
        this.tag  = tag;
    }

    public final DAY_TYPE type;
    public final String tag;
}
