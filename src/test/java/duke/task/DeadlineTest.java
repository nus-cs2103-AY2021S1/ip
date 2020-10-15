package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final Deadline DEADLINE_ONE = new Deadline("test", false, getLocalDate());
    private static final Deadline DEADLINE_TWO = new Deadline("test 2", true, getLocalDate());

    private static LocalDateTime getLocalDate() {
        return LocalDateTime.of(2020, 12, 12, 6, 0);
    }

    @Test
    public void testCreateDeadline() {
        assertEquals(Deadline.createDeadline("test", getLocalDate()), DEADLINE_ONE);
    }

    @Test
    public void testToString() {
        assertEquals(DEADLINE_ONE.toString(), "[D][\u2717] test (by: Dec 12 2020 06:00)");
        assertEquals(DEADLINE_TWO.toString(), "[D][\u2713] test 2 (by: Dec 12 2020 06:00)");
    }

    @Test
    public void testGetTaskDatetime() {
        assertEquals(DEADLINE_ONE.getTaskDatetime(), Optional.of("Dec 12 2020 06:00"));
    }
}
