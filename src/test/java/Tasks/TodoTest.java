package Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    final Todo t1 = new Todo("Test 1","[T]");
    final task t1Done = new Todo("Test 1","[T]");

    @Test
    void taskName() {
        assertEquals("Test 1",t1.getTaskName());
    }

    @Test
    void taskCompleted() {
        t1Done.setDone();
        assertEquals(false,t1.getTaskCompleted());
        assertEquals(true,t1Done.getTaskCompleted());
    }

    @Test
    void taskType() {
        assertEquals("[T]",t1.getTaskType());
    }

    @Test
    void taskDate() {
        assertEquals(null,t1.getTaskDate());
    }
}