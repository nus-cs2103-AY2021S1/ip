package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.messages.Message;

public class DukeInvalidScheduleInputExceptionTest {

    @Test
    public void testToString() {
        assertEquals(String.format("%s\n%s", Message.ERROR_SCHEDULE_INPUT, Message.ERROR_DATE_FORMATTING),
                new DukeInvalidScheduleInputException().toString());
    }

}
