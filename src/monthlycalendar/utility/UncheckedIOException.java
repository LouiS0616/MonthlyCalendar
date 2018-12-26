package monthlycalendar.utility;

import java.io.IOException;

/*
 * 1.8↑ なら本家の UncheckedIOException を使えるのに。
 */
public class UncheckedIOException extends RuntimeException {
    public UncheckedIOException(IOException e) {
        super(e.getMessage());
    }
}
