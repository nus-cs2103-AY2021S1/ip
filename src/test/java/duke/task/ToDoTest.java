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
    public void output__toWriteStorage() {
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
    public void toString__systemOutput() {
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
        assertFalse(t.isHappeningOn(today));
        assertFalse(t.isHappeningToday());
        assertFalse(t.hasHappenedBefore(today));
        assertFalse(t.hasHappenedBeforeToday());
        assertFalse(t.isHappeningAfter(today));
        assertFalse(t.isHappeningAfterToday());
        assertFalse(t.isHappeningBetween(today, today));
        assertFalse(t.willHappenInDays(8));
    }

    @Test
    public void happen_beforeToday_alwaysFalse() {
        ToDo t = new ToDo("return books");
        LocalDate d1 = LocalDate.parse("2019-08-10");
        LocalDate d2 = LocalDate.parse("2010-09-01");

        assertFalse(t.isHappeningOn(d1));
        assertFalse(t.isHappeningToday());
        assertFalse(t.hasHappenedBefore(d1));
        assertFalse(t.hasHappenedBeforeToday());
        assertFalse(t.isHappeningAfter(d1));
        assertFalse(t.isHappeningAfterToday());
        assertFalse(t.isHappeningBetween(d1, d2));
        assertFalse(t.willHappenInDays(8));
    }

    @Test
    public void happen_afterToday_alwaysFalse() {
        ToDo t = new ToDo("return books");
        LocalDate d1 = LocalDate.parse("2029-08-10");
        LocalDate d2 = LocalDate.parse("2030-09-01");

        assertFalse(t.isHappeningOn(d1));
        assertFalse(t.isHappeningToday());
        assertFalse(t.hasHappenedBefore(d1));
        assertFalse(t.hasHappenedBeforeToday());
        assertFalse(t.isHappeningAfter(d1));
        assertFalse(t.isHappeningAfterToday());
        assertFalse(t.isHappeningBetween(d1, d2));
        assertFalse(t.willHappenInDays(8));
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
