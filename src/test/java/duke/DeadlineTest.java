package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineToString_completeTaskCorrectly() {
        Deadline task = new Deadline("return book ", LocalDate.parse("2019-10-13"));
        assertEquals("[D][✘] return book (by: Oct 13 2019)", task.toString());
        task.completeTask();
        assertEquals("[D][✓] return book (by: Oct 13 2019)", task.toString());
    }
}
