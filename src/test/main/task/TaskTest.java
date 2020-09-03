package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private static final Task TASK_ONE = new Task("task 1");
    private static final Task TASK_TWO = new Task("task 2", true);

    @Nested
    @DisplayName("write")
    class Write {
        @Test
        @DisplayName("should return a string meant for writing to disk")
        public void write_task_string() {
            assertEquals(",0,task 1\n", TASK_ONE.write());
        }

        @Test
        @DisplayName("should return a string meant for writing to disk with alt task")
        public void write_altTask_altString() {
            assertEquals(",1,task 2\n", TASK_TWO.write());
        }
    }

    @Nested
    @DisplayName("string representation")
    class StringRepresentation {
        @Test
        @DisplayName("should return a string representation of the task instance")
        public void toString_task_string() {
            assertEquals("[\u2718] task 1", TASK_ONE.toString());
        }

        @Test
        @DisplayName("should return a string representation of an alternate task instance")
        public void toString_altTask_altString() {
            assertEquals("[\u2713] task 2", TASK_TWO.toString());
        }
    }

    @Nested
    @DisplayName("equals")
    class Equal {
        @Test
        @DisplayName("should return true for a task with the same name")
        public void equals_task_true() {
            assertTrue(TASK_ONE.equals(new Task("task 1")));
        }

        @Test
        @DisplayName("should return true for an alt task with the same name")
        public void equals_altTask_true() {
            assertTrue(TASK_TWO.equals(new Task("task 2", true)));
        }

        @Test
        @DisplayName("should return false for a task with different name")
        public void equals_task_false() {
            assertFalse(TASK_ONE.equals(new Task("different")));
        }

        @Test
        @DisplayName("should return false for a task with different done state")
        public void equals_altTask_false() {
            assertFalse(TASK_ONE.equals(new Task("task 1", true)));
        }
    }
}
