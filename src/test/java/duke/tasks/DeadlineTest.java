package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

class DeadlineTest {


    @Test
    public void testCreateDeadline() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10", "0");
    }

    @Test
    public void testDeadlineSetComplete() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10", "0");
        deadline.setComplete(true);
        assertTrue(deadline.isComplete);
    }

    @Test
    public void testIncompleteDeadlineToString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10", "0");
        assertEquals("[D][X](!) Test content (by: 1990-10-10 10:10)", deadline.toString());
    }

    @Test
    public void testCompleteDeadlineToString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10", "0");
        deadline.setComplete(true);
        assertEquals("[D][Y](!) Test content (by: 1990-10-10 10:10)", deadline.toString());
    }

    @Test
    public void testIncompleteDeadlineToSaveString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10", "0");
        assertEquals("D/0/0/Test content/1990-10-10 10:10", deadline.toSaveString());
    }

    @Test
    public void testCompleteDeadlineToSaveString() throws DukeException {
        Deadline deadline = new Deadline("Test content", "1990-10-10 10:10", "0");
        deadline.setComplete(true);
        assertEquals("D/0/1/Test content/1990-10-10 10:10", deadline.toSaveString());
    }

}
