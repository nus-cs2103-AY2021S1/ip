import main.java.duke.Parser;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.exception.DukeException;
import main.java.duke.exception.DukeInputNotRecognizedException;
import main.java.duke.exception.DukeTaskNotFoundException;
import main.java.duke.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testDelete() throws DukeException {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("read book");
        taskList.getTasks().add(toDo);
        Parser.parse("delete 1").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Noted. Target Scraped: \n   " + toDo.toString() +
                        " \n Now you have 0 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDone() throws DukeException {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("read book");
        taskList.getTasks().add(toDo);
        Parser.parse("done 1").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Nice! Target Eliminated: \n   " + toDo.toString(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testUndo() throws DukeException {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("read book");
        toDo.doneTask();
        taskList.getTasks().add(toDo);
        Parser.parse("undo 1").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Task has been undone: \n   " + toDo.toString(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testToDo() throws DukeException {
        TaskList taskList = new TaskList();
        Parser.parse("todo read book").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Got it. I've added this task: " + System.lineSeparator() + "   [T][✘] read book "
                + "\n Now you have 1 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDeadline() throws DukeException {
        TaskList taskList = new TaskList();
        Parser.parse("deadline return book /by 2/12/2019 1800").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Got it. I've added this task: " + System.lineSeparator()
                + "   [D][✘] return book  (by: Dec 2 2019 1800 PM) "
                + "\n Now you have 1 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testEvent() throws DukeException {
        TaskList taskList = new TaskList();
        Parser.parse("event return book /at 2/12/2019 4-6pm").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Got it. I've added this task: " + System.lineSeparator()
                + "   [E][✘] return book  (at: 2/12/2019 4-6pm) "
                + "\n Now you have 1 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
