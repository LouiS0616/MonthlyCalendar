package monthlycalendar.view.prop;

import monthlycalendar.model.ImmutableDate;


class Parrot extends DayViewPropertyDecider {
    @Override
    public DayViewProperty getViewProperty(ImmutableDate date) {
        return new DayViewProperty(
            null, null, ""
        );
    }

    // singleton
    private Parrot() {}
    static Parrot getInstance() {
        return self_;
    }

    private static final Parrot self_ = new Parrot();
}
