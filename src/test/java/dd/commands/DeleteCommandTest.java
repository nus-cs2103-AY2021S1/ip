package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.tasks.Todo;
import dd.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {

    //@@author g-erm-reused
    //Reused from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    //@@author

    @Test
    public void execute_success() throws DukeException {
        Todo delTask = new Todo("borrow book");

        String res = "Alright! I've deleted the task:\n  " + delTask
                + "\n " + "You now have 0 task(s) in your list!\n ";

        TaskList tasks = new TaskList();
        tasks.addTask(delTask);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        new DeleteCommand("delete", "1").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void execute_invalidTaskNumber_exceptionThrown() {
        String res = "hmm.. I don't think thats a valid task, try again?";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            new DeleteCommand("delete", "1").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }
}
