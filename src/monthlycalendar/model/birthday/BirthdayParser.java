package monthlycalendar.model.birthday;

import monthlycalendar.model.ImmutableDate;
import monthlycalendar.utility.csv.RecordParser;


public class BirthdayParser implements RecordParser<Birthday> {
    @Override
    public Birthday parseRecord(String[] line) throws InvalidRecordException {
        if(line.length != 2) {
            throw new InvalidRecordException();
        }

        String name = line[0];
        ImmutableDate date;
        {
            String[] work = line[1].split("/");
            if(work.length != 3) {
                throw new InvalidRecordException();
            }

            date = new ImmutableDate(
                Integer.parseInt(work[0]), Integer.parseInt(work[1]), Integer.parseInt(work[2])
            );
        }

        return new Birthday(name, date);
    }
}
