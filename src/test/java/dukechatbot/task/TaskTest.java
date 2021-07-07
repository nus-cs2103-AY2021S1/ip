package dukechatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    private final Task task = new ToDoTask("lol123");

    @Test
    void getSaveFormat_success() {
        assertEquals("T | 0 | lol123", this.task.getSaveFormat());
    }
}
