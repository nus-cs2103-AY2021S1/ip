package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.component.CliUi;
import duke.task.Task;
import duke.task.ToDo;

public class DoneCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new DoneCommand("done ").isExit());
        assertFalse(new DoneCommand("done 3").isExit());
        assertFalse(new DoneCommand("done 0").isExit());
        assertFalse(new DoneCommand("done -3").isExit());
        assertFalse(new DoneCommand("done anything").isExit());
    }

    public void executeExceptionHelper(String s, CliUi ui, TaskList list, Storage storage) {
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
        CliUi ui = new CliUi();
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
    public void execute_markDoneAsDone_throwException() {
        CliUi ui = new CliUi();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        Task task = new ToDo("hello");
        list.add(task);
        try {
            task.markAsDone();
        } catch (Exception e) {
            fail();
        }

        try {
            String res = new DoneCommand("done 1").execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("The task " + task + " has already been done.", e.getMessage());
        }
    }

    @Test
    public void execute_validCommand_doneTask() {
        CliUi ui = new CliUi();
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
