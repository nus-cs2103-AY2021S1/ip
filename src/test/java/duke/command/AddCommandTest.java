package duke.command;

import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.component.Ui;
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

    @Test
    public void execute_unrecognizedCommand_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        AddCommand a = new AddCommand("bash");
        try {
            a.execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        AddCommand b = new AddCommand("done");
        try {
            b.execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        AddCommand c = new AddCommand("happen");
        try {
            c.execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
