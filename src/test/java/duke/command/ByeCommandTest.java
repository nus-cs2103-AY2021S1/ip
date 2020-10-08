package duke.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.ui.Message;

public class ByeCommandTest {
    @Test
    public void execute_bye_returnCorrectMessage() {
        ByeCommand byeCommand = new ByeCommand();
        assertDoesNotThrow(() -> {
            Message result = byeCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("Have a nice day.\n", result.toString());
        });
    }

    @Test
    public void isDone_byeCommand_returnsTrue() {
        ByeCommand byeCommand = new ByeCommand();
        assertTrue(byeCommand.isDone());
    }
}
