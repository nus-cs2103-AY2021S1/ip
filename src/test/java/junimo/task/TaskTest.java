package junimo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getFormat() {
        assertEquals("deadline <task description> /by <yyyy-MM-dd>", Task.getFormat("deadline"));
        assertEquals("event <event description> /start <yyyy-MM-dd HH:mm> /end <yyyy-MM-dd HH:mm>",
                Task.getFormat("event"));
        assertEquals("todo <task description>", Task.getFormat("todo"));
    }
}
