package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void newEventTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid input datetime, please input as yyyy-MM-dd HH:mm.", e.getMessage());
        }
    }

    @Test
    public void happenOnDateTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertEquals(true, e.happenOnDate(LocalDate.parse("2020-09-03")));
            assertEquals(false, e.happenOnDate(LocalDate.parse("2020-09-04")));
        } catch (Exception e) {
            fail();
        }
    }
}
