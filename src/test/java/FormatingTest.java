package test.java;

import Duke.main.Formating;
import Duke.main.Time;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class is to test the Formating class.
 * Mainly 2 methods are tested: shorten() and stringToTask().
 */
public class FormatingTest {

    /**
     * This is to test the shorten() method.
     * This is when the input string has
     * unnecessary spaces at forth and in behind.
     */
    @Test
    public void formatShortenTest1() {
        String test =
                new Formating<>("   hahaha  ")
                .shorten()
                .getContent();

        String expected = "hahaha";
        assertTrue(test.equals(expected));
    }

    /**
     * This is to test the shorten() method.
     * This is when the input string has
     * an string with only spaces.
     */
    @Test
    public void formatShortenTest2() {
        String test =
                new Formating<>("       ")
                        .shorten()
                        .getContent();

        String expected = "";
        assertTrue(test.equals(expected));
    }

    /**
     * This is to test the stringToTask() method.
     * This is when the input is of a task of Todo.
     */
    @Test
    public void formatStringToTaskTest1() {
        String string = "[T][✘] reading";
        Task expected = new Todo("reading");
        Task todo = new Formating<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

    /**
     * This is to test the stringToTask() method.
     * This is when the input is of a task of Deadline.
     */
    @Test
    public void formatStringToTaskTest2() {
        String string = "[D][✘] eating (by: Aug_30_2020)";
        Task expected = new Deadline("eating", new Time("2020-08-30").toString());
        Task todo = new Formating<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

    /**
     * This is to test the stringToTask() method.
     * This is when the input is of a task of Event.
     */
    @Test
    public void formatStringToTaskTest3() {
        String string = "[E][✘] eating (at: Aug_30_2020)";
        Task expected = new Event("eating", new Time("2020-08-30").toString());
        Task todo = new Formating<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

}
