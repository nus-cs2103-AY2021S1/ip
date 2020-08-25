package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.Deadline;
import dd.tasks.Event;
import dd.tasks.TaskList;
import dd.tasks.Todo;
import dd.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.String;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    // implementation of code from https://www.baeldung.com/java-testing-system-out-println
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void addCommand_todo_success() throws DukeException {
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
    public void addCommand_deadline_success() throws DukeException {
        String res = "Ok, Deadline added:\n  " + new Deadline("return book", "31 Dec 2020")
                + "\n " + "You now have 1 task(s) in your list!\n ";
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        new AddCommand("deadline", "return book /by 31-12-2020").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void addCommand_deadline_invalidDeadline_exceptionThrown() {
        String res = "Due date not detected, try again!\n"
                + "Please input deadline as 'deadline (title) /by (date)'\n"
                + "Example: deadline return book /by 31-12-2020";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            new AddCommand("deadline", "return book").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        }
        catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void addCommand_deadline_invalidDate_exceptionThrown() {
        String res = "I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                + "Example: 31-12-2020 or 31-12-2020 2359";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            new AddCommand("deadline", "return book /by 9 June").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        }
        catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void addCommand_event_success() throws DukeException {
        String res = "Ok, Event added:\n  " + new Event("meeting", "31 Dec 2020 02:00 PM")
                + "\n " + "You now have 1 task(s) in your list!\n ";
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        new AddCommand("event", "meeting /at 31-12-2020 1400").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void addCommand_event_invalidEvent_exceptionThrown() {
        String res = "Event date not detected, try again!\n"
                + "Please input event as 'event (title) /at (date)'\n"
                + "Example: event group meeting /at 31-12-2020";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            new AddCommand("event", "return book /at").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        }
        catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
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
