package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class ActualEventTest {
    @Test
    public void constructor_invalidInput_throwException() {
        try {
            ActualEvent e = new ActualEvent("Project meeting", "2020-09-03 11");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid input datetime, please input as yyyy-MM-dd HH:mm.", e.getMessage());
        }
    }

    @Test
    public void happenOnDateTest() {
        try {
            ActualEvent e = new ActualEvent("Project meeting", "2020-09-03 11:30");
            assertTrue(e.happenOnDate(LocalDate.parse("2020-09-03")));
            assertFalse(e.happenOnDate(LocalDate.parse("2020-09-04")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenTodayTest() {
        try {
            LocalDateTime today = LocalDateTime.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ActualEvent e1 = new ActualEvent("Project meeting", td);
            assertTrue(e1.happenToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ActualEvent e2 = new ActualEvent("Project meeting", newStr);
            assertFalse(e2.happenToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBeforeDateTest() {
        try {
            ActualEvent e = new ActualEvent("Project meeting", "2020-09-03 11:30");
            assertTrue(e.happenBeforeDate(LocalDate.parse("2020-09-05")));
            assertFalse(e.happenBeforeDate(LocalDate.parse("2020-09-01")));
            assertFalse(e.happenBeforeDate(LocalDate.parse("2020-09-03")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBeforeTodayTest() {
        try {
            LocalDateTime today = LocalDateTime.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ActualEvent e1 = new ActualEvent("Project meeting", td);
            assertFalse(e1.happenBeforeToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ActualEvent e2 = new ActualEvent("Project meeting", newStr);
            assertFalse(e2.happenBeforeToday());

            ActualEvent e3 = new ActualEvent("Project meeting", "2020-08-01 11:30");
            assertTrue(e3.happenBeforeToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenAfterDateTest() {
        try {
            ActualEvent e = new ActualEvent("Project meeting", "2020-09-03 11:30");
            assertFalse(e.happenAfterDate(LocalDate.parse("2020-09-05")));
            assertTrue(e.happenAfterDate(LocalDate.parse("2020-09-01")));
            assertFalse(e.happenAfterDate(LocalDate.parse("2020-09-03")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenAfterTodayTest() {
        try {
            LocalDateTime today = LocalDateTime.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ActualEvent e1 = new ActualEvent("Project meeting", td);
            assertFalse(e1.happenAfterToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ActualEvent e2 = new ActualEvent("Project meeting", newStr);
            assertTrue(e2.happenAfterToday());

            ActualEvent e3 = new ActualEvent("Project meeting", "2020-08-01 11:30");
            assertFalse(e3.happenAfterToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBetweenTest() {
        try {
            ActualEvent e = new ActualEvent("Project meeting", "2020-09-03 11:30");
            assertFalse(e.happenBetween(LocalDate.parse("2020-08-01"), LocalDate.parse("2020-09-01")));
            assertTrue(e.happenBetween(LocalDate.parse("2020-09-01"), LocalDate.parse("2020-09-04")));
            assertTrue(e.happenBetween(LocalDate.parse("2020-09-03"), LocalDate.parse("2020-09-03")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenInTest() {
        try {
            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ActualEvent e = new ActualEvent("Project meeting", newStr);
            assertTrue(e.happenIn(3));
            assertTrue(e.happenIn(2));
            assertFalse(e.happenIn(1));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void outputTest() {
        try {
            ActualEvent e = new ActualEvent("Project meeting", "2020-09-03 11:30");
            assertEquals("E | 0 | Project meeting | At: 2020-09-03 11:30\n", e.output());

            e.markAsDone();
            assertEquals("E | 1 | Project meeting | At: 2020-09-03 11:30\n", e.output());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        try {
            ActualEvent e = new ActualEvent("Project meeting", "2020-09-03 11:30");
            assertEquals("[E][\u2718] Project meeting (at: 11:30 AM   Sep 3 2020)", e.toString());

            e.markAsDone();
            assertEquals("[E][\u2713] Project meeting (at: 11:30 AM   Sep 3 2020)", e.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
