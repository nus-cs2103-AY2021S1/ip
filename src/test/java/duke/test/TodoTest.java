package duke.test;


import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {

    private static final char DONE = '\u2713';
    private static final char NOT_DONE = '\u2717';

    // @Test
    // public void dummyTest() {
    //     assertEquals(2, 2);
    // }


    @Test
    public void constructor_NotDone_toString_printedCorrectly() {
        Todo testTodo = new Todo("stuff");
        String expected = String.format("[T][%c] %s", NOT_DONE, "stuff");
        assertEquals(expected, testTodo.toString());
    }


    @Test
    public void constructor_Done_toString_printedCorrectly() {
        Todo testTodo = new Todo("stuff", true);
        String expected = String.format("[T][%c] %s", DONE, "stuff");
        assertEquals(expected, testTodo.toString());
    }

}
