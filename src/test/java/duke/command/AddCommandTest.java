package duke.command;

import duke.component.*;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {
    @Test
    public void isExit__alwaysFalse() {
        assertFalse(new AddCommand("anything").isExit());
        assertFalse(new AddCommand("todo anything").isExit());
        assertFalse(new AddCommand("event anything").isExit());
        assertFalse(new AddCommand("deadline anything").isExit());
    }

    public void executeExceptionHelper(String s, Ui ui, TaskList list, Storage storage) {
        AddCommand a = new AddCommand(s);
        try {
            a.execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            try {
                Parser.generate(s);
                fail();
            } catch (Exception e2) {
                assertEquals(e2.getMessage(), e.getMessage());
            }
        }
    }

    @Test
    public void execute_invalidCommand_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        list.add(new ToDo("hello"));

        executeExceptionHelper("bash", ui, list, storage);
        executeExceptionHelper("done", ui, list, storage);
        executeExceptionHelper("delete", ui, list, storage);
        executeExceptionHelper("happen", ui, list, storage);
        executeExceptionHelper("todo", ui, list, storage);
        executeExceptionHelper("deadline do something", ui, list, storage);
        executeExceptionHelper("event meeting /at", ui, list, storage);
        executeExceptionHelper("event meeting /at 2020-08-09", ui, list, storage);
    }

    @Test
    public void execute_validCommand_addTask() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        Task task = new ToDo("hello");
        Task task2 = new ToDo("world");

        try {
            String res = new AddCommand("todo hello").execute(ui, list, storage);
            assertEquals("Got it. I've added this task:\n\t    " + task +
                    "\n\t  Now you have 1 task in the list.", res);

            String res2 = new AddCommand("todo world").execute(ui, list, storage);
            assertEquals("Got it. I've added this task:\n\t    " + task2 +
                    "\n\t  Now you have 2 tasks in the list.", res2);
        } catch (Exception e) {
            fail();
        }
    }
}
