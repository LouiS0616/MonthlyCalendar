package monthlycalendar.view.prop.color;

import monthlycalendar.model.DayPropertySequence;
import monthlycalendar.model.ImmutableDate;

import java.awt.Color;


public class SecondColorDecider implements ColorDecider {
    @Override
    public Color decide(ImmutableDate date, DayPropertySequence propSequence) {
        return null;
    }
}
