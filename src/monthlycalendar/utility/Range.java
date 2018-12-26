package monthlycalendar.utility;


public class Range {
    public Range(int start, int stop) {
        start_ = start;
        stop_  = stop;
    }

    public boolean contains(int num) {
        return start_ <= num && num < stop_;
    }
    public boolean closed(int num) {
        return start_ <= num && num <= stop_;
    }

    //
    private final int start_;
    private final int stop_;
}
