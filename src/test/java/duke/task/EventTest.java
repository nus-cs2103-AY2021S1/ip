package duke.task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() throws ParseException {
        String expected = String.format("[E] [\u2718] Test (at: 11/11/2020 1200)");
        Date date = new SimpleDateFormat("d/M/yy HHmm").parse("11/11/20 1200");
        assertEquals(expected, new Event("Test", false, date).toString());
    }

    @Test
    public void getDate() throws ParseException {
        Date date = new SimpleDateFormat("d/M/yy HHmm").parse("11/11/20 1200");
        Event event = new Event("Test", false, date);
        assertEquals(date, event.getDate());
    }
}
