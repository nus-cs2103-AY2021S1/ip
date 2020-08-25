package duke.exception;

import duke.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void testExpectedException() {
        Assertions.assertThrows(DukeException.class, () -> {
            Task t = new Task("asdfg");
            t.validate();
        });
    }
}
