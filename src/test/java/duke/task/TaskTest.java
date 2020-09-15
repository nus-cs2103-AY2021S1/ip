package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void getStatusIcon_getStatusIconForNewTask_statusIconIsACross() {
        Task testTask = new Task("This is a test");
        assertEquals(Task.STATUS_CROSS, testTask.getStatusIcon());
    }

    @Test
    void markAsDone_markNewTaskAsDone_statusIconIsATick() {
        Task testTask = new Task("This is a test");
        testTask.markAsDone();
        assertEquals(Task.STATUS_TICK, testTask.getStatusIcon());
    }

    @Test
    void markAsDone_markAlreadyDoneTaskAsDone_statusIconIsATick() {
        Task testTask = new Task("This is a test");
        testTask.markAsDone();
        testTask.markAsDone();
        assertEquals(Task.STATUS_TICK, testTask.getStatusIcon());
    }

    @Test
    void hasDoneStatus_checkDoneStatusWhenTaskIsMarkedDone_returnsTrue() {
        Task testTask = new Task("This is a test");
        testTask.markAsDone();
        assertEquals(true, testTask.hasDoneStatus());
    }

    @Test
    void hasDoneStatus_checkDoneStatusWhenTaskIsNotMarkedDone_returnsFalse() {
        Task testTask = new Task("This is a test");
        assertEquals(false, testTask.hasDoneStatus());
    }
}
