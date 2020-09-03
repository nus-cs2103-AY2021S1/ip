package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final Deadline DEADLINE_ONE = new Deadline("task 1",
            LocalDateTime.of(1993, 12, 6, 10, 10));
    private static final Deadline DEADLINE_TWO = new Deadline(
            "task 2", "4198-01-13T23:39", true);

    @Nested
    @DisplayName("write")
    class Write {
        @Test
        @DisplayName("should return a string meant for writing to disk")
        public void write_deadlineTask_string() {
            assertEquals("D,1993-12-06T10:10,0,task 1\n",
                    DEADLINE_ONE.write());
        }

        @Test
        @DisplayName("should return a string meant for writing to disk with alt deadline")
        public void write_altDeadlineTask_altString() {
            assertEquals("D,4198-01-13T23:39,1,task 2\n",
                    DEADLINE_TWO.write());
        }
    }

    @Nested
    @DisplayName("string representation")
    class StringRepresentation {
        @Test
        @DisplayName("should return a string representation of the deadline instance")
        public void toString_deadlineTask_string() {
            assertEquals("[D][\u2718] task 1 (by: Monday, 06 Dec 1993, 10:10AM)",
                    DEADLINE_ONE.toString());
        }

        @Test
        @DisplayName("should return a string representation of an alternate deadline instance")
        public void toString_altDeadlineTask_altString() {
            assertEquals("[D][\u2713] task 2 (by: Saturday, 13 Jan 4198, 11:39PM)",
                    DEADLINE_TWO.toString());
        }
    }

    @Nested
    @DisplayName("equals")
    class Equal {
        @Test
        @DisplayName("should return true for a deadline with the same name, deadline and done state")
        public void equals_deadline_true() {
            assertTrue(DEADLINE_ONE.equals(
                    new Deadline("task 1", "1993-12-06T10:10", false)));
        }

        @Test
        @DisplayName("should return true for an alt deadline with the same "
                + "name, deadline and done state")
        public void equals_altDeadline_true() {
            assertTrue(DEADLINE_TWO.equals(
                    new Deadline("task 2", "4198-01-13T23:39", true)));
        }

        @Test
        @DisplayName("should return false for a non deadline tasks")
        public void equals_nonDeadline_false() {
            assertFalse(DEADLINE_ONE.equals(new Task("task 1")));
            assertFalse(DEADLINE_TWO.equals(new Todo("task 2")));
            assertFalse(DEADLINE_TWO.equals(
                    new Event("task 2", "4198-01-13T23:39", true)));
        }

        @Test
        @DisplayName("should return false for an deadline with different name")
        public void equals_deadline_false() {
            assertFalse(DEADLINE_ONE.equals(
                    new Deadline("different name", "1993-12-06T10:10", false)));
        }

        @Test
        @DisplayName("should return false for an deadline with different done state")
        public void equals_altDeadline_false() {
            assertFalse(DEADLINE_ONE.equals(
                    new Deadline("task 1", "1993-12-06T10:10", true)));
        }

        @Test
        @DisplayName("should return false for an deadline with different date")
        public void equals_altDeadlineTwo_false() {
            assertFalse(DEADLINE_ONE.equals(
                    new Deadline("task 1", "2020-12-06T10:10", false)));
        }
    }
}
