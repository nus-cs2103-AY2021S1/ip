package duke.task;

import duke.exception.DateException;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() throws ParseException {
        String expected = String.format("[D] [\u2718] Test (by: 11/11/2020 1200)");
        Date date = new SimpleDateFormat("d/M/yy HHmm").parse("11/11/20 1200");
        assertEquals(expected, new Deadline("Test", false, date).toString());
    }

    @Test
    public void getDate() throws ParseException {
        Date date = new SimpleDateFormat("d/M/yy HHmm").parse("11/11/20 1200");
        Deadline deadline = new Deadline("Test", false, date);
        assertEquals(date, deadline.getDate());
    }
}
