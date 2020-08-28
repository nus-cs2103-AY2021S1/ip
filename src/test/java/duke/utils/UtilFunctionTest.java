package duke.utils;


import duke.exceptions.DateFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class UtilFunctionTest {
    @Test
    public void matchPatternTest() {
        assertEquals(true,
               UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "ls"));
        assertEquals(true,
                UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "list"));
    }

    @Test
    public void formatDateToFormatTest_standardInput_standardOutput() throws DateFormatException {
        assertEquals("Aug 20 2020", UtilFunction.formatDateToStandard("2020-08-20"));
    }

    @Test
    public void formateDateToFormatTest_invalidInput_exceptionThrown() {
        try {
            assertEquals(0, UtilFunction.formatDateToStandard("20200820"));
            fail();
        } catch(DateFormatException e) {
            assertEquals("The input format of date is not valid\n" +
                    "please write date in the forms of " + Constants.DATEFORMAT, e.getMessage());
        }
    }

}
