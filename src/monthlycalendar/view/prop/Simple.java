package monthlycalendar.view.prop;

import monthlycalendar.model.DayPropertySequence;
import monthlycalendar.model.ImmutableDate;
import monthlycalendar.view.prop.color.ColorDecider;
import monthlycalendar.view.prop.color.FirstColorDecider;


class Simple extends DayViewPropertyDecider {
    private static final ColorDecider firstColorDecider_ = new FirstColorDecider();

    @Override
    public DayViewProperty getViewProperty(ImmutableDate date) {
        DayPropertySequence propSequence = new DayPropertySequence(date);

        return new DayViewProperty(
            firstColorDecider_.decide(date, propSequence),
            null,
            propSequence.getRepresentativeTag()
        );
    }


    // singleton
    private Simple() {}
    static Simple getInstance() {
        return self_;
    }

    private static final Simple self_ = new Simple();
}
