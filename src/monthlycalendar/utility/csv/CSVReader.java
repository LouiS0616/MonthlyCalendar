package monthlycalendar.utility.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class CSVReader<T> implements AutoCloseable {
    public CSVReader(Path path, RecordParser<T> parser, boolean hasHeader) throws IOException {
        br_ = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        parser_ = parser;

        if(hasHeader) {
            br_.readLine();
        }
    }

    @Override
    public void close() throws IOException {
        br_.close();
    }

    //
    public T readRecord() throws IOException, RecordParser.InvalidRecordException {
        String line = br_.readLine();

        if(line == null) {
            return null;
        }
        return parser_.parseRecord(line.split(","));
    }

    //
    private final BufferedReader br_;
    private final RecordParser<T> parser_;
}
