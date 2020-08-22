package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
            assertEquals(true, d.happenOnDate(LocalDate.parse("2020-09-01")));
            assertEquals(false, d.happenOnDate(LocalDate.parse("2020-09-04")));
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
            assertEquals(true, d1.happenToday());

            LocalDate newDate = LocalDate.now().plusDays(2);
            String newStr = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline d2 = new Deadline("Assignment 1", newStr);
            assertEquals(false, d2.happenToday());
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void happenBeforeDateTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-09-01");
            assertEquals(true, d.happenBeforeDate(LocalDate.parse("2020-09-05")));
            assertEquals(false, d.happenBeforeDate(LocalDate.parse("2020-09-01")));
            assertEquals(false, d.happenBeforeDate(LocalDate.parse("2020-08-03")));
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
            assertEquals(false, d1.happenBeforeToday());

            Deadline d2 = new Deadline("Assignment 1", "2030-08-01");
            assertEquals(false, d2.happenBeforeToday());

            Deadline d3 = new Deadline("Assignment 1", "2020-08-01");
            assertEquals(true, d3.happenBeforeToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenAfterDateTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-09-01");
            assertEquals(false, d.happenAfterDate(LocalDate.parse("2020-09-05")));
            assertEquals(true, d.happenAfterDate(LocalDate.parse("2020-07-01")));
            assertEquals(false, d.happenAfterDate(LocalDate.parse("2020-09-01")));
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
            assertEquals(false, d1.happenAfterToday());

            Deadline d2 = new Deadline("Assignment 1", "2030-08-01");
            assertEquals(true, d2.happenAfterToday());

            Deadline d3 = new Deadline("Assignment 1", "2020-08-01");
            assertEquals(false, d3.happenAfterToday());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happenBetweenTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-09-03");
            assertEquals(false, d.happenBetween(LocalDate.parse("2020-08-01"), LocalDate.parse("2020-09-01")));
            assertEquals(true, d.happenBetween(LocalDate.parse("2020-09-01"), LocalDate.parse("2020-09-04")));
            assertEquals(true, d.happenBetween(LocalDate.parse("2020-09-03"), LocalDate.parse("2020-09-03")));
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
            assertEquals(true, d.happenIn(3));
            assertEquals(true, d.happenIn(2));
            assertEquals(false, d.happenIn(1));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void isOverdueTest() {
        try {
            Deadline d = new Deadline("Assignment 1", "2020-08-02");
            assertEquals(true, d.isOverdue());

            d.markAsDone();
            assertEquals(false, d.isOverdue());
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
