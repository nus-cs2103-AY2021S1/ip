package duke.datetime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import static duke.datetime.DateTimeHandler.parseDateTime;
import static duke.datetime.DateTimeHandler.parseEventTimings;
import static duke.datetime.DateTimeHandler.STANDARD_DATETIME_FORMAT;


public class DateTimeHandlerTest {

    @Test
    public void testParseDateTime_validStrings_success() {
        String time1 = "24-09-2000 1800";
        String time2 = "24-12-2020 0100";
        String expected1 = "2000-09-24T18:00";
        String expected2 = "2020-12-24T01:00";
        LocalDateTime localDateTime1 = parseDateTime(time1);
        assertEquals(expected1, localDateTime1.toString());
        assertEquals(expected2, parseDateTime(time2).toString());
        assertEquals(time1, STANDARD_DATETIME_FORMAT.format(localDateTime1));
    }

    @Test
    public void testParseDateTime_invalidStrings_fail() {
        String input1 = "abcde";
        String input2 = "24-09-2020 100";
        String invalidError = "is an invalid datetime format! Please use";
        try {
            parseDateTime(input1);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(invalidError));
        }
        try {
            parseDateTime(input2);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(invalidError));
        }

    }

    @Test
    public void testParseEventTimings_validStrings_success() {
        String input1 = "24-09-2000 1800-2000";
        String input2 = "24-09-2000 1800-26-09-2000 2000";
        String expected1 = "(2000-09-24T18:00, 2000-09-24T20:00)";
        String expected2 = "(2000-09-24T18:00, 2000-09-26T20:00)";
        assertEquals(expected1, parseEventTimings(input1).toString());
        assertEquals(expected2, parseEventTimings(input2).toString());
    }

    @Test
    public void testParseEventTimings_invalidStrings_fail() {
        String input1 = "abcde";
        String input2 = "24-09-2000 180026-09-2000 2000";
        String input3 = "22-09-2000 1800 20-09-2000 2000";
        String input4 = "22-09-2000 1800-1600";
        String invalidError = " is not a valid event timing. Please use either\n";
        String endEarlierThanStartError = "End timing must be later than start timing!";
        try {
            parseEventTimings(input1);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(invalidError));
        }
        try {
            parseEventTimings(input2);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(invalidError));
        }
        try {
            parseEventTimings(input3);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(endEarlierThanStartError));
        }
        try {
            parseEventTimings(input4);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(endEarlierThanStartError));
        }
    }

}
