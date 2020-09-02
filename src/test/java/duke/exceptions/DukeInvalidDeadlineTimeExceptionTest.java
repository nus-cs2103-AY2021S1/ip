package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeInvalidDeadlineTimeExceptionTest {

    @Test
    public void testToString() {
        assertEquals(String.format("%s\n%s", Message.ERROR_DEADLINE_TIME, Message.ERROR_TIME_FORMATTING),
                new DukeInvalidDeadlineTimeException().toString());
    }

}
