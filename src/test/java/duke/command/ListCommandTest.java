package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.component.CliUi;

public class ListCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new ListCommand("list").isExit());
    }

    @Test
    public void execute_nothingWrong() {
        CliUi ui = new CliUi();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        ListCommand b = new ListCommand("list");
        assertEquals("list", b.execute(ui, list, storage));
    }
}
