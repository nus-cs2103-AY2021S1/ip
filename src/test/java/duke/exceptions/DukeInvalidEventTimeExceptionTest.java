package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeInvalidEventTimeExceptionTest {

    @Test
    public void testToString() {
        assertEquals(String.format("%s\n%s", Message.ERROR_EVENT_TIME, Message.ERROR_TIME_FORMATTING),
                new DukeInvalidEventTimeException().toString());
    }

}
