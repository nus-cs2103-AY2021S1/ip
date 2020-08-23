package duke.command;

import duke.component.*;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {
    @Test
    public void isExit_alwaysFalse() {
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
        executeExceptionHelper("done 3", ui, list, storage);
        executeExceptionHelper("done 0", ui, list, storage);
        executeExceptionHelper("done -1", ui, list, storage);
        executeExceptionHelper("done 1", ui, list, storage);
        executeExceptionHelper("done 1", ui, list, storage);
        executeExceptionHelper("happen", ui, list, storage);
        executeExceptionHelper("todo", ui, list, storage);
        executeExceptionHelper("deadline do something", ui, list, storage);
        executeExceptionHelper("event meeting /at", ui, list, storage);
        executeExceptionHelper("event meeting /at 2020-08-09", ui, list, storage);
    }
}
