package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    @Test
    public void constructor_anyInput_noException() {
        try {
            ToDo t = new ToDo("return books");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void outputTest() {
        try {
            ToDo t = new ToDo("return books");
            assertEquals("T | 0 | return books\n", t.output());

            t.markAsDone();
            assertEquals("T | 1 | return books\n", t.output());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        try {
            ToDo t = new ToDo("return books");
            assertEquals("[T][\u2718] return books", t.toString());

            t.markAsDone();
            assertEquals("[T][\u2713] return books", t.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void happen_today_alwaysFalse() {
        ToDo t = new ToDo("return books");
        LocalDate today = LocalDate.now();
        assertFalse(t.happenOnDate(today));
        assertFalse(t.happenToday());
        assertFalse(t.happenBeforeDate(today));
        assertFalse(t.happenBeforeToday());
        assertFalse(t.happenAfterDate(today));
        assertFalse(t.happenAfterToday());
        assertFalse(t.happenBetween(today, today));
        assertFalse(t.happenIn(8));
    }

    @Test
    public void happen_beforeToday_alwaysFalse() {
        ToDo t = new ToDo("return books");
        LocalDate d1 = LocalDate.parse("2019-08-10");
        LocalDate d2 = LocalDate.parse("2010-09-01");

        assertFalse(t.happenOnDate(d1));
        assertFalse(t.happenToday());
        assertFalse(t.happenBeforeDate(d1));
        assertFalse(t.happenBeforeToday());
        assertFalse(t.happenAfterDate(d1));
        assertFalse(t.happenAfterToday());
        assertFalse(t.happenBetween(d1, d2));
        assertFalse(t.happenIn(8));
    }

    @Test
    public void happen_afterToday_alwaysFalse() {
        ToDo t = new ToDo("return books");
        LocalDate d1 = LocalDate.parse("2029-08-10");
        LocalDate d2 = LocalDate.parse("2030-09-01");

        assertFalse(t.happenOnDate(d1));
        assertFalse(t.happenToday());
        assertFalse(t.happenBeforeDate(d1));
        assertFalse(t.happenBeforeToday());
        assertFalse(t.happenAfterDate(d1));
        assertFalse(t.happenAfterToday());
        assertFalse(t.happenBetween(d1, d2));
        assertFalse(t.happenIn(8));
    }

    @Test
    public void markAsDone_markingDoneAsDone_throwException() {
        try {
            ToDo t = new ToDo("return books");
            t.markAsDone();
            t.markAsDone();
            fail();
        } catch (Exception e) {
            assertEquals("The task [T][\u2713] return books has already been done.", e.getMessage());
        }
    }
}
