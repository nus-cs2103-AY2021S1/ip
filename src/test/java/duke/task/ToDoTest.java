package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        assertEquals(false, t.happenOnDate(today));
        assertEquals(false, t.happenToday());
        assertEquals(false, t.happenBeforeDate(today));
        assertEquals(false, t.happenBeforeToday());
        assertEquals(false, t.happenAfterDate(today));
        assertEquals(false, t.happenAfterToday());
        assertEquals(false, t.happenBetween(today, today));
        assertEquals(false, t.happenIn(8));
    }

    @Test
    public void happen_beforeToday_alwaysFalse() {
        ToDo t = new ToDo("return books");
        LocalDate d1 = LocalDate.parse("2019-08-10");
        LocalDate d2 = LocalDate.parse("2010-09-01");

        assertEquals(false, t.happenOnDate(d1));
        assertEquals(false, t.happenToday());
        assertEquals(false, t.happenBeforeDate(d1));
        assertEquals(false, t.happenBeforeToday());
        assertEquals(false, t.happenAfterDate(d1));
        assertEquals(false, t.happenAfterToday());
        assertEquals(false, t.happenBetween(d1, d2));
        assertEquals(false, t.happenIn(8));
    }

    @Test
    public void happen_afterToday_alwaysFalse() {
        ToDo t = new ToDo("return books");
        LocalDate d1 = LocalDate.parse("2029-08-10");
        LocalDate d2 = LocalDate.parse("2030-09-01");

        assertEquals(false, t.happenOnDate(d1));
        assertEquals(false, t.happenToday());
        assertEquals(false, t.happenBeforeDate(d1));
        assertEquals(false, t.happenBeforeToday());
        assertEquals(false, t.happenAfterDate(d1));
        assertEquals(false, t.happenAfterToday());
        assertEquals(false, t.happenBetween(d1, d2));
        assertEquals(false, t.happenIn(8));
    }
}
