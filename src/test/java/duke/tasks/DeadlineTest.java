package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void invalidDate_exceptionThrown() {
        Deadline deadline = new Deadline("hi", "2020-10-19");
        try {
            deadline.hasDate("202020");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, Invalid date.");
        }
    }
}
