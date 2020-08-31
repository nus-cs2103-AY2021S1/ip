package test.java;

import Duke.main.Format;
import Duke.main.Time;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class is to test the Format class.
 * Mainly 2 methods are tested: shorten() and stringToTask().
 */
public class FormatTest {
    //Warning: clear the strings in data/Duke.txt before testing.

    /**
     * Tests the shorten() method.
     * This is when the input string has
     * unnecessary spaces at forth and in behind.
     */
    @Test
    public void formatShortenTest1() {
        String test =
                new Format<>("   hahaha  ")
                .shorten()
                .getContent();

        String expected = "hahaha";
        assertTrue(test.equals(expected));
    }

    /**
     * Tests the shorten() method.
     * This is when the input string has
     * an string with only spaces.
     */
    @Test
    public void formatShortenTest2() {
        String test =
                new Format<>("       ")
                        .shorten()
                        .getContent();

        String expected = "";
        assertTrue(test.equals(expected));
    }

    /**
     * Tests the stringToTask() method.
     * This is when the input is of a task of Todo.
     */
    @Test
    public void formatStringToTaskTest1() {
        String string = "[T][✘] reading";
        Task expected = new Todo("reading");
        Task todo = new Format<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

    /**
     * Tests the stringToTask() method.
     * This is when the input is of a task of Deadline.
     */
    @Test
    public void formatStringToTaskTest2() {
        String string = "[D][✘] eating (by: Aug_30_2020)";
        Task expected = new Deadline("eating", new Time("2020-08-30").toString());
        Task todo = new Format<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

    /**
     * Tests the stringToTask() method.
     * This is when the input is of a task of Event.
     */
    @Test
    public void formatStringToTaskTest3() {
        String string = "[E][✘] eating (at: Aug_30_2020)";
        Task expected = new Event("eating", new Time("2020-08-30").toString());
        Task todo = new Format<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

}
