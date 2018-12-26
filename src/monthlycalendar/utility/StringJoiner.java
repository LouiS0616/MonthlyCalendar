package monthlycalendar.utility;

/*
 * 1.8↑ なら本家の StringJoiner を使えるのに。
 */
public class StringJoiner {
    public StringJoiner(CharSequence delimiter) {
        delimiter_ = delimiter;
    }
    public StringJoiner add(CharSequence... newElements) {
        for(CharSequence newElement: newElements) {
            if(builder_.length() != 0) {
                builder_.append(delimiter_);
            }

            builder_.append(newElement);
        }

        return this;
    }

    @Override
    public String toString() {
        return builder_.toString();
    }

    //
    private final CharSequence delimiter_;
    private final StringBuilder builder_ = new StringBuilder();
}
