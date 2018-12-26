package monthlycalendar.view.prop.decider;

import monthlycalendar.view.prop.color.FirstColorDecider;
import monthlycalendar.view.prop.color.SecondColorDecider;


class Advanced extends DayViewPropertyDecider {
    Advanced() {
        super(
            new FirstColorDecider(), new SecondColorDecider()
        );
    }
}
