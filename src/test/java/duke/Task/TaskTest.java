package duke.Task;

import duke.Exception.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    private Task task;

    @BeforeEach
    void init() {
        task = new TaskTestStub("Test");
    }

    @Test
    void getDescription() {
        assertEquals("Test", task.getDescription());
    }

    @Test
    void stringConversion() {
        assertEquals("[✘] Test", task.toString());
    }

    @Test
    void markAsDone_notDoneYet_success() throws DukeException {
        assertEquals(0, task.getStatusCode());
        task.markAsDone();
        assertEquals(1, task.getStatusCode());
    }

    @Test
    void markAsDone_alreadyDone_exceptionThrown() {
        try {
            task.markAsDone();
            task.markAsDone();
            fail();
        } catch (DukeException e) {
            assertEquals("This task is already completed!",e.getMessage());
        }
    }

}
