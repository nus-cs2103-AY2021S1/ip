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

    @Test
    public void happenTodayTest() {
        try {
            Event e1 = new Event("Project meeting", "2020-08-22 11:30");
            assertEquals(true, e1.happenToday());

            Event e2 = new Event("Project meeting", "2020-08-24 11:30");
            assertEquals(false, e2.happenToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBeforeDateTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertEquals(true, e.happenBeforeDate(LocalDate.parse("2020-09-05")));
            assertEquals(false, e.happenBeforeDate(LocalDate.parse("2020-09-01")));
            assertEquals(false, e.happenBeforeDate(LocalDate.parse("2020-09-03")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBeforeTodayTest() {
        try {
            Event e1 = new Event("Project meeting", "2020-08-22 11:30");
            assertEquals(false, e1.happenBeforeToday());

            Event e2 = new Event("Project meeting", "2020-08-24 11:30");
            assertEquals(false, e2.happenBeforeToday());

            Event e3 = new Event("Project meeting", "2020-08-01 11:30");
            assertEquals(true, e3.happenBeforeToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenAfterDateTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertEquals(false, e.happenAfterDate(LocalDate.parse("2020-09-05")));
            assertEquals(true, e.happenAfterDate(LocalDate.parse("2020-09-01")));
            assertEquals(false, e.happenAfterDate(LocalDate.parse("2020-09-03")));
        } catch (Exception e) {
            fail();
        }
    }
}
