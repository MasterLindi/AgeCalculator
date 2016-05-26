package at.mvb.tools.calculation;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        List<DiffDate> diffDates = Calculator.calculateTimeDifference(testDates, refDate);


        //ASSERT
        assertEquals(1, diffDates.size());
        DiffDate actual = diffDates.get(0);
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
        assertEquals(7, actual.getMonths());
        assertEquals(30, actual.getDays());
    }

    private LocalDate getRefDate() {
        return LocalDate.of(2016, 5, 28);
    }
}