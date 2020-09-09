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

public class DeadlineTest {
    private static final LocalDateTime NOW = LocalDateTime.now();
    private static Deadline deadlineOne;
    private static Deadline deadlineTwo;

    @BeforeEach
    public void beforeEach() throws InvalidOptionException {
        deadlineOne = new Deadline(
                "task 1",
                LocalDateTime.of(1993, 12, 6, 10, 10),
                new HashSet<>(),
                new String[0]
        );
        deadlineTwo = new Deadline(
                "task 2", "", "4198-01-13T23:39", true, new String[0]);
    }

    @Nested
    @DisplayName("recurrence")
    class Recurrence {
        @Test
        @DisplayName("should reset done state and push back deadline to one day away")
        public void constructor_recurringDaily_deadline() throws InvalidOptionException {
            Deadline test = new Deadline("task 2", "rd",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_DAILY);

            assertEquals(new Deadline(
                    "task 2",
                    date.plusDays(date.until(NOW, ChronoUnit.DAYS) + 1),
                    options,
                    new String[0]
            ), test);
        }

        @Test
        @DisplayName("should reset done state and push back deadline to one week away")
        public void constructor_recurringWeekly_deadline() throws InvalidOptionException {
            Deadline test = new Deadline("task 2", "rw",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_WEEKLY);

            assertEquals(new Deadline(
                    "task 2",
                    date.plusWeeks(date.until(NOW, ChronoUnit.WEEKS) + 1),
                    options,
                    new String[0]
            ), test);
        }

        @Test
        @DisplayName("should reset done state and push back deadline to one month away")
        public void constructor_recurringMonthly_deadline() throws InvalidOptionException {
            Deadline test = new Deadline("task 2", "rm",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_MONTHLY);

            assertEquals(new Deadline(
                    "task 2",
                    date.plusMonths(date.until(NOW, ChronoUnit.MONTHS) + 1),
                    options,
                    new String[0]
            ), test);
        }

