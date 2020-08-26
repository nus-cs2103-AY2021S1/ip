package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.*;
import dd.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListCommandTest {

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
    public void list_success() throws DukeException {
        String res = "1. [T][✘] borrow book\n ";

        Todo t = new Todo("borrow book");
        ArrayList<Task> a = new ArrayList<>();
        a.add(t);

        TaskList tasks = new TaskList();
        tasks.getTaskList().add(t);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        new ListCommand("list", "").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void checkDate_success() throws DukeException {
        String res = "Here is your list of task(s) on 31-12-2020:\n "
                + "1. [D][✘] return book (by: 31 Dec 2020)\n ";

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

        new ListCommand("check", "31-12-2020").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
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
            new ListCommand("check", "9 June").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
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
            new ListCommand("check", "09-06-2020").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }

    @Test
    public void checkDesc_success() throws DukeException {
        String res = "Here is the list of task(s) related to book:\n "
                + "1. [T][✘] borrow book\n "
                + "2. [D][✘] return book (by: 31 Dec 2020)\n ";

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

        new ListCommand("find", "book").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
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
            new ListCommand("find", "meeting").execute(tasks, ui, ds);

            assertEquals("", outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }
}
