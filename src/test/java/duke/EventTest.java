package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventTaskToString_completeTaskCorrectly() {
        Event task = new Event("beach party ", LocalDate.parse("2020-12-03"));
        assertEquals("[E][✘] beach party (at: Dec 03 2020)", task.toString());
        task.completeTask();
        assertEquals("[E][✓] beach party (at: Dec 03 2020)", task.toString());
    }
}