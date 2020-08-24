import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeHandlerTest {

    /**
     * Tests to see if parseDateTime handles Strings properly.
     */
    @Test
    public void parseDateTime_validStrings_success() {
        String input1 = "21-09-2020 0800";
        String input2 = "21-09-2020 0800-21-09-2020 1000";
        String input3 = "23-09-2020 1000";
        String output1 = "2020-09-21T08:00";
        String output3 = "2020-09-23T10:00";
        assertEquals(output1, DateTimeHandler.parseDateTime(input1).toString());
        assertEquals(output3, DateTimeHandler.parseDateTime(input3).toString());
        try {
            DateTimeHandler.parseDateTime(input2);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("is an invalid datetime format! Please use "));
        }
    }

    /**
     * Tests to see if parseEventTimings handles valid Strings properly.
     */
    @Test
    public void parseEventTimings_validStrings_success() {
        String input1 = "21-09-2020 0800-1000";
        String input2 = "21-09-2020 0800-21-09-2020 1000";
        String input3 = "21-09-2020 0800-23-09-2020 1000";
        String output1 = "(2020-09-21T08:00, 2020-09-21T10:00)";
        String output2 = "(2020-09-21T08:00, 2020-09-21T10:00)";
        String output3 = "(2020-09-21T08:00, 2020-09-23T10:00)";
        assertEquals(output1, DateTimeHandler.parseEventTimings(input1).toString());
        assertEquals(output2, DateTimeHandler.parseEventTimings(input2).toString());
        assertEquals(output3, DateTimeHandler.parseEventTimings(input3).toString());
    }

    /**
     * Tests to see if parseEventTimings rejects invalid Strings properly.
     */
    @Test
    public void parseEventTimings_invalidStrings_fail() {
        try {
            String input1 = "21-09-2020 0800-20-09-2020 0800"; //End timing earlier than start time
            DateTimeHandler.parseEventTimings(input1);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("End timing must be later than start timing!"));
        }
        try {
            String input2 = "abcde";
            DateTimeHandler.parseEventTimings(input2);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("is not a valid event timing. Please use either"));
        }
        try {
            String input3 = "21-09-2020 2020- 21-09-2020 20:30";
            DateTimeHandler.parseEventTimings(input3);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("is not a valid event timing. Please use either"));
        }
    }



}
