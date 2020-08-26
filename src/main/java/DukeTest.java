import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * JUnit test class
 * Used to test the individual class to ensure correctness
 */
public class DukeTest {

    @Test
    public void toDoTest() {
        ToDo todo = new ToDo("return book");
        Assert.assertEquals(todo.toString(), "[T][\u2718] return book");
    }

    @Test
    public void eventTest() {
        Event event = new Event("project meeting","2/12/2019 1800");
        Assert.assertEquals(event.toString(), "[E][\u2718] project meeting (at: 18:00, 02 Jan 2019)");
    }

    @Test
    public void DeadlineTest() {
        Deadline deadline = new Deadline("return book","2/12/2019 1800");
        Assert.assertEquals(deadline.toString(), "[D][\u2718] return book (by: 18:00, 02 Jan 2019)");
    }

    @Test
    public void parseCommandInvalidTest() {
        Parser parser = new Parser();
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        parser.parseCommand(new String[]{"test"},new TaskList(new ArrayList<Task>()));
        Assert.assertEquals(os.toString(), "OOPS!!! I'm sorry, but I don't know what that means :-(" + System.getProperty("line.separator"));
    }

}
