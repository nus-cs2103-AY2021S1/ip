package duke.tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.DukeException;


public class DeadlineTest {
    @Test
    public void isSameDate_trueOrFalse() throws DukeException {
        Deadline deadline = new Deadline("hi", "2020-10-19");
        LocalDate d1;
        d1 = LocalDate.parse("2020-10-19");
        assertEquals(deadline.isSameDate(d1), true);
    }
}
