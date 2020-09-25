package duke.commands;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class UnknownCommandTest {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    void init() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
    }

    @Test
    @DisplayName("Invalid command (hello world)")
    public void invalidInput_helloWorld_unknownCommandExceptionThrown() {
        String type = "hello world";
        UnknownCommand command = new UnknownCommand(new String[]{type});
        assertThrows(UnknownCommandException.class, () -> command.execute(tasks, ui, storage));
    }

    @Test
    @DisplayName("Invalid command (blah)")
    public void invalidInput_blah_unknownCommandExceptionThrown() {
        String type = "blah";
        UnknownCommand command = new UnknownCommand(new String[]{type});
        assertThrows(UnknownCommandException.class, () -> command.execute(tasks, ui, storage));
    }
}
