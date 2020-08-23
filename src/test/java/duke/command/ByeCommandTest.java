package duke.command;

import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.component.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isExit__alwaysTrue() {
        ByeCommand b = new ByeCommand("bye");
        assertTrue(b.isExit());
    }

    @Test
    public void execute__nothingWrong() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        ByeCommand b = new ByeCommand("bye");
        assertEquals("bye", b.execute(ui, list, storage));
    }
}
