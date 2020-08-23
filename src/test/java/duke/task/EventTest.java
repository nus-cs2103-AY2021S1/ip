package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void constructor_invalidInput_throwException() {
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
            assertTrue(e.isHappeningOn(LocalDate.parse("2020-09-03")));
            assertFalse(e.isHappeningOn(LocalDate.parse("2020-09-04")));
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
            assertTrue(e1.isHappeningToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e2 = new Event("Project meeting", newStr);
            assertFalse(e2.isHappeningToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBeforeDateTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertTrue(e.hasHappenedBefore(LocalDate.parse("2020-09-05")));
            assertFalse(e.hasHappenedBefore(LocalDate.parse("2020-09-01")));
            assertFalse(e.hasHappenedBefore(LocalDate.parse("2020-09-03")));
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
            assertFalse(e1.hasHappenedBeforeToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e2 = new Event("Project meeting", newStr);
            assertFalse(e2.hasHappenedBeforeToday());

            Event e3 = new Event("Project meeting", "2020-08-01 11:30");
            assertTrue(e3.hasHappenedBeforeToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenAfterDateTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertFalse(e.isHappeningAfter(LocalDate.parse("2020-09-05")));
            assertTrue(e.isHappeningAfter(LocalDate.parse("2020-09-01")));
            assertFalse(e.isHappeningAfter(LocalDate.parse("2020-09-03")));
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
            assertFalse(e1.isHappeningAfterToday());

            LocalDateTime newDate = LocalDateTime.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Event e2 = new Event("Project meeting", newStr);
            assertTrue(e2.isHappeningAfterToday());

            Event e3 = new Event("Project meeting", "2020-08-01 11:30");
            assertFalse(e3.isHappeningAfterToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBetweenTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertFalse(e.isHappeningBetween(LocalDate.parse("2020-08-01"), LocalDate.parse("2020-09-01")));
            assertTrue(e.isHappeningBetween(LocalDate.parse("2020-09-01"), LocalDate.parse("2020-09-04")));
            assertTrue(e.isHappeningBetween(LocalDate.parse("2020-09-03"), LocalDate.parse("2020-09-03")));
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
            assertTrue(e.willHappenInDays(3));
            assertTrue(e.willHappenInDays(2));
            assertFalse(e.willHappenInDays(1));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void outputTest() {
        try {
            Event e = new Event("Project meeting", "2020-09-03 11:30");
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
            Event e = new Event("Project meeting", "2020-09-03 11:30");
            assertEquals("[E][\u2718] Project meeting (at: 11:30 AM   Sep 3 2020)", e.toString());

            e.markAsDone();
            assertEquals("[E][\u2713] Project meeting (at: 11:30 AM   Sep 3 2020)", e.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
