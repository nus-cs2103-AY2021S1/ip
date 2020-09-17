package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeInvalidTaskTimeExceptionTest {

    @Test
    public void testToString() {
        assertEquals(String.format("%s\n%s", Message.ERROR_INVALID_TASK_TIME, Message.ERROR_TIME_FORMATTING),
                new DukeInvalidTaskTimeException().toString());
    }

}
