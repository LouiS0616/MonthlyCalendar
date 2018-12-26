package monthlycalendar.model;

import java.util.Objects;


public class ImmutableDateWithoutYear {
    ImmutableDateWithoutYear(ImmutableDate date) {
        month = date.month;
        day   = date.day;
    }

    @Override
    public boolean equals(Object other) {
        ImmutableDateWithoutYear otherDate;

        if(other instanceof ImmutableDate) {
            otherDate = ((ImmutableDate)other).withoutYear();
        }
        else if(other instanceof ImmutableDateWithoutYear) {
            otherDate = (ImmutableDateWithoutYear)other;
        }
        else {
            return false;
        }

        return month == otherDate.month
            && day   == otherDate.day
        ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(month, day);
    }

    public final int month;
    public final int day;
}
