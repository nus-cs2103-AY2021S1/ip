package duke.command;

import static duke.TestUtils.EVENT_COMMAND_CORRECT;
import static duke.TestUtils.EVENT_UNDONE_STRING;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.ui.Message;

public class EventCommandTest {
    @Test
    public void execute_eventCommand_returnsCorrectMessage() {
        EventCommand eventCommand = new EventCommand(EVENT_COMMAND_CORRECT);
        assertDoesNotThrow(() -> {
            Message message = eventCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("Task added: " + EVENT_UNDONE_STRING + "\n", message.toString());
        });
    }

    @Test
    public void isDone_eventCommand_returnsFalse() {
        EventCommand eventCommand = new EventCommand(EVENT_COMMAND_CORRECT);
        assertFalse(eventCommand.isDone());
    }
}
