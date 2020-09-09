package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.command.Option;
import main.exception.InvalidOptionException;

public class EventTest {
    private static final LocalDateTime NOW = LocalDateTime.now();
    private static Event eventOne;
    private static Event eventTwo;

    @BeforeEach
    public void beforeEach() throws InvalidOptionException {
        eventOne = new Event("task 1",
                LocalDateTime.of(1993, 12, 6, 10, 10),
                new HashSet<>(),
                new String[0]
        );
        eventTwo = new Event(
                "task 2", "", "4198-01-13T23:39", true, new String[0]);
    }

    @Nested
    @DisplayName("recurrence")
    class Recurrence {
        @Test
        @DisplayName("should reset done state and push back event to one day away")
        public void constructor_recurringDaily_event() throws InvalidOptionException {
            Event test = new Event("task 2", "rd",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_DAILY);

            assertEquals(new Event(
                    "task 2",
                    date.plusDays(date.until(NOW, ChronoUnit.DAYS) + 1),
                    options,
                    new String[0]
            ), test);
        }

        @Test
        @DisplayName("should reset done state and push back event to one week away")
        public void constructor_recurringWeekly_event() throws InvalidOptionException {
            Event test = new Event("task 2", "rw",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_WEEKLY);

            assertEquals(new Event(
                    "task 2",
                    date.plusWeeks(date.until(NOW, ChronoUnit.WEEKS) + 1),
                    options,
                    new String[0]
            ), test);
        }

        @Test
        @DisplayName("should reset done state and push back event to one month away")
        public void constructor_recurringMonthly_event() throws InvalidOptionException {
            Event test = new Event("task 2", "rm",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_MONTHLY);

            assertEquals(new Event(
                    "task 2",
                    date.plusMonths(date.until(NOW, ChronoUnit.MONTHS) + 1),
                    options,
                    new String[0]
            ), test);
        }

        @Test
        @DisplayName("should reset done state and push back event to one year away")
        public void constructor_recurringYearly_event() throws InvalidOptionException {
            Event test = new Event("task 2", "ry",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_YEARLY);

            assertEquals(new Event(
                    "task 2",
                    date.plusYears(date.until(NOW, ChronoUnit.YEARS) + 1),
                    options,
                    new String[0]
            ), test);
        }
    }

    @Nested
    @DisplayName("write")
    class Write {
        @Test
        @DisplayName("should return a string meant for writing to disk")
        public void write_eventTask_string() {
            assertEquals("E,,1993-12-06T10:10,0,,task 1\n",
                    eventOne.write());
        }

        @Test
        @DisplayName("should return a string meant for writing to disk with alt event")
        public void write_altEventTask_altString() {
            assertEquals("E,,4198-01-13T23:39,1,,task 2\n",
                    eventTwo.write());
        }

        @Test
        @DisplayName("should return a string with the recurrence alias")
        public void write_recurringEventTask_string() throws InvalidOptionException {
            Event test = new Event(
                    "task 3",
                    "rw",
                    "4000-11-23T12:44",
                    true,
                    new String[0]
            );

            assertEquals("E,rw,4000-11-23T12:44,1,,task 3\n",
                    test.write());
        }

        @Test
        @DisplayName("should return a string with tags")
        public void write_taggedEventTask_string() throws InvalidOptionException {
            Event test = new Event(
                    "task 3",
                    "rw",
                    "4000-11-23T12:44",
                    true,
                    new String[] { "happy" }
            );

            assertEquals("E,rw,4000-11-23T12:44,1,happy,task 3\n",
                    test.write());
        }

        @Test
        @DisplayName("should return an alternate string with tags")
        public void write_taggedAltEventTask_string() throws InvalidOptionException {
            Event test = new Event(
                    "task 3",
                    "rw",
                    "4000-11-23T12:44",
                    true,
                    new String[] { "abc", "123", "apple" }
            );

            assertEquals("E,rw,4000-11-23T12:44,1,abc;123;apple,task 3\n",
                    test.write());
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
                    new Event(
                            "task 1",
                            "",
                            "1993-12-06T10:10",
                            false,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return true for an alt event with the same "
                + "name, event time and done state")
        public void equals_altEvent_true() throws InvalidOptionException {
            assertTrue(eventTwo.equals(
                    new Event(
                            "task 2",
                            "",
                            "4198-01-13T23:39",
                            true,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for a non event tasks")
        public void equals_nonEvent_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(new Task("task 1", new String[0])));
            assertFalse(eventTwo.equals(new Todo("task 2", new String[0])));
            assertFalse(eventTwo.equals(
                    new Deadline(
                            "task 2",
                            "",
                            "4198-01-13T23:39",
                            true,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for an event with different name")
        public void equals_event_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(
                    new Event(
                            "different name",
                            "",
                            "1993-12-06T10:10",
                            false,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for an event with different done state")
        public void equals_altEvent_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(
                    new Event(
                            "task 1",
                            "",
                            "1993-12-06T10:10",
                            true,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for an event with different date")
        public void equals_altEventTwo_false() throws InvalidOptionException {
            assertFalse(eventOne.equals(
                    new Event(
                            "task 1",
                            "",
                            "2020-12-06T10:10",
                            false,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false if events have different recurrence")
        public void equals_altRecurrenceEvent_false() throws InvalidOptionException {
            Event recurringDaily = new Event("task 1", "rd",
                    "1993-12-06T10:10", false, new String[0]);
            Event recurringWeekly = new Event("task 1", "rw",
                    "1993-12-06T10:10", false, new String[0]);
            Event recurringMonthly = new Event("task 1", "rm",
                    "1993-12-06T10:10", false, new String[0]);
            Event recurringYearly = new Event("task 1", "ry",
                    "1993-12-06T10:10", false, new String[0]);

            assertFalse(eventOne.equals(recurringMonthly));
            assertFalse(recurringDaily.equals(recurringMonthly));
            assertFalse(recurringMonthly.equals(recurringYearly));
            assertFalse(recurringYearly.equals(recurringDaily));
        }

        @Test
        @DisplayName("should return false if tags are different")
        public void equals_differentTagDeadline_false() {
            Event test = new Event(
                    "task 1",
                    LocalDateTime.of(1993, 12, 6, 10, 10),
                    new HashSet<>(),
                    new String[] { "abc" }
            );

            assertFalse(eventOne.equals(test));
        }
    }
}
