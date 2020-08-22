package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static LocalDateTime getLocalDate() {
        return LocalDateTime.of(2020, 12, 12, 6, 0);
    }

    private static final Deadline DEADLINE_ONE = new Deadline("test", false, getLocalDate());
    private static final Deadline DEADLINE_TWO = new Deadline("test 2", true, getLocalDate());

    @Test
    public void testCreateDeadline() {
        assertEquals(Deadline.createDeadline("test", getLocalDate()), DEADLINE_ONE);
    }

    @Test
    public void testToString() {
        assertEquals(DEADLINE_ONE.toString(), "[D][\u2717] test (by: Dec 12 2020 06:00 am)");
        assertEquals(DEADLINE_TWO.toString(), "[D][\u2713] test 2 (by: Dec 12 2020 06:00 am)");
    }

    @Test
    public void testGetTaskDatetime() {
        assertEquals(DEADLINE_ONE.getTaskDatetime(), Optional.of("Dec 12 2020 06:00 am"));
    }
}
