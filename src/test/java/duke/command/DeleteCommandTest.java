package duke.command;

import duke.component.*;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {
    @Test
    public void isExit_alwaysFalse() {
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
}
