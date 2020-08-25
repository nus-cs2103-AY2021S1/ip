package duke.util;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateFormatterTest {
    @Test
    public void extractTimestampInput_dateString_dateObject() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hhmm");
            Date expected = sdf.parse("2020-10-10 1800");
            Date actual = DateFormatter.extractTimestampInput("2020-10-10 1800");
            assertEquals(expected, actual);
        } catch (DukeException | ParseException e) {
            fail("Exception thrown:" + e.getMessage());
        }
    }

    @Test
    public void extractTimestampInput_invalidDateString_dukeExceptionThrown() {
        try {
            Date actual = DateFormatter.extractTimestampInput("2020 Oct 10 1021");
            fail("Exception not thrown");
        } catch (DukeException e) {
            String expectedError = "Invalid input for date. Given '" + "2020 Oct 10 1021" + "', "
                    + "expecting format yyyy-MM-dd hhmm or yyyy-MM-dd.";
            assertEquals(e.getMessage(), expectedError);
        }
    }
    
    @Test
    public void formatDisplay_validDate_validFormat() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hhmm");
            Date date = sdf.parse("2020-10-10 1800");
            String actual = DateFormatter.formatDisplay(date);
            assertEquals("10 Oct 2020 18:00", actual);
        } catch (ParseException e) {
            fail("Exception thrown:" + e.getMessage());
        }
    }
}
