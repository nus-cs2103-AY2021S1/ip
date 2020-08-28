package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.component.CliUi;

public class ByeCommandTest {
    @Test
    public void isExit_alwaysTrue() {
        ByeCommand b = new ByeCommand("bye");
        assertTrue(b.isExit());
    }

    @Test
    public void execute_nothingWrong() {
        CliUi ui = new CliUi();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        ByeCommand b = new ByeCommand("bye");
        assertEquals("bye", b.execute(ui, list, storage));
    }
}
