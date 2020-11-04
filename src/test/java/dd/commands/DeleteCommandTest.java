package dd.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.tasks.Todo;
import dd.ui.Ui;

public class DeleteCommandTest {

    @Test
    public void execute_success() throws DukeException {
        Todo delTask = new Todo("borrow book");

        String res = "Alright! I've deleted the task:  \n" + delTask
                + "\n" + "You now have 0 task(s) in your list!";

        TaskList tasks = new TaskList();
        tasks.addTask(delTask);

        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        String actual = new DeleteCommand("delete", "1").execute(tasks, ui, ds);

        assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                actual.replaceAll("\\p{Cntrl}", " "));
    }

    @Test
    public void execute_invalidTaskNumber_exceptionThrown() {
        String res = "hmm.. I don't think thats a valid task, try again?";

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        DataStorage ds = new DataStorage();

        try {
            String actual = new DeleteCommand("delete", "1").execute(tasks, ui, ds);

            assertEquals("", actual.replaceAll("\\p{Cntrl}", " "));
            fail();
        } catch (DukeException e) {
            assertEquals(res.replaceAll("\\p{Cntrl}", " "),
                    e.getMessage().replaceAll("\\p{Cntrl}", " "));
        }
    }
}
