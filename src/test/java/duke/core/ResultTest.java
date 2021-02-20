package duke.core;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @org.junit.jupiter.api.Test
    void testGetMessage_resultWithSpecificType_correspondingMessage() {
        Result result = new Result("Noted. I have removed this task:\n"
                + "1.[T][\u2718] a b c\nNow you have 5 tasks in the list.",
                true,
                MessageType.COMMAND_FOUND_MESSAGE);
        assertEquals("Noted. I have removed this task:\n"
                + "1.[T][\u2718] a b c\nNow you have 5 tasks in the list.",
                result.getMessage());
    }

    @org.junit.jupiter.api.Test
    void testIsContinuing_resultWithSpecificType_correspondingContinuingState() {
        Result result = new Result("Noted. I have removed this task:\n"
                + "1.[T][\u2718] a b c\nNow you have 5 tasks in the list.",
                true,
                MessageType.COMMAND_FOUND_MESSAGE);
        assertEquals(true,
                result.isContinuing());
    }
}