package monthlycalendar.model.birthday;

import monthlycalendar.model.ImmutableDate;


public class Birthday {
    Birthday(String name, ImmutableDate date) {
        this.name = name;
        this.date = date;
    }

    public final String name;
    public final ImmutableDate date;
}
