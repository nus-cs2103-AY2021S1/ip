package duke.command;

import duke.component.*;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {
    @Test
    public void isExit__alwaysFalse() {
        assertFalse(new DeleteCommand("delete ").isExit());
        assertFalse(new DeleteCommand("delete 3").isExit());
        assertFalse(new DeleteCommand("delete 0").isExit());
        assertFalse(new DeleteCommand("delete -3").isExit());
        assertFalse(new DeleteCommand("delete anything").isExit());
    }

    public void executeExceptionHelper(String s, Ui ui, TaskList list, Storage storage) {
        DeleteCommand a = new DeleteCommand(s);
        try {
            a.execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            try {
                Parser.isValidDelete(s, 1);
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

        executeExceptionHelper("delete 0", ui, list, storage);
        executeExceptionHelper("delete -3", ui, list, storage);
        executeExceptionHelper("delete 5", ui, list, storage);
        executeExceptionHelper("delete anything", ui, list, storage);
        executeExceptionHelper("delete 3 5", ui, list, storage);
        executeExceptionHelper("delete ", ui, list, storage);
    }

    @Test
    public void execute_validCommand_deleteTask() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        Task task1 = new ToDo("hello");
        Task task2 = new ToDo("world");
        Task task3 = new ToDo("test");
        list.add(task1);
        list.add(task2);
        list.add(task3);

        try {
            assertEquals("Noted. I've removed this task:\n\t    " + task1 +
                    "\n\t  Now you have 2 tasks", new DeleteCommand("delete 1").execute(ui, list, storage));
            assertEquals("Noted. I've removed this task:\n\t    " + task3 +
                    "\n\t  Now you have 1 task", new DeleteCommand("delete 2").execute(ui, list, storage));
            assertEquals("Noted. I've removed this task:\n\t    " + task2 +
                    "\n\t  Now you have 0 task", new DeleteCommand("delete 1").execute(ui, list, storage));
        } catch (Exception e) {
            fail();
        }
    }
}
