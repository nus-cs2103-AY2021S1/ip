package junimo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getFormat() {
        assertEquals("deadline <task description> /by <yyyy-mm-dd>", Task.getFormat("deadline"));
        assertEquals("event <event description> /at <event location>", Task.getFormat("event"));
        assertEquals("todo <task description>", Task.getFormat("todo"));
    }
}
