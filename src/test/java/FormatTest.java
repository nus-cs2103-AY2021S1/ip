import duke.exception.DukeException;
import duke.tools.Format;
import duke.tools.Time;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Format class.
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
        String test = Format.shorten("   hahaha  ");

        String expected = "hahaha";
        assertEquals(test, expected);
    }

    /**
     * Tests the shorten() method.
     * This is when the input string has
     * an string with only spaces.
     */
    @Test
    public void formatShortenTest2() {
        String test =
                Format.shorten("       ");

        String expected = "";
        assertEquals(test, expected);
    }

    /**
     * Tests the stringToTask() method.
     * This is when the input is of a task of Todo.
     */
    @Test
    public void formatStringToTaskTest1() {
        String string = "[T][✘] reading";
        Task expected = new Todo("reading");
        Task todo = Format.decodeTask(string);
        assertEquals(todo.toString(), expected.toString());
    }

    /**
     * Tests the stringToTask() method.
     * This is when the input is of a task of Deadline.
     */
    @Test
    public void formatStringToTaskTest2() throws DukeException {
        String string = "[D][✘] eating (by: Aug_30_2020)";
        Task expected = new Deadline("eating", new Time("2020-08-30").toString());
        Task deadline = Format.decodeTask(string);
        assertEquals(deadline.toString(), expected.toString());
    }

    /**
     * Tests the stringToTask() method.
     * This is when the input is of a task of Event.
     */
    @Test
    public void formatStringToTaskTest3() throws DukeException {
        String string = "[E][✘] eating (at: Aug_30_2020)";
        Task expected = new Event("eating", new Time("2020-08-30").toString());
        Task event = Format.decodeTask(string);
        assertEquals(event.toString(), expected.toString());
    }

}
