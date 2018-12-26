package monthlycalendar.model;


public class ImmutableDateWithoutYear {
    ImmutableDateWithoutYear(ImmutableDate date) {
        month = date.month;
        day   = date.day;
    }

    public final int month;
    public final int day;
}
