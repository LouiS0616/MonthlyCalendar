package monthlycalendar.utility.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class CSVReader<T> implements AutoCloseable {
    public CSVReader(Path path, RecordParser<T> parser) throws IOException {
        br_ = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        parser_ = parser;
    }

    @Override
    public void close() throws IOException {
        br_.close();
    }

    //
    public T readRecord() throws IOException, RecordParser.InvalidRecordException {
        return parser_.parseRecord(br_.readLine().split(","));
    }


    //
    private final BufferedReader br_;
    private final RecordParser<T> parser_;
}
