package monthlycalendar.utility.csv;


import monthlycalendar.utility.StringJoiner;

public interface RecordParser<T> {
    T parseRecord(String[] line) throws InvalidRecordException;


    class InvalidRecordException extends Exception {
        protected InvalidRecordException(String line) {
            super(String.format(
                "Invalid line: '%s'.", line
            ));
        }
        protected InvalidRecordException(String[] line) {
            this(new StringJoiner(",").add(line).toString());
        }
    }
}
