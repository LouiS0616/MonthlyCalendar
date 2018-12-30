package monthlycalendar.utility;

import java.util.Arrays;
import java.util.List;

/*
 * 1.8↑ なら本家の StringJoiner を使えるのに。
 */
public class StringJoiner {
    public StringJoiner(CharSequence delimiter) {
        delimiter_ = delimiter;
    }
    public StringJoiner add(CharSequence... newElements) {
        if(newElements.length == 0) return this;

        builder_.append(newElements[0]);

        List<CharSequence> list = Arrays.asList(newElements).subList(1, newElements.length);
        for(CharSequence newElement: list) {
            builder_.append(delimiter_);
            builder_.append(newElement);
        }

        return this;

        /*
        if(newElements.length == 0) return this;

        for(CharSequence newElement: newElements[1:]) {
        }
         */
    }

    @Override
    public String toString() {
        return builder_.toString();
    }

    //
    private final CharSequence delimiter_;
    private final StringBuilder builder_ = new StringBuilder();
}
