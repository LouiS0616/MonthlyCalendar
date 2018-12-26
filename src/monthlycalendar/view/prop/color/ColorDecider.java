package monthlycalendar.view.prop.color;

import monthlycalendar.model.DayPropertySequence;
import monthlycalendar.model.ImmutableDate;

import java.awt.Color;


public interface ColorDecider {
    Color decide(ImmutableDate date, DayPropertySequence propSequence);


    class Parrot implements ColorDecider {
        @Override
        public Color decide(ImmutableDate date, DayPropertySequence propSequence) {
            return null;
        }
    }
}
