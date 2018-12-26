package monthlycalendar.view.prop.decider;

import monthlycalendar.view.prop.color.ColorDecider;
import monthlycalendar.view.prop.decider.DayViewPropertyDecider;


class Plain extends DayViewPropertyDecider {
    Plain() {
        super(
            new ColorDecider.Parrot(), new ColorDecider.Parrot(), false
        );
    }
}
