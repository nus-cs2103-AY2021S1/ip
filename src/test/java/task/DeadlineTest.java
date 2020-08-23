package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_createNewDeadlineTask_deadlineTaskInfoReturned() {
        assertEquals("[D][✘] return books (by: Aug 31 2020, 18:00)",
                new Deadline("return books", LocalDateTime.parse("2020-08-31T18:00"), false).toString());
    }
}
