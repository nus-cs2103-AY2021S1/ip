package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidOptionException;

public class TodoTest {
    private static final Todo TODO_ONE = new Todo("task 1", new String[0]);
    private static final Todo TODO_TWO = new Todo("task 2", true, new String[0]);

    @Nested
    @DisplayName("write")
    class Write {
        @Test
        @DisplayName("should return a string meant for writing to disk")
        public void write_todoTask_string() {
            assertEquals("T,,,0,,task 1\n", TODO_ONE.write());
        }

        @Test
        @DisplayName("should return a string meant for writing to disk with alt todo")
        public void write_altTodoTask_altString() {
            assertEquals("T,,,1,,task 2\n", TODO_TWO.write());
        }

        @Test
        @DisplayName("should return a string with tags")
        public void write_taggedTodoTask_string() {
            Todo test = new Todo(
                    "task 3",
                    true,
                    new String[] { "abc", "123", "apple" }
            );

            assertEquals("T,,,1,abc;123;apple,task 3\n",
                    test.write());
        }

        @Test
        @DisplayName("should return an alternate string with tags")
        public void write_taggedAltTodoTask_string() {
            Todo test = new Todo(
                    "task 3",
                    true,
                    new String[] { "happy" }
            );

            assertEquals("T,,,1,happy,task 3\n",
                    test.write());
        }
    }

    @Nested
    @DisplayName("string representation")
    class StringRepresentation {
        @Test
        @DisplayName("should return a string representation of the todo instance")
        public void toString_todoTask_string() {
            assertEquals("[T][\u2718] task 1", TODO_ONE.toString());
        }

        @Test
        @DisplayName("should return a string representation of an alternate todo instance")
        public void toString_altTodoTask_altString() {
            assertEquals("[T][\u2713] task 2", TODO_TWO.toString());
        }
    }

    @Nested
    @DisplayName("equals")
    class Equal {
        @Test
        @DisplayName("should return true for a todo with the same name")
        public void equals_todo_true() {
            assertTrue(TODO_ONE.equals(new Todo("task 1", new String[0])));
        }

        @Test
        @DisplayName("should return true for an alt todo with the same name")
        public void equals_altTodo_true() {
            assertTrue(TODO_TWO.equals(new Todo("task 2", true, new String[0])));
        }

        @Test
        @DisplayName("should return false for a non todo tasks")
        public void equals_nonTodo_false() throws InvalidOptionException {
            assertFalse(TODO_ONE.equals(new Task("task 1", new String[0])));
            assertFalse(TODO_TWO.equals(
                    new Event(
                            "task 2",
                            "",
                            "4198-01-13T23:39",
                            true,
                            new String[0]
                    )));
            assertFalse(TODO_TWO.equals(
                    new Deadline(
                            "task 2",
                            "",
                            "4198-01-13T23:39",
                            true,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for a todo with different name")
        public void equals_todo_false() {
            assertFalse(TODO_ONE.equals(new Todo("task 2", new String[0])));
        }

        @Test
        @DisplayName("should return false for a todo with different done state")
        public void equals_altTodo_false() {
            assertFalse(TODO_ONE.equals(
                    new Todo("task 1", true, new String[0])));
        }

        @Test
        @DisplayName("should return false if tags are different")
        public void equals_differentTagDeadline_false() {
            Todo test = new Todo("task 1", new String[] { "abc" });

            assertFalse(TODO_ONE.equals(test));
        }
    }
}
