package monthlycalendar.model.birthday;

import monthlycalendar.model.ImmutableDate;
import monthlycalendar.model.ImmutableDateWithoutYear;
import monthlycalendar.utility.MappedSequence;
import monthlycalendar.utility.property.PropertyWrapper;
import monthlycalendar.utility.csv.CSVReader;
import monthlycalendar.utility.csv.RecordParser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class BirthdayModel {
    private static final PropertyWrapper property_ = new PropertyWrapper("model");

    private static final
    MappedSequence<ImmutableDateWithoutYear, Birthday> birthdaySequence_ = new MappedSequence<>();

    static {
        Path path = Paths.get(
            property_.getProperty("birthdayFilePath", "birthday.csv")
        );

        try(CSVReader<Birthday> reader = new CSVReader<>(path, new BirthdayParser(), true)) {

            Birthday birthday;
            while((birthday = reader.readRecord()) != null) {
                birthdaySequence_.push(
                    birthday.date.withoutYear(), birthday
                );
            }
        }
        catch(IOException e) {
            System.err.println(String.format("Failed to read '%s'.", path));
        }
        catch(RecordParser.InvalidRecordException e) {
            System.err.println(e.getMessage());
        }
    }

    //
    public static boolean isSomeonesBirthday(ImmutableDate date) {
        return birthdaySequence_.contains(date.withoutYear());
    }
    public static List<Birthday> getBirthdays(ImmutableDate date) {
        return birthdaySequence_.get(date.withoutYear());
    }
}
