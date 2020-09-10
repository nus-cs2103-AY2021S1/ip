package duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exceptions.DateFormatException;



public class UtilFunctionTest {
    @Test
    public void matchPatternTest() {
        Assertions.assertEquals(true,
               UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "ls"));
        Assertions.assertEquals(true,
                UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "list"));
    }

    @Test
    public void formatDateToFormatTest_standardInput_standardOutput() throws DateFormatException {
        Assertions.assertEquals("Aug 20 2020", UtilFunction.formatDateToStandard("2020-08-20"));
    }

    @Test
    public void formateDateToFormatTest_invalidInput_exceptionThrown() {
        try {
            Assertions.assertEquals(0, UtilFunction.formatDateToStandard("20200820"));
            Assertions.fail();
        } catch (DateFormatException e) {
            Assertions.assertEquals("The input format of date is not valid\n"
                    + "please write date in the forms of " + Constants.DATEFORMAT, e.getMessage());
        }
    }

}
