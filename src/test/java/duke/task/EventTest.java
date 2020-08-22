package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            LocalDateTime today = LocalDateTime.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e1 = new Event("Project meeting", td);
            assertEquals(true, e1.happenToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e2 = new Event("Project meeting", newStr);
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
            LocalDateTime today = LocalDateTime.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e1 = new Event("Project meeting", td);
            assertEquals(false, e1.happenBeforeToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e2 = new Event("Project meeting", newStr);
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

    @Test
    public void happenAfterTodayTest() {
        try {
            LocalDateTime today = LocalDateTime.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e1 = new Event("Project meeting", td);
            assertEquals(false, e1.happenAfterToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e2 = new Event("Project meeting", newStr);
            assertEquals(true, e2.happenAfterToday());

            Event e3 = new Event("Project meeting", "2020-08-01 11:30");
            assertEquals(false, e3.happenAfterToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBetweenTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertEquals(false, e.happenBetween(LocalDate.parse("2020-08-01"), LocalDate.parse("2020-09-01")));
            assertEquals(true, e.happenBetween(LocalDate.parse("2020-09-01"), LocalDate.parse("2020-09-04")));
            assertEquals(true, e.happenBetween(LocalDate.parse("2020-09-03"), LocalDate.parse("2020-09-03")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenInTest() {
        try {
            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e = new Event("Project meeting", newStr);
            assertEquals(true, e.happenIn(3));
            assertEquals(true, e.happenIn(2));
            assertEquals(false, e.happenIn(1));
        } catch (Exception e) {
            fail();
        }
    }
}
