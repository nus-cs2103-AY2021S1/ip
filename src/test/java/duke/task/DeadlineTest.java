package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void newDeadlineTest() {
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
}
