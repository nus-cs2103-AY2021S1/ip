import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateAndTimeTest {

    @Test
    public void dateTime_passedInCorrectInputFormat_correctStringFormat() {
        //set up
        DateAndTime dT = new DateAndTime("26/08/2020", "2359");
        String dTStr = dT.toString();

        assertEquals("26th of August 2020, 11.59PM", dTStr);
    }

    @Test
    public void dateTime_passedInIncorrectInputFormat_exceptionThrown() {
        Exception exception = assertThrows(DateTimeParseException.class, () -> new DateAndTime(
                "26-08-2020", "2300"));
        String expected = "Text '26-08-2020' could not be parsed at index 2";
        String actual = exception.getMessage();

        assertTrue(actual.contains(expected));
    }

}
