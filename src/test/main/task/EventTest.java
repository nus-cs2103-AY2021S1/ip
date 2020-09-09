package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidOptionException;

public class EventTest {
    private static Event eventOne;
    private static Event eventTwo;

    @BeforeEach
    public void beforeEach() throws InvalidOptionException {
        eventOne = new Event("task 1",
                LocalDateTime.of(1993, 12, 6, 10, 10), new HashSet<>());
        eventTwo = new Event(
                "task 2", "", "4198-01-13T23:39", true);
    }

    @Nested
    @DisplayName("write")
    class Write {
        @Test
        @DisplayName("should return a string meant for writing to disk")
        public void write_eventTask_string() {
            assertEquals("E,,1993-12-06T10:10,0,task 1\n",
                    eventOne.write());
        }

        @Test
        @DisplayName("should return a string meant for writing to disk with alt event")
        public void write_altEventTask_altString() {
            assertEquals("E,,4198-01-13T23:39,1,task 2\n",
                    eventTwo.write());
        }
    }

    @Nested
    @DisplayName("string representation")
    class StringRepresentation {
        @Test
        @DisplayName("should return a string representation of the event instance")
        public void toString_eventTask_string() {
            assertEquals("[E][\u2718] task 1\n(at: Monday, 06 Dec 1993, 10:10AM)",
                    eventOne.toString());
        }

        @Test
        @DisplayName("should return a string representation of an alternate event instance")
        public void toString_altEventTask_altString() {
            assertEquals("[E][\u2713] task 2\n(at: Saturday, 13 Jan 4198, 11:39PM)",
                    eventTwo.toString());
        }
    }

    @Nested
    @DisplayName("equals")
    class Equal {
        @Test
        @DisplayName("should return true for a event with the same name, event time and done state")
        public void equals_event_true() throws InvalidOptionException {
            assertTrue(eventOne.equals(
                    new Event("task 1", "", "1993-12-06T10:10", false)));
        }

        @Test
        @DisplayName("should return true for an alt event with the same "
                + "name, event time and done state")
        public void equals_altEvent_true() throws InvalidOptionException {
            assertTrue(eventTwo.equals(
                    new Event("task 2", "", "4198-01-13T23:39", true)));
        }

        @Test
        @DisplayName("should return false for a non event tasks")
        public void equals_nonEvent_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(new Task("task 1")));
            assertFalse(eventTwo.equals(new Todo("task 2")));
            assertFalse(eventTwo.equals(
                    new Deadline("task 2", "", "4198-01-13T23:39", true)));
        }

        @Test
        @DisplayName("should return false for an event with different name")
        public void equals_event_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(
                    new Event("different name", "", "1993-12-06T10:10", false)));
        }

        @Test
        @DisplayName("should return false for an event with different done state")
        public void equals_altEvent_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(
                    new Event("task 1", "", "1993-12-06T10:10", true)));
        }

        @Test
        @DisplayName("should return false for an event with different date")
        public void equals_altEventTwo_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(
                    new Event("task 1", "", "2020-12-06T10:10", false)));
        }
    }
}
