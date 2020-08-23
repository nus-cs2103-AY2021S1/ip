package duke.task;

import org.junit.jupiter.api.Test;

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
}