        @Test
        @DisplayName("should reset done state and push back deadline to one year away")
        public void constructor_recurringYearly_deadline() throws InvalidOptionException {
            Deadline test = new Deadline("task 2", "ry",
                    "2020-09-09T14:39", true, new String[0]);
            LocalDateTime date = LocalDateTime.of(2020, 9, 9, 14, 39);
            HashSet<Option> options = new HashSet<>();
            options.add(Option.RECURRING_YEARLY);

            assertEquals(new Deadline(
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
        public void write_deadlineTask_string() {
            assertEquals("D,,1993-12-06T10:10,0,,task 1\n",
                    deadlineOne.write());
        }

        @Test
        @DisplayName("should return a string meant for writing to disk with alt deadline")
        public void write_altDeadlineTask_altString() {
            assertEquals("D,,4198-01-13T23:39,1,,task 2\n",
                    deadlineTwo.write());
        }

        @Test
        @DisplayName("should return a string with the recurrence alias")
        public void write_recurringDeadlineTask_string() throws InvalidOptionException {
            Deadline test = new Deadline(
                    "task 3", "rw", "4000-11-23T12:44", true, new String[0]);

            assertEquals("D,rw,4000-11-23T12:44,1,,task 3\n",
                    test.write());
        }

        @Test
        @DisplayName("should return a string with tags")
        public void write_taggedDeadlineTask_string() throws InvalidOptionException {
            Deadline test = new Deadline(
                    "task 3",
                    "rw",
                    "4000-11-23T12:44",
                    true,
                    new String[] { "happy" }
            );

            assertEquals("D,rw,4000-11-23T12:44,1,happy,task 3\n",
                    test.write());
        }

        @Test
        @DisplayName("should return an alternate string with tags")
        public void write_taggedAltDeadlineTask_string() throws InvalidOptionException {
            Deadline test = new Deadline(
                    "task 3",
                    "rw",
                    "4000-11-23T12:44",
                    true,
                    new String[] { "abc", "123", "apple" }
                );

            assertEquals("D,rw,4000-11-23T12:44,1,abc;123;apple,task 3\n",
                    test.write());
        }
    }

    @Nested
    @DisplayName("string representation")
    class StringRepresentation {
        @Test
        @DisplayName("should return a string representation of the deadline instance")
        public void toString_deadlineTask_string() {
            assertEquals("[D][\u2718] task 1\n(by: Monday, 06 Dec 1993, 10:10AM)",
                    deadlineOne.toString());
        }

        @Test
        @DisplayName("should return a string representation of an alternate deadline instance")
        public void toString_altDeadlineTask_altString() {
            assertEquals("[D][\u2713] task 2\n(by: Saturday, 13 Jan 4198, 11:39PM)",
                    deadlineTwo.toString());
        }
    }

    @Nested
    @DisplayName("equals")
    class Equal {
        @Test
        @DisplayName("should return true for a deadline with the same name, deadline and done state")
        public void equals_deadline_true() throws InvalidOptionException {
            assertTrue(deadlineOne.equals(
                    new Deadline(
                            "task 1",
                            "",
                            "1993-12-06T10:10",
                            false,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return true for an alt deadline with the same "
                + "name, deadline and done state")
        public void equals_altDeadline_true() throws InvalidOptionException {
            assertTrue(deadlineTwo.equals(
                    new Deadline(
                            "task 2",
                            "",
                            "4198-01-13T23:39",
                            true,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for a non deadline tasks")
        public void equals_nonDeadline_false() throws InvalidOptionException {
            assertFalse(deadlineOne.equals(new Task("task 1", new String[0])));
            assertFalse(deadlineTwo.equals(new Todo("task 2", new String[0])));
            assertFalse(deadlineTwo.equals(
                    new Event(
                            "task 2",
                            "",
                            "4198-01-13T23:39",
                            true,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for an deadline with different name")
        public void equals_deadline_false() throws InvalidOptionException {
            assertFalse(deadlineOne.equals(
                    new Deadline(
                            "different name",
                            "",
                            "1993-12-06T10:10",
                            false,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for an deadline with different done state")
        public void equals_altDeadline_false() throws InvalidOptionException {
            assertFalse(deadlineOne.equals(
                    new Deadline(
                            "task 1",
                            "",
                            "1993-12-06T10:10",
                            true,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false for an deadline with different date")
        public void equals_altDeadlineTwo_false() throws InvalidOptionException {
            assertFalse(deadlineOne.equals(
                    new Deadline(
                            "task 1",
                            "",
                            "2020-12-06T10:10",
                            false,
                            new String[0]
                    )));
        }

        @Test
        @DisplayName("should return false if deadlines have different recurrence")
        public void equals_altRecurrenceDeadline_false() throws InvalidOptionException {
            Deadline recurringDaily = new Deadline("task 1", "rd",
                    "1993-12-06T10:10", false, new String[0]);
            Deadline recurringWeekly = new Deadline("task 1", "rw",
                    "1993-12-06T10:10", false, new String[0]);
            Deadline recurringMonthly = new Deadline("task 1", "rm",
                    "1993-12-06T10:10", false, new String[0]);
            Deadline recurringYearly = new Deadline("task 1", "ry",
                    "1993-12-06T10:10", false, new String[0]);

            assertFalse(deadlineOne.equals(recurringMonthly));
            assertFalse(recurringDaily.equals(recurringMonthly));
            assertFalse(recurringMonthly.equals(recurringYearly));
            assertFalse(recurringYearly.equals(recurringDaily));
        }

        @Test
        @DisplayName("should return false if tags are different")
        public void equals_differentTagDeadline_false() throws InvalidOptionException {
            Deadline test = new Deadline(
                    "task 1",
                    LocalDateTime.of(1993, 12, 6, 10, 10),
                    new HashSet<>(),
                    new String[] { "abc" }
            );

            assertFalse(deadlineOne.equals(test));
        }
    }
}
