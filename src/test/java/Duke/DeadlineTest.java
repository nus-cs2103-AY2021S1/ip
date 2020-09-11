package duke;

import task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests if the Deadline Task is correctly implemented.
 *
 * @author Joshua
 */
public class DeadlineTest {

    LocalDateTime dateTime = LocalDateTime.parse("2020-02-20T23:59");
    Deadline unfinishedDeadline = new Deadline("return book ");
    Deadline doneDeadline = new Deadline("return 2 books ");

    /**
     * Tests if the Deadline is saved and loaded in the correct format.
     */
    @Test
    public void testSaveFormat() {
        unfinishedDeadline.setTime(dateTime);
        doneDeadline.setTime(dateTime);
        doneDeadline.completeTask();
        assertEquals("[D] [✗]return book by:20/02/2020 2359", unfinishedDeadline.saveFormat());
        assertEquals("[D] [✓]return 2 books by:20/02/2020 2359", doneDeadline.saveFormat());
    }

    /**
     * Tests if the display format of the Deadline is correctly implemented.
     */
    @Test
    public void testToString() {
        unfinishedDeadline.setTime(dateTime);
        doneDeadline.setTime(dateTime);
        doneDeadline.completeTask();
        assertEquals("[D] [✗]return book (by:Feb 20 2020 23:59PM)", unfinishedDeadline.toString());
        assertEquals("[D] [✓]return 2 books (by:Feb 20 2020 23:59PM)", doneDeadline.toString());
    }
}
