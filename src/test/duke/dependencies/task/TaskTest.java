package duke.dependencies.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {

    @Test
    void getDateLine() {
    }

    @Test
    void hasADate() {
    }

    @Test
    void showTask() {
    }

    @Test
    void isItEmpty() {

    }

    @Test
    void completed() {
    }

    @Test
    void createMiscTask() {
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