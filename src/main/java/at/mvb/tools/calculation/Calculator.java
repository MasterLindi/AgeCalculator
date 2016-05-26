package at.mvb.tools.calculation;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

public class Calculator {

    public static DiffDate calcOverallDiffFromCsv(LocalDate refDate, Path path) {
        List<LocalDate> musicBirthDates = CsvReader.readRecords(path);

        return calcOverallDiff(musicBirthDates, refDate);
    }

    public static DiffDate calcOverallDiff(List<LocalDate> musicBirthDates, LocalDate refDate) {
        List<DiffDate> diffDates = calculateTimeDifference(musicBirthDates, refDate);

        LongSummaryStatistics yearStat = diffDates.stream()
                .collect(Collectors.summarizingLong(DiffDate::getYears));

        LongSummaryStatistics monthStat = diffDates.stream()
                .collect(Collectors.summarizingLong(DiffDate::getMonths));

        LongSummaryStatistics dayStat = diffDates.stream()
                .collect(Collectors.summarizingLong(DiffDate::getDays));

        return new DiffDate(yearStat.getSum(), monthStat.getSum(), dayStat.getSum());
    }

    public static List<DiffDate> calculateTimeDifference(List<LocalDate> musicBirthDates, LocalDate refDate) {
        return musicBirthDates.stream()
                .map(date -> calcSingleDiffDate(date, refDate))
                .collect(Collectors.toList());
    }

    private static DiffDate calcSingleDiffDate(LocalDate date, LocalDate refDate) {
        LocalDate tempDateTime = LocalDate.from(date);
        long years = tempDateTime.until(refDate, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(refDate, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(refDate, ChronoUnit.DAYS);

        return new DiffDate(years, months, days);
    }
}
