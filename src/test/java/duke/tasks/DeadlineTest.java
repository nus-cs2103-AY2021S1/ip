package duke.tasks;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {



    @Test
    public void testCreateDeadline() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10");
    }

    @Test
    public void testDeadlineSetComplete() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10");
        deadline.setComplete(true);
        assertTrue(deadline.isComplete);
    }

    @Test
    public void testIncompleteDeadlineToString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10");
        assertEquals("[D][X] Test content (by: 1990-10-10 10:10)", deadline.toString());
    }

    @Test
    public void testCompleteDeadlineToString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10");
        deadline.setComplete(true);
        assertEquals("[D][Y] Test content (by: 1990-10-10 10:10)", deadline.toString());
    }

    @Test
    public void testIncompleteDeadlineToSaveString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10");
        assertEquals("D/0/Test content/1990-10-10 10:10", deadline.toSaveString());
    }

    @Test
    public void testCompleteDeadlineToSaveString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10");
        deadline.setComplete(true);
        assertEquals("D/1/Test content/1990-10-10 10:10", deadline.toSaveString());
    }

}
