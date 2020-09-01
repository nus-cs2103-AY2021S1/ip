package duke.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;


public class TodoTest {

    private static final char DONE = '\u2713';
    private static final char NOT_DONE = '\u2717';

    // @Test
    // public void dummyTest() {
    //     assertEquals(2, 2);
    // }


    @Test
    public void constructorNotDone_toString_printedCorrectly() {
        Todo testTodo = new Todo("stuff");
        String expected = String.format("[T][%c] %s", NOT_DONE, "stuff");
        assertEquals(expected, testTodo.toString());
    }


    @Test
    public void constructorDone_toString_printedCorrectly() {
        Todo testTodo = new Todo("stuff", true);
        String expected = String.format("[T][%c] %s", DONE, "stuff");
        assertEquals(expected, testTodo.toString());
    }

}
