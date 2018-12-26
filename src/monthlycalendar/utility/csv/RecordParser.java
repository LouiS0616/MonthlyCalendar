package monthlycalendar.utility.csv;


public interface RecordParser<T> {
    T parseRecord(String[] line) throws InvalidRecordException;


    class InvalidRecordException extends Exception {
    }
}
