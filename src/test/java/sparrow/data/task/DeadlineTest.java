package sparrow.data.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline deadline = new Deadline("I am a deadline.", LocalDate.now());

    @Test
    public void equals() {
        Deadline sameDeadline = new Deadline("I am a deadline.", LocalDate.now());
        Deadline diffDescription = new Deadline("I am another deadline.", LocalDate.now());
        Deadline diffDate = new Deadline("I am a deadline.", LocalDate.EPOCH);

        // same details -> returns true
        assertTrue(deadline.equals(sameDeadline));

        // different description -> returns false
        assertFalse(deadline.equals(diffDescription));

        // different date -> returns false
        assertFalse(deadline.equals(diffDate));

        // null -> returns false
        assertFalse(deadline.equals(null));

        // different type -> returns false
        assertFalse(deadline.equals(27));
    }
}
