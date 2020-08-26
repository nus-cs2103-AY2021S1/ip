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

public class DoneCommandTest {

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void doneCommand_success() throws DukeException {
        Todo doneTask = new Todo("borrow book");
        doneTask.markAsDone();

        String res = "Wow!! Good job!!\n  " + doneTask + "\n ";

        TaskList tasks = new TaskList();
        tasks.addTask(doneTask);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        new DoneCommand("done", "1").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void doneCommand_invalidTaskNumber_exceptionThrown() {
        String res = "hmm.. I don't think thats a valid task, try again?";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            new DoneCommand("done", "1").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
