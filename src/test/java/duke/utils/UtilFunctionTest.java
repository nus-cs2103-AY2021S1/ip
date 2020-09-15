package duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exceptions.DateFormatException;
import duke.exceptions.DurationFormatException;



public class UtilFunctionTest {
    @Test
    public void matchPatternTest() {
        Assertions.assertEquals(true,
               UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "ls"));
        Assertions.assertEquals(true,
                UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "list"));
    }

    @Test
    public void formatDateToFormatTest_standardDateInput_standardOutput() throws DateFormatException {
        Assertions.assertEquals("Aug 20 2020 00:00", UtilFunction.formatDateTimeToStandard("2020-08-20"));
    }
    @Test
    public void formatDateToFormatTest_standardTimeInput_standardOutput() throws DateFormatException {
        Assertions.assertEquals("Aug 20 2020 12:30",
                UtilFunction.formatDateTimeToStandard("2020-08-20 12:30"));
    }

    @Test
    public void formateDateToFormatTest_invalidInput_exceptionThrown() {
        try {
            Assertions.assertEquals(0, UtilFunction.formatDateTimeToStandard("20200820"));
            Assertions.fail();
        } catch (DateFormatException e) {
            Assertions.assertEquals("The input format of date is not valid\n"
                    + "please write date in the forms of " + Constants.DF_LOCAL_TIME, e.getMessage());
        }
    }

    @Test
    public void formatDurationToStandard_validInput1_standardFormatReturned()
            throws DurationFormatException, DateFormatException {
        Assertions.assertEquals("Sep 25 2020 10:00 ~ Sep 25 2020 12:30",
                UtilFunction.formatDurationToStandard("2020-09-25 10:00~12:30"));
    }
    @Test
    public void formatDurationToStandard_validInput2_standardFormatReturned()
            throws DurationFormatException, DateFormatException {
        Assertions.assertEquals("Sep 25 2020 10:00 ~ Sep 26 2020 12:30",
                UtilFunction.formatDurationToStandard("2020-09-25 10:00 ~ 2020-09-26 12:30"));
    }

}
