package at.mvb.tools.calculation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {

    private static final String SEPARATOR = ";";


    public static List<LocalDate> readRecords(Path path) {
        final Reader source = getCsvFile(path);
        assert source != null;
        try (BufferedReader reader = new BufferedReader(source)) {
            List<LocalDate> test = reader.lines()
                    .skip(1)
                    .filter(line -> "1".equals(line.split(SEPARATOR)[5]))
                    .map(line -> LocalDate.parse(line.split(SEPARATOR)[4], DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .collect(Collectors.toList());
            return test;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Reader getCsvFile(Path path) {
        try {
            return Files.newBufferedReader(path, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
