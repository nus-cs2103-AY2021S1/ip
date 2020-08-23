package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isExit_alwaysTrue() {
        ByeCommand b = new ByeCommand("bye");
        assertTrue(b.isExit());
    }

    @Test
    public void execute_nothingWrong() {
        /*Ui ui = new Ui();
        Storage storage;
        TaskList list;
        try {
            storage = new Storage("../data/tasks.txt");
            list = storage.getList();
        } catch (Exception e) {
            ui.output(e.getMessage());
            list = new TaskList();
        }
        ByeCommand b = new ByeCommand("bye");
        assertEquals("bye", b.execute(ui, storage, list));*/
    }
}
