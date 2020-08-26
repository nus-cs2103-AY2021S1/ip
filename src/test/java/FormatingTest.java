package test.java;

import Duke.main.Formating;
import Duke.main.Time;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormatingTest {
    @Test
    public void formatShortenTest1() {
        String test =
                new Formating<>("   hahaha  ")
                .shorten()
                .getContent();

        String expected = "hahaha";
        assertTrue(test.equals(expected));
    }

    @Test
    public void formatShortenTest2() {
        String test =
                new Formating<>("       ")
                        .shorten()
                        .getContent();

        String expected = "";
        assertTrue(test.equals(expected));
    }

    @Test
    public void formatStringToTaskTest1() {
        String string = "[T][✘] reading";
        Task expected = new Todo("reading");
        Task todo = new Formating<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

    @Test
    public void formatStringToTaskTest2() {
        String string = "[D][✘] eating (by: Aug_30_2020)";
        Task expected = new Deadline("eating", new Time("2020-08-30").toString());
        Task todo = new Formating<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

    @Test
    public void formatStringToTaskTest3() {
        String string = "[E][✘] eating (at: Aug_30_2020)";
        Task expected = new Event("eating", new Time("2020-08-30").toString());
        Task todo = new Formating<>(string).stringToTask();
        assertTrue(todo.toString().equals(expected.toString()));
    }

}
