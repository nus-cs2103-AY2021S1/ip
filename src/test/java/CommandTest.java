import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.ToDo;

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
    public void parse_deleteCommand_exceptionThrown() throws DukeException {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("read book");
        taskList.getTasks().add(toDo);
        Parser.parse("delete 1").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Noted. Target Scraped: \n   " + toDo.toString() +
                        " \n Now you have 0 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void parse_doneCommand_exceptionThrown() throws DukeException {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("read book");
        taskList.getTasks().add(toDo);
        Parser.parse("done 1").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Nice! Target Eliminated: \n   " + toDo.toString(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void parse_undoCommand_exceptionThrown() throws DukeException {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("read book");
        toDo.doneTask();
        taskList.getTasks().add(toDo);
        Parser.parse("undo 1").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Task has been undone: \n   " + toDo.toString(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void parse_toDoCommand_exceptionThrown() throws DukeException {
        TaskList taskList = new TaskList();
        Parser.parse("todo read book").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Got it. I've added this task: " + System.lineSeparator() + "   [T][X] read book "
                + "\n Now you have 1 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void parse_deadline_exceptionThrown() throws DukeException {
        TaskList taskList = new TaskList();
        Parser.parse("deadline return book /by 2/12/2019 1800").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Got it. I've added this task: " + System.lineSeparator()
                + "   [D][X] return book  (by: Dec 2 2019 6:00 PM) "
                + "\n Now you have 1 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void parse_event_exceptionThrown() throws DukeException {
        TaskList taskList = new TaskList();
        Parser.parse("event return book /at 2/12/2019 4-6pm").execute(taskList, new Ui(), new Storage("invalidPath/task.txt"));

        assertEquals("Got it. I've added this task: " + System.lineSeparator()
                + "   [E][X] return book  (at: 2/12/2019 4-6pm) "
                + "\n Now you have 1 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
