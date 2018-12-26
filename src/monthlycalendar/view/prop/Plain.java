package monthlycalendar.view.prop;

import monthlycalendar.view.prop.color.ColorDecider;


class Plain extends DayViewPropertyDecider {
    Plain() {
        super(
            new ColorDecider.Parrot(), new ColorDecider.Parrot(), false
        );
    }
}
