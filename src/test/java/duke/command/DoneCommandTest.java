package duke.command;

import duke.component.*;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoneCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new DoneCommand("done ").isExit());
        assertFalse(new DoneCommand("done 3").isExit());
        assertFalse(new DoneCommand("done 0").isExit());
        assertFalse(new DoneCommand("done -3").isExit());
        assertFalse(new DoneCommand("done anything").isExit());
    }

    public void executeExceptionHelper(String s, Ui ui, TaskList list, Storage storage) {
        DoneCommand a = new DoneCommand(s);
        try {
            a.execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            try {
                Parser.isValidDone(s, 1);
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
        Task task = new ToDo("hello");
        list.add(task);

        executeExceptionHelper("done 0", ui, list, storage);
        executeExceptionHelper("done -3", ui, list, storage);
        executeExceptionHelper("done 5", ui, list, storage);
        executeExceptionHelper("done anything", ui, list, storage);
        executeExceptionHelper("done 3 5", ui, list, storage);
        executeExceptionHelper("done ", ui, list, storage);
    }

    @Test
    public void execute_validCommand_doneTask() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        Task task1 = new ToDo("hello");
        list.add(task1);

        try {
            String res = new DoneCommand("done 1").execute(ui, list, storage);
            assertEquals("Nice! I've marked this task as done:\n\t    " + task1, res);
        } catch (Exception e) {
            fail();
        }
    }
}
