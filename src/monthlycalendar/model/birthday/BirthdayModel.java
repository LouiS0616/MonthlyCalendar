package monthlycalendar.model.birthday;

import monthlycalendar.model.ImmutableDateWithoutYear;
import monthlycalendar.utility.PropertyWrapper;
import monthlycalendar.utility.UncheckedIOException;
import monthlycalendar.utility.csv.CSVReader;
import monthlycalendar.utility.csv.RecordParser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BirthdayModel {
    private static final PropertyWrapper property_ = new PropertyWrapper("model");

    private static final Map<ImmutableDateWithoutYear, List<Birthday>> birthdayMap_ = new HashMap<>();
    static {
        Path path = Paths.get(
            property_.getProperty("birthdayFilePath", "birthday.csv")
        );
        try(CSVReader<Birthday> reader = new CSVReader<>(path, new BirthdayParser())) {
            Birthday birthday = reader.readRecord();
            ImmutableDateWithoutYear date = birthday.date.withoutYear();

            if(birthdayMap_.containsKey(date)) {
                birthdayMap_.get(date).add(birthday);
            }
            else {
                List<Birthday> list = new ArrayList<>();
                list.add(birthday);

                birthdayMap_.put(date, list);
            }
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        catch(RecordParser.InvalidRecordException e) {
            System.err.println(e.getMessage());
        }
    }
}
