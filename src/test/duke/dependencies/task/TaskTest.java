package duke.dependencies.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getDateLine() {
        //legacy method
    }

    @Test
    void showTask() {
        Task t = Task.createEvent("Meeting", "2020-08-05");
        assertEquals("Meeting", t.showTask());
    }


    @Test
    void completed() {
        Task t = Task.createTodo("Run");
        t.completed();
        assertTrue(t.isCompleted());
    }

    @Test
    void createMiscTask() {
        Task t = Task.createMiscTask("1");
        assertAll(() -> assertFalse(t.hasADate()),
                () -> assertEquals("1", t.showTask()),
                () -> assertNull(t.state));

    }

    @Test
    void test_createEmptyTask() {
        assertAll(() -> assertTrue(Task.createEmptyTask().isItEmpty()),
                () -> assertNull(Task.createEmptyTask().showTask()));
    }

    @Test
    void createTodo() {
    }

    @Test
    void createEvent() {
    }

    @Test
    void createDeadline() {
    }
}