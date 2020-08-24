import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkTaskAsDone() {
        String expected = "Nice! I've marked this task as done:\n"
                + "  [D][✓] return book (by: tmr)\n";
        String actual = new Deadline("return book", "tmr").markAsDone();
        assertEquals(expected, actual);
    }

    @Test
    public void testPrintingOfTask() {
        String expected = "[T][✘] read book";
        String actual = new ToDo("read book").toString();
        assertEquals(expected, actual);
    }
}
