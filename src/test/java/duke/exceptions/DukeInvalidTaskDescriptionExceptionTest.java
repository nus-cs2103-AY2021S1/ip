package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeInvalidTaskDescriptionExceptionTest {

    @Test
    public void testToString() {
        assertEquals(Message.ERROR_INVALID_TASK_DESCRIPTION, new DukeInvalidTaskDescriptionException().toString());
    }

}
