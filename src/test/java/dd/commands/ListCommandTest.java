package dd.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.Deadline;
import dd.tasks.Event;
import dd.tasks.Task;
import dd.tasks.TaskList;
import dd.tasks.Todo;
import dd.ui.Ui;

public class ListCommandTest {

    @Test
    public void list_success() throws DukeException {
        String res = "Here is your current list of task(s)!\n"
                + "**************************************************\n"
                + "1. [T][✘] borrow book\n";

        Todo t = new Todo("borrow book");
        ArrayList<Task> a = new ArrayList<>();
        a.add(t);

        TaskList tasks = new TaskList();
        tasks.getTaskList().add(t);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        String actual = new ListCommand("list", "").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                actual.replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void checkDate_success() throws DukeException {
        String res = "Here is your list of task(s) on 31-12-2020:\n"
                + "**************************************************\n"
                + "1. [D][✘] return book (by: 31 Dec 2020)\n";

        Todo t = new Todo("borrow book");
        Deadline d = new Deadline("return book", "31 Dec 2020");
        ArrayList<Task> a = new ArrayList<>();
        a.add(t);
        a.add(d);

        TaskList tasks = new TaskList();
        tasks.getTaskList().add(t);
        tasks.getTaskList().add(d);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        String actual = new ListCommand("check", "31-12-2020").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                actual.replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void checkDate_invalidDate_exceptionThrown() {
        String res = "I don't understand :( Please input date as DD-MM-YYYY Example: 31-12-2020";

        Todo t = new Todo("borrow book");
        Deadline d = new Deadline("return book", "31 Dec 2020");
        ArrayList<Task> a = new ArrayList<>();
        a.add(t);
        a.add(d);

        TaskList tasks = new TaskList();
        tasks.getTaskList().add(t);
        tasks.getTaskList().add(d);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new ListCommand("check", "9 June").execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void checkDate_noDateMatch_exceptionThrown() {
        String res = "No tasks found on 09-06-2020!";

        Todo t = new Todo("borrow book");
        Deadline d = new Deadline("return book", "31 Dec 2020");
        ArrayList<Task> a = new ArrayList<>();
        a.add(t);
        a.add(d);

        TaskList tasks = new TaskList();
        tasks.getTaskList().add(t);
        tasks.getTaskList().add(d);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new ListCommand("check", "09-06-2020").execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void checkDesc_success() throws DukeException {
        String res = "Here is the list of task(s) related to book:\n"
                + "**************************************************\n"
                + "1. [T][✘] borrow book\n"
                + "2. [D][✘] return book (by: 31 Dec 2020)\n";

        Todo t = new Todo("borrow book");
        Deadline d = new Deadline("return book", "31 Dec 2020");
        Event e = new Event("meeting", "06 Jun 2020");
        ArrayList<Task> a = new ArrayList<>();
        a.add(t);
        a.add(d);
        a.add(e);

        TaskList tasks = new TaskList();
        tasks.getTaskList().add(t);
        tasks.getTaskList().add(d);
        tasks.getTaskList().add(e);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        String actual = new ListCommand("find", "book").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                actual.replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void checkDate_noDescMatch_exceptionThrown() {
        String res = "No tasks related to meeting!";

        Todo t = new Todo("borrow book");
        Deadline d = new Deadline("return book", "31 Dec 2020");
        ArrayList<Task> a = new ArrayList<>();
        a.add(t);
        a.add(d);

        TaskList tasks = new TaskList();
        tasks.getTaskList().add(t);
        tasks.getTaskList().add(d);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new ListCommand("find", "meeting").execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }
}
