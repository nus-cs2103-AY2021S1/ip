package task.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void correctDeadlineRepresentation(){
        Deadline deadline = new Deadline("Sample Deadline", "Today");
        assertEquals("[D][N] Sample Deadline (by: Today)", deadline.toString());
        assertEquals("N", deadline.getStatusIcon());
    }

    @Test
    public void testDone(){
        Deadline deadline = new Deadline("Sample Deadline", "Today");
        deadline.markAsDone();
        assertEquals("[D][Y] Sample Deadline (by: Today)", deadline.toString());
        assertEquals("Y", deadline.getStatusIcon());
    }
}
