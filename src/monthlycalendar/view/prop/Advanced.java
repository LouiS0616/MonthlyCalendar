package monthlycalendar.view.prop;

import monthlycalendar.view.prop.color.FirstColorDecider;
import monthlycalendar.view.prop.color.SecondColorDecider;


class Advanced extends DayViewPropertyDecider {
    Advanced() {
        super(
            new FirstColorDecider(), new SecondColorDecider()
        );
    }
}
