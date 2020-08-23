package duke.command;

import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.component.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HappenCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new HappenCommand("happen on today").isExit());
    }

    public void unrecognizedCommandHelper(String s, Ui ui, TaskList list, Storage storage) {
        try {
            new HappenCommand(s).execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid happen command input.", e.getMessage());
        }
    }

    @Test
    public void execute_unrecognizedCommand_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        unrecognizedCommandHelper("happen ", ui, list, storage);
        unrecognizedCommandHelper("happen anything", ui, list, storage);
        unrecognizedCommandHelper("happen onward", ui, list, storage);
    }
}
