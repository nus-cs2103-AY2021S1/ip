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
        unrecognizedCommandHelper("happen on 1 2 3", ui, list, storage);
        unrecognizedCommandHelper("happen after 1 2", ui, list, storage);
        unrecognizedCommandHelper("happen in 3 months", ui, list, storage);
        unrecognizedCommandHelper("happen between a b c", ui, list, storage);
        unrecognizedCommandHelper("happen between a", ui, list, storage);
    }

    public void invalidDateFormatHelper(String s, Ui ui, TaskList list, Storage storage) {
        try {
            new HappenCommand(s).execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid date format. Please use yyyy-MM-dd.", e.getMessage());
        }
    }

    @Test
    public void execute_invalidDateFormat_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        invalidDateFormatHelper("happen on 2020", ui, list, storage);
        invalidDateFormatHelper("happen on tomorrow", ui, list, storage);
        invalidDateFormatHelper("happen before tomorrow", ui, list, storage);
        invalidDateFormatHelper("happen between 2020-08-09 2020/09/01", ui, list, storage);
    }

    @Test
    public void execute_nonPositiveInDays_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        try {
            new HappenCommand("happen in 0 days").execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Please input a positive integer for happen in command.", e.getMessage());
        }

        try {
            new HappenCommand("happen in -3 days").execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Please input a positive integer for happen in command.", e.getMessage());
        }
    }

    @Test
    public void execute_invalidBetween_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        try {
            new HappenCommand("happen between 2020-09-01 2020-08-01").execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Latter date is before former date for happen between.", e.getMessage());
        }
    }
}
