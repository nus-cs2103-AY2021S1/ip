package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EventTest {
    private static final Event EVENT_ONE = new Event("task 1",
            LocalDateTime.of(1993, 12, 6, 10, 10));
    private static final Event EVENT_TWO = new Event(
            "task 2", "4198-01-13T23:39", true);

    @Nested
    @DisplayName("write")
    class Write {
        @Test
        @DisplayName("should return a string meant for writing to disk")
        public void write_eventTask_string() {
            assertEquals("E,1993-12-06T10:10,0,task 1\n",
                    EVENT_ONE.write());
        }

        @Test
        @DisplayName("should return a string meant for writing to disk with alt event")
        public void write_altEventTask_altString() {
            assertEquals("E,4198-01-13T23:39,1,task 2\n",
                    EVENT_TWO.write());
        }
    }

    @Nested
    @DisplayName("string representation")
    class StringRepresentation {
        @Test
        @DisplayName("should return a string representation of the event instance")
        public void toString_eventTask_string() {
            assertEquals("[E][\u2718] task 1 (at: Monday, 06 Dec 1993, 10:10AM)",
                    EVENT_ONE.toString());
        }

        @Test
        @DisplayName("should return a string representation of an alternate event instance")
        public void toString_altEventTask_altString() {
            assertEquals("[E][\u2713] task 2 (at: Saturday, 13 Jan 4198, 11:39PM)",
                    EVENT_TWO.toString());
        }
    }

    @Nested
    @DisplayName("equals")
    class Equal {
        @Test
        @DisplayName("should return true for a event with the same name, event time and done state")
        public void equals_event_true() {
            assertTrue(EVENT_ONE.equals(
                    new Event("task 1", "1993-12-06T10:10", false)));
        }

        @Test
        @DisplayName("should return true for an alt event with the same "
                + "name, event time and done state")
        public void equals_altEvent_true() {
            assertTrue(EVENT_TWO.equals(
                    new Event("task 2", "4198-01-13T23:39", true)));
        }

        @Test
        @DisplayName("should return false for a non event tasks")
        public void equals_nonEvent_false() {
            assertFalse(EVENT_ONE.equals(new Task("task 1")));
            assertFalse(EVENT_TWO.equals(new Todo("task 2")));
            assertFalse(EVENT_TWO.equals(
                    new Deadline("task 2", "4198-01-13T23:39", true)));
        }

        @Test
        @DisplayName("should return false for an event with different name")
        public void equals_event_false() {
            assertFalse(EVENT_ONE.equals(
                    new Event("different name", "1993-12-06T10:10", false)));
        }

        @Test
        @DisplayName("should return false for an event with different done state")
        public void equals_altEvent_false() {
            assertFalse(EVENT_ONE.equals(
                    new Event("task 1", "1993-12-06T10:10", true)));
        }

        @Test
        @DisplayName("should return false for an event with different date")
        public void equals_altEventTwo_false() {
            assertFalse(EVENT_ONE.equals(
                    new Event("task 1", "2020-12-06T10:10", false)));
        }
    }
}
