import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.DukeTask;


// To test that the methods defined in abstract class DukeTask works properly
public class DukeTaskTest {
    private static final String tick = "\u2713";
    private static final String cross = "\u2718";
    private static final String description = "TESTING";
    @Test
    public void dukeStatusIconTest() {
        DukeTask task = new DukeTaskStub(description);
        assertEquals(task.getStatusIcon(), cross);
        task.markAsDone();
        assertEquals(task.getStatusIcon(), tick);
    }

    @Test
    public void dukeMarkDoneTest() {
        DukeTask task = new DukeTaskStub(description);
        assertFalse(task.getDoneStatus());
        task.markAsDone();
        assertTrue(task.getDoneStatus());
    }

    @Test
    public void dukeDescriptionTest() {
        DukeTask task = new DukeTaskStub(description);
        assertEquals(task.getDescription(), description);
    }

    @Test
    public void dukeToStringTest() {
        DukeTask task = new DukeTaskStub(description);
        assertEquals(task.toString(), "[" + cross + "] " + description);
        task.markAsDone();
        assertEquals(task.toString(), "[" + tick + "] " + description);
    }
}
