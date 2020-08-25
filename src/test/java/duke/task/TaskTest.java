package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void doneTask() {
        assertEquals("[T][✓] sleep", new Task("sleep", TaskType.TODO).doneTask().toString());
    }

    @Test
    void returnTaskType() {
        assertEquals(TaskType.TODO, new Task("sleep", TaskType.TODO).returnTaskType());
    }

    @Test
    void returnDoneStatus() {
        assertEquals(1, new Task("sleep", TaskType.TODO).doneTask().returnDoneStatus());
    }

    @Test
    void returnTaskInfo() {
        assertEquals("sleep", new Task("sleep", TaskType.TODO).doneTask().returnTaskInfo());
    }

    @Test
    void testToString() {
        assertEquals("[T][✗] sleep", new Task("sleep", TaskType.TODO).toString());
    }
}