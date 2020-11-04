package dd.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.Deadline;
import dd.tasks.Event;
import dd.tasks.TaskList;
import dd.tasks.Todo;
import dd.ui.Ui;

public class AddCommandTest {

    @Test
    public void addTodo_success() throws DukeException {
        String res = "Ok, To-do added:  \n" + new Todo("borrow book")
                + "\n" + "You now have 1 task(s) in your list!";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        String actual = new AddCommand("todo", "borrow book").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                actual.replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void addDeadline_success() throws DukeException {
        String res = "Ok, Deadline added:  \n" + new Deadline("return book", "31 Dec 2020")
                + "\n" + "You now have 1 task(s) in your list!";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        String actual = new AddCommand("deadline", "return book /by 31-12-2020")
                .execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                actual.replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void addDeadline_invalidDeadline_exceptionThrown() {
        String res = "Due date not detected, try again!\n"
                + "Please input deadline as 'deadline (title) /by (date)'\n"
                + "Example: deadline return book /by 31-12-2020";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new AddCommand("deadline", "return book")
                    .execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void addDeadline_invalidDate_exceptionThrown() {
        String res = "I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                + "Example: 31-12-2020 or 31-12-2020 2359";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new AddCommand("deadline", "return book /by 9 June")
                    .execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void addEvent_success() throws DukeException {
        String res = "Ok, Event added:  \n" + new Event("meeting", "31 Dec 2020 02:00 PM")
                + "\n" + "You now have 1 task(s) in your list!";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        String actual = new AddCommand("event", "meeting /at 31-12-2020 1400")
                .execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                actual.replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void addEvent_invalidEvent_exceptionThrown() {
        String res = "Event date not detected, try again!\n"
                + "Please input event as 'event (title) /at (date)'\n"
                + "Example: event group meeting /at 31-12-2020";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new AddCommand("event", "return book /at")
                    .execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void addEvent_invalidDate_exceptionThrown() {
        String res = "I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                + "Example: 31-12-2020 or 31-12-2020 2359";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new AddCommand("event", "meeting /at 9 June")
                    .execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }
}
