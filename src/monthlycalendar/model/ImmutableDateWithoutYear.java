package monthlycalendar.model;

import java.util.Objects;


public final class ImmutableDateWithoutYear {
    ImmutableDateWithoutYear(ImmutableDate date) {
        month = date.month;
        day   = date.day;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) { return true; }

        if(!(other instanceof ImmutableDateWithoutYear)) {
            return false;
        }

        ImmutableDateWithoutYear otherDate = (ImmutableDateWithoutYear)other;
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
