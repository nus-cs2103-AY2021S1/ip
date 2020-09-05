package junimo.task;

import junimo.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void constructorTest() {
        // Test with correct arguments
        Deadline deadline1 = new Deadline("Eat Apple", "2020-12-12", false);
        Deadline deadline2 = new Deadline("Write paper", "2022-01-21", true);

        assertEquals("Eat Apple", deadline1.description);
        assertEquals(LocalDate.parse("2020-12-12"), deadline1.by);
        assertEquals(false, deadline1.isDone);
        assertEquals("Write paper", deadline2.description);
        assertEquals(LocalDate.parse("2022-01-21"), deadline2.by);
        assertEquals(true, deadline2.isDone);
    }

    @Test
    public void invalidDateConstructorTest1() {
        // Test with incorrect arguments
        assertThrows(IllegalArgumentException.class, () ->
                new Deadline("Eat Ice cream", "2023-90-23", true));
    }

    @Test
    public void invalidDateConstructorTest2() {
        assertThrows(IllegalArgumentException.class, () ->
                new Deadline("Do work", "wrong date", false));
    }

    @Test
    public void getParsedTaskTest() {
        Deadline deadline = new Deadline("Eat Apple", "2020-12-12", true);
        String expectedParsedTask = "deadline Eat Apple /by 2020-12-12" + System.lineSeparator()
                + "true" + System.lineSeparator();
        assertEquals(expectedParsedTask, deadline.getParsedTask());
    }

    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("Eat Apple", "2020-12-12", true);
        String expectedToString = "[D][\u2713] Eat Apple (by: Dec 12 2020)";
        assertEquals(expectedToString, deadline.toString());
    }

    @Test
    public void equalsTest() {
        Deadline deadline1 = new Deadline("Eat Apple", "2020-12-12", true);
        Deadline deadline2 = new Deadline("Eat Apple", "2020-12-12", true);
        assertTrue(deadline1.equals(deadline2));
    }
}
