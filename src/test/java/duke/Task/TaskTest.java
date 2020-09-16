package duke.task;

import duke.exception.DukeException;

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
        assertEquals("[✘][4] Test", task.toString());
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
            assertEquals("this task is already completed!",e.getMessage());
        }
    }

}
