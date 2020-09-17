package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the deadline class
 */

public class DeadlineTest {
    @Test
    public void DeadlineTest(){
        Deadline deadline = new Deadline(Task.TASK_DEADLINE, Task.DONE, "Homework", "2020-09-03");
        assertEquals(deadline.toString(), "[DeadLine][v] Homework/by: Sep 03 2020");

    }
}
