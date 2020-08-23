package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void constructor_invalidInput_throwException() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020/09/01");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid input date, please input as yyyy-mm-dd.", e.getMessage());
        }
    }

    @Test
    public void happenOnDateTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-09-01");
            assertTrue(d.isHappeningOn(LocalDate.parse("2020-09-01")));
            assertFalse(d.isHappeningOn(LocalDate.parse("2020-09-04")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenTodayTest() {
        try {
            LocalDate today = LocalDate.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline d1 = new Deadline("Assignment 1", td);
            assertTrue(d1.isHappeningToday());

            LocalDate newDate = LocalDate.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline d2 = new Deadline("Assignment 1", newStr);
            assertFalse(d2.isHappeningToday());
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void happenBeforeDateTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-09-01");
            assertTrue(d.hasHappenedBefore(LocalDate.parse("2020-09-05")));
            assertFalse(d.hasHappenedBefore(LocalDate.parse("2020-09-01")));
            assertFalse(d.hasHappenedBefore(LocalDate.parse("2020-08-03")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBeforeTodayTest() {
        try {
            LocalDate today = LocalDate.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline d1 = new Deadline("Assignment 1", td);
            assertFalse(d1.hasHappenedBeforeToday());

            Deadline d2 = new Deadline("Assignment 1", "2030-08-01");
            assertFalse(d2.hasHappenedBeforeToday());

            Deadline d3 = new Deadline("Assignment 1", "2020-08-01");
            assertTrue(d3.hasHappenedBeforeToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenAfterDateTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-09-01");
            assertFalse(d.isHappeningAfter(LocalDate.parse("2020-09-05")));
            assertTrue(d.isHappeningAfter(LocalDate.parse("2020-07-01")));
            assertFalse(d.isHappeningAfter(LocalDate.parse("2020-09-01")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenAfterTodayTest() {
        try {
            LocalDate today = LocalDate.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline d1 = new Deadline("Assignment 1", td);
            assertFalse(d1.isHappeningAfterToday());

            Deadline d2 = new Deadline("Assignment 1", "2030-08-01");
            assertTrue(d2.isHappeningAfterToday());

            Deadline d3 = new Deadline("Assignment 1", "2020-08-01");
            assertFalse(d3.isHappeningAfterToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBetweenTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-09-03");
            assertFalse(d.isHappeningBetween(LocalDate.parse("2020-08-01"), LocalDate.parse("2020-09-01")));
            assertTrue(d.isHappeningBetween(LocalDate.parse("2020-09-01"), LocalDate.parse("2020-09-04")));
            assertTrue(d.isHappeningBetween(LocalDate.parse("2020-09-03"), LocalDate.parse("2020-09-03")));
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void happenInTest() {
        try {
            LocalDate newDate = LocalDate.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline d = new Deadline("Assignment 1", newStr);
            assertTrue(d.willHappenInDays(3));
            assertTrue(d.willHappenInDays(2));
            assertFalse(d.willHappenInDays(1));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void isOverdueTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-08-02");
            assertTrue(d.isOverdue());

            d.markAsDone();
            assertFalse(d.isOverdue());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void outputTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-08-02");
            assertEquals("D | 0 | Assignment 1 | By: 2020-08-02\n", d.output());

            d.markAsDone();
            assertEquals("D | 1 | Assignment 1 | By: 2020-08-02\n", d.output());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-08-02");
            assertEquals("[D][\u2718] Assignment 1 (by: Aug 2 2020) This is overdue! The deadline has passed!!!", d.toString());

            d.markAsDone();
            assertEquals("[D][\u2713] Assignment 1 (by: Aug 2 2020)", d.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
