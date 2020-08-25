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

public class ListCommandTest {

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void listCommand_success() throws DukeException {
        String res = "Ok, To-do added:\n  " + new Todo("borrow book")
                + "\n " + "You now have 1 task(s) in your list!\n ";
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        new AddCommand("todo", "borrow book").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void addCommand_event_invalidDate_exceptionThrown() {
        String res = "I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                + "Example: 31-12-2020 or 31-12-2020 2359";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            new AddCommand("event", "meeting /at 9 June").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        }
        catch (DukeException e) {
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
