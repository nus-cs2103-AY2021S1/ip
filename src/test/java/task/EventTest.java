package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_createNewEventTask_eventTaskInfoReturned() {
        assertEquals("[E][\u2717] 2103T Team Meeting (at: Aug 26 2020, 17:00)",
                new Event("2103T Team Meeting", LocalDateTime.parse("2020-08-26T17:00"), false).toString());
    }
}
