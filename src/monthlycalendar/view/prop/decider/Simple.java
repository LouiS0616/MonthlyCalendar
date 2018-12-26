package monthlycalendar.view.prop.decider;

import monthlycalendar.view.prop.color.ColorDecider;
import monthlycalendar.view.prop.color.FirstColorDecider;
import monthlycalendar.view.prop.decider.DayViewPropertyDecider;


class Simple extends DayViewPropertyDecider {
    Simple() {
        super(
            new FirstColorDecider(), new ColorDecider.Parrot()
        );
    }
}
