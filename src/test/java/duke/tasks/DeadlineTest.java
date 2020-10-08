package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.utils.DukeDateTime;

public class DeadlineTest {

    @Test
    public void testTime() {
        DukeDateTime by = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Deadline deadline = new Deadline(description, by);
        assertEquals(by, deadline.getTime());
    }

    @Test
    public void testToFileString() {
        DukeDateTime by = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Deadline deadline = new Deadline(description, by);
        String expected = "D | 0 | " + description + " | 23 Aug 2020 1:00 PM";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void testToString() {
        DukeDateTime by = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Deadline deadline = new Deadline(description, by);
        String expected = "[D][\u2718] " + description + " (by: 23 Aug 2020 1:00 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toFileString_markedAsDone() {
        DukeDateTime by = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Deadline deadline = new Deadline(description, by);
        deadline.markAsDone();
        String expected = "D | 1 | " + description + " | 23 Aug 2020 1:00 PM";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void toString_markedAsDone() {
        DukeDateTime by = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String description = "description";
        Deadline deadline = new Deadline(description, by);
        deadline.markAsDone();
        String expected = "[D][\u2713] " + description + " (by: 23 Aug 2020 1:00 PM)";
        assertEquals(expected, deadline.toString());
    }

}
