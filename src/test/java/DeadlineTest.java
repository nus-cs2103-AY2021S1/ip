import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    void getTaskTest() throws DukeException {

        LocalDate date = LocalDate.of(2030, 8, 27);
        Deadline deadline = new Deadline("Dummy Task", date);

        assertEquals("Dummy Task", deadline.getTask());
    }

    @Test
    void toStringTest() throws DukeException {

        LocalDate date = LocalDate.of(2030, 8, 7);
        Deadline deadline = new Deadline("Dummy Task", date);

        assertEquals("[D][\u2717] Dummy Task (by: 07 Aug 2030)" , deadline.toString());
    }

    @Test
    void toStringDoneTest() throws DukeException {

        LocalDate date = LocalDate.of(1990, 1, 14);
        Deadline deadline = new Deadline("Birthday", date);
        deadline.markDone();

        assertEquals("[D][\u2713] Birthday (by: 14 Jan 1990)" , deadline.toString());
    }



}
