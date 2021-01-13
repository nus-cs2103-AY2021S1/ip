package duke.dependencies.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getDateLine() {
        Task t = Task.createEvent("Meeting", "2020-08-05");
        assertEquals("Aug 05 2020", t.getDateString());
    }

    @Test
    void showTask() {
        Task t = Task.createEvent("Meeting", "2020-08-05");
        assertEquals("Meeting", t.showTaskDescription());
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
                () -> assertEquals("1", t.showTaskDescription()),
                () -> assertNull(t.state));

    }

    @Test
    void test_createEmptyTask() {
        assertAll(() -> assertTrue(Task.createEmptyTask().isItEmpty()),
                () -> assertNull(Task.createEmptyTask().showTaskDescription()));
    }

    @Test
    void createTodo() {
        Task t = Task.createTodo("Run");
        assertAll("Checking task",
                () -> assertFalse(t.isItEmpty()),
                () -> assertEquals("Run", t.showTaskDescription()));
    }

    @Test
    void createEvent() {
        Task t = Task.createEvent("Meeting", "08/12/2013");
        assertAll(() -> assertEquals("Meeting", t.showTaskDescription()),
                () -> assertFalse(t.isItEmpty()),
                () -> assertEquals("Dec 08 2013", t.getDateString()));
    }

    @Test
    void createDeadline() {
        Task t = Task.createDeadline("Return book", "30/08/2050");
        assertAll(() -> assertEquals("Return book", t.showTaskDescription()),
                () -> assertFalse(t.isItEmpty()),
                () -> assertEquals("Aug 30 2050", t.getDateString()));
    }
}