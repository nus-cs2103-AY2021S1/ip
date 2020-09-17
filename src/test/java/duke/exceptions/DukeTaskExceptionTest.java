package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeTaskExceptionTest {

    @Test
    public void testToString() {
        assertEquals(Message.ERROR_TASK_ERROR, new DukeTaskException().toString());
    }

}
