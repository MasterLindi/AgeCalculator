package at.mvb.tools.calculation;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculatorTest {

    @Test
    public void runForProductiveResult() throws Exception {
        //ARRANGE
        LocalDate refDate = getRefDate();
        Path path = Paths.get("src/main/resources/Standesliste_Mai_2016.csv");

        //ACT
        DiffDate actual = Calculator.calcOverallDiffFromCsv(refDate, path);

        //ASSERT
        assertNotNull(actual);
        System.out.println(actual.toString());
    }


    @Test
    public void testCalcOverallDiffFromCsv_OnePersonActivated_ResultIsCorrect() throws Exception {
        //ARRANGE
        LocalDate refDate = getRefDate();
        Path path = Paths.get("src/main/resources/Standesliste_Mai_2016_TestWithOnePerson.csv");

        //ACT
        DiffDate actual = Calculator.calcOverallDiffFromCsv(refDate, path);

        //ASSERT
        assertEquals(26, actual.getYears());
        assertEquals(6, actual.getMonths());
        assertEquals(2, actual.getDays());
    }

    @Test
    public void testCalculateTimeDifference_OneDateTime_ResultIsCorrect() throws Exception {
        //ARRANGE
        ArrayList<LocalDate> testDates = new ArrayList<>();
        testDates.add(LocalDate.of(1989, 11, 26));

        LocalDate refDate = getRefDate();

        //ACT

        Period actual = Calculator.calculateTimeDifference(testDates, refDate);


        //ASSERT
        assertEquals(26, actual.getYears());
        assertEquals(6, actual.getMonths());
        assertEquals(2, actual.getDays());
    }

    @Test
    public void testCalcOverallDiff_ThreeDateTime_ResultIsCorrect() throws Exception {
        //ARRANGE
        ArrayList<LocalDate> testDates = new ArrayList<>();
        testDates.add(LocalDate.of(1989, 11, 26));
        testDates.add(LocalDate.of(2016, 5, 26));
        testDates.add(LocalDate.of(2000, 4, 2));

        LocalDate refDate = getRefDate();

        //ACT
        DiffDate actual = Calculator.calcOverallDiff(testDates, refDate);

        //ASSERT
        assertEquals(42, actual.getYears());
        assertEquals(8, actual.getMonths());
        assertEquals(0, actual.getDays());
    }

    @Test
    public void testCalcOverallDiff_SixDatesWithMoreMonthThanPerYear_ResultIsCorrect() throws Exception {
        //ARRANGE
        ArrayList<LocalDate> testDates = new ArrayList<>();
        testDates.add(LocalDate.of(1989, 11, 26));
        testDates.add(LocalDate.of(2016, 5, 26));
        testDates.add(LocalDate.of(2000, 4, 2));
        testDates.add(LocalDate.of(2016, 1, 28));
        testDates.add(LocalDate.of(2016, 1, 28));
        testDates.add(LocalDate.of(2016, 1, 28));

        LocalDate refDate = getRefDate();

        //ACT
        DiffDate actual = Calculator.calcOverallDiff(testDates, refDate);

        //ASSERT
        assertEquals(43, actual.getYears());
        assertEquals(8, actual.getMonths());
        assertEquals(0, actual.getDays());
    }

    private LocalDate getRefDate() {
        return LocalDate.of(2016, 5, 28);
    }
}