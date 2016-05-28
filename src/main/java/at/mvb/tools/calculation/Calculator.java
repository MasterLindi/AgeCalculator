package at.mvb.tools.calculation;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Calculator {

    public static DiffDate calcOverallDiffFromCsv(LocalDate refDate, Path path) {
        List<LocalDate> musicBirthDates = CsvReader.readRecords(path);

        return calcOverallDiff(musicBirthDates, refDate);
    }

    public static DiffDate calcOverallDiff(List<LocalDate> musicBirthDates, LocalDate refDate) {
        Period period = calculateTimeDifference(musicBirthDates, refDate);

        long days = period.get(ChronoUnit.DAYS);
        long months = period.get(ChronoUnit.MONTHS);
        long years = period.get(ChronoUnit.YEARS);

        return new DiffDate(years, months, days);
    }

    public static Period calculateTimeDifference(List<LocalDate> musicBirthDates, LocalDate refDate) {
        Period period = Period.ZERO;
        for (LocalDate musicBirthDate : musicBirthDates) {
            period = period.plus(calcSingleDiffDate(musicBirthDate, refDate));
        }
        period = normalizeDays(period);
        return period.normalized();
    }

    private static Period normalizeDays(Period period) {
        int days = period.getDays();
        int months = period.getMonths() + (days / 30);
        days = days % 30;

        return Period.of(period.getYears(), months, days);
    }

    private static Period calcSingleDiffDate(LocalDate date, LocalDate refDate) {
        return Period.between(date, refDate);
    }
}
