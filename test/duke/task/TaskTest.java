package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testGetStatusIcon() {
        Task testTask = new Task("This is a test");
        assertEquals(Task.STATUS_CROSS, testTask.getStatusIcon());
    }

    @Test
    void testMarkAsDone() {
        Task testTask = new Task("This is a test");
        testTask.markAsDone();
        assertEquals(Task.STATUS_TICK, testTask.getStatusIcon());
    }

    @Test
    void testHasDoneStatus() {
        Task testTask = new Task("This is a test");
        testTask.markAsDone();
        assertEquals(true, testTask.hasDoneStatus());
    }
}