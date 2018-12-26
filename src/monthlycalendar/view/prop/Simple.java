package monthlycalendar.view.prop;

import monthlycalendar.view.prop.color.ColorDecider;
import monthlycalendar.view.prop.color.FirstColorDecider;


class Simple extends DayViewPropertyDecider {
    Simple() {
        super(
            new FirstColorDecider(), new ColorDecider.Parrot()
        );
    }
}
