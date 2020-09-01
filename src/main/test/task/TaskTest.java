package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    Task task = new ToDoTask("lol123");

    @Test
    void get_save_format_success() {
        assertEquals("T | 0 | lol123", this.task.getSaveFormat());
    }
}
