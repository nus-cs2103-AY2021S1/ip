package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidFileFormatException;
import duke.task.ComplexTask;
import duke.task.Task;
import duke.task.ToDo;

public class CsvConverterTest {
    @Test
    public void testMatchingTasks() {
        try {
            String s1 = "TODO  ,cs2103 tutorial  ,-  ,Not done";
            String s2 = "EVENT  ,eat  ,1200-1400  ,Not done";
            String s3 = "DEADLINE  ,read  ,2pm  ,DonE";

            Task todo = CsvConverter.parseToTask(s1);
            Task event = CsvConverter.parseToTask(s2);
            Task deadline = CsvConverter.parseToTask(s3);
            // Check ToDo
            assertTrue(todo instanceof ToDo);
            assertEquals("cs2103 tutorial", todo.getDescription());
            assertEquals("-", todo.getTime());
            assertFalse(todo.isDone());
            // Check event
            assertTrue(event instanceof ComplexTask);
            assertEquals("eat", event.getDescription());
            assertEquals("1200-1400", event.getTime());
            assertFalse(event.isDone());
            // Check deadline
            assertTrue(deadline instanceof ComplexTask);
            assertEquals("read", deadline.getDescription());
            assertEquals("2pm", deadline.getTime());
            assertTrue(deadline.isDone());

        } catch (InvalidFileFormatException e) {
            fail();
        }
    }

    @Test
    public void testInvalidStrings() {
        String s1 = "TODO  ,cs2103 tutorial ,-  ,Not done";
        String s2 = "DEADLINE  ,read  ,2pm";
        String s3 = "EVENT  ,eat   ,Not done";
        String s4 = "EVENT  ,eat   ,I am not done";
        String s5 = "NOTEVENT  ,eat   ,2-4pm  ,done";
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s1));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s2));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s3));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s4));
        assertThrows(InvalidFileFormatException.class, () -> CsvConverter.parseToTask(s5));
    }
}
