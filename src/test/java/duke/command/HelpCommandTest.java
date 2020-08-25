package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HelpCommandTest {
    private final HelpCommand helpCommand = new HelpCommand();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(helpCommand.isExit());
    }

    @Test
    public void testExecute() {
        try {
            helpCommand.execute("help me", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tDid you meant the command 'help'?", e.getMessage());
        }
    }
}
