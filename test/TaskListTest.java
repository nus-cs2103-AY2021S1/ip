import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @org.junit.jupiter.api.Test
    void createTask_todo_success() {
        try {
            assertEquals(new Todo("borrow book"),
                    TaskList.createTask("todo", "borrow book"));
        } catch (DukeException e) {
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void createTask_event_success() {
        try {
            assertEquals(new Event("book borrowing", "2020-08-26 00:00"),
                    TaskList.createTask("event", "book borrowing /at 2020-08-26 00:00"));
        } catch (DukeException e) {
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void createTask_eventMissingAt_exceptionThrown() {
        try {
            assertEquals(new Event("book borrowing", "2020-08-26 00:00"),
                    TaskList.createTask("event", "book borrowing at 2020-08-26 00:00"));
            fail("Should have thrown exception.");
        } catch (DukeException e) {
            assertEquals("!!Whoops!! Invalid event description!", e.getMessage());
        }
    }
}