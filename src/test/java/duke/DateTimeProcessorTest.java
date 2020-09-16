package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.parser.DateTimeProcessor;

/**
 * Test class for the DateTimeProcessor.
 */
public class DateTimeProcessorTest {
    /**
     * Given sample strings, the date time processor should
     * return the formatted string in the correct format.
     */
    @Test
    void testDateTime_correctFormat_isReturned() {
        DateTimeProcessor processor = new DateTimeProcessor();
        String result = processor.getParsedDate("20-10-2021 1245");
        assertEquals("OCTOBER 20 2021 12:45 AM", result);
        result = processor.getParsedDate("10/08/2011");
        assertEquals("AUGUST 10 2011", result);
        result = processor.getParsedDate("tomorrow");
        assertEquals("tomorrow", result);
    }
}
