package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidFileFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Tests the CsvConverter on various inputs.
 */
public class CsvConverterTest {

    /**
     * Tests Csv converter with correctly formatted basic text.
     */
    @Test
    public void testValidInputBasic() {
        try {
            String s1 = "TODO  ,cs2103 tutorial  ,-  ,23 Aug 2020 @ 9.45 PM  ,Not done";
            String s2 = "EVENT  ,eat  ,1200-1400  ,23 Aug 2020 @ 9.45 PM  ,Not done";
            String s3 = "DEADLINE  ,read  ,-  ,23 Aug 2020 @ 9.45 PM  ,Done";
            Task todo = CsvConverter.parseToTask(s1);
            Task event = CsvConverter.parseToTask(s2);
            Task deadline = CsvConverter.parseToTask(s3);
            // Check ToDo
            assertTrue(todo instanceof ToDo);
            assertEquals("cs2103 tutorial", todo.getDescription());
            assertEquals("23 Aug 2020 @ 9.45 PM", todo.getTime());
            assertFalse(todo.isDone());
            // Check event
            assertTrue(event instanceof Event);
            assertEquals("eat", event.getDescription());
            assertEquals("23 Aug 2020 @ 9.45 PM", event.getTime());
            assertFalse(event.isDone());
            // Check deadline
            assertTrue(deadline instanceof Deadline);
            assertEquals("read", deadline.getDescription());
            assertEquals("23 Aug 2020 @ 9.45 PM", deadline.getTime());
            assertTrue(deadline.isDone());
        } catch (InvalidFileFormatException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            fail();
        }
    }

    /**
     * Tests Csv converter with correctly formatted text with multiple complicated variants.
     */
    @Test
    public void testValidInputAdvanced() {
        try {
            String s1 = "TODO  ,cs2103 tutorial  ,-  ,23 Aug 2020 @ 9.45 PM  ,Not Done";
            String s2 = "EVENT  ,eat  ,1200-1400  ,23 Aug 2020 @ 9.45 PM  ,NOt done";
            String s3 = "deadLinE  ,read  ,-  ,23 Aug 2020 @ 9.45 PM  ,done";
            Task todo = CsvConverter.parseToTask(s1);
            Task event = CsvConverter.parseToTask(s2);
            Task deadline = CsvConverter.parseToTask(s3);
            // Check ToDo
            assertTrue(todo instanceof ToDo);
            assertEquals("cs2103 tutorial", todo.getDescription());
            assertEquals("23 Aug 2020 @ 9.45 PM", todo.getTime());
            assertFalse(todo.isDone());
            // Check event
            assertTrue(event instanceof Event);
            assertEquals("eat", event.getDescription());
            assertEquals("23 Aug 2020 @ 9.45 PM", event.getTime());
            assertFalse(event.isDone());
            // Check deadline
            assertTrue(deadline instanceof Deadline);
            assertEquals("read", deadline.getDescription());
            assertEquals("23 Aug 2020 @ 9.45 PM", deadline.getTime());
            assertTrue(deadline.isDone());
        } catch (InvalidFileFormatException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            fail();
        }
    }

    /**
     * Tests CsvConverter with invalid string formats.
     */
    @Test
    public void testInvalidFormat() {

        String s1 = "TODO  ,cs2103 tutorial ,-  ,23 Aug 2020 @ 9.45 PM  ,Not done";
        String s2 = "EVENT  ,eat  ,1200-1400  ,23 Aug 2020 @ 9.45 PM  ,";
        String s3 = "DEADLINE  ,read  , ,23 Aug 2020 @ 9.45 PM  ,Done";
        String s4 = "EVENT  ,eat  ,1200-1400  ,23 Aug 2020 @ 9.45 PM  ,Not done!!";
        String s5 = "NOTEVENT  ,eat   ,2-4pm  ,23 Aug 2020 @ 9.45 PM  ,done";
        String s6 = "NOTEVENT  ,eat   ,2-4pm  ,23 Aug 2020@9.45 PM  ,done";
        // Tests
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s1));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s2));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s3));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s4));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s5));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s6));
    }
}
