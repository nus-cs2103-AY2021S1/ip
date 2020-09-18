package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mattbot.tasks.Task;
import mattbot.tasks.Todo;





class TodoTest {
    final Todo t1 = new Todo("Test 1", "[T]");
    final Task t1Done = new Todo("Test 1", "[T]");

    @Test
    void taskName() {
        assertEquals("Test 1", t1.getTaskName());
    }

    @Test
    void taskCompleted() {
        t1Done.setDone();
        assertEquals(false, t1.getTaskCompleted());
        assertEquals(true, t1Done.getTaskCompleted());
    }

    @Test
    void taskType() {
        assertEquals("[T]", t1.getTaskType());
    }

    @Test
    void taskDate() {
        assertEquals(null, t1.getTaskDate());
    }
}
