package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testGetStatusIcon() {
        Task testTask = new Task("This is a test");
        assertEquals(Task.CROSS, testTask.getStatusIcon());
    }

    @Test
    void testMarkAsDone() {
        Task testTask = new Task("This is a test");
        testTask.markAsDone();
        assertEquals(Task.TICK, testTask.getStatusIcon());
    }

    @Test
    void testHasDoneStatus() {
        Task testTask = new Task("This is a test");
        testTask.markAsDone();
        assertEquals(true, testTask.hasDoneStatus());
    }
}