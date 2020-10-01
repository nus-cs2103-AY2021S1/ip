package duke.command;

import static duke.TestUtils.DEADLINE_COMMAND_CORRECT;
import static duke.TestUtils.DEADLINE_UNDONE_STRING;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.Message;

public class DeadlineCommandTest {
    @Test
    public void execute_deadlineCommand_returnsCorrectMessage() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(DEADLINE_COMMAND_CORRECT);
        assertDoesNotThrow(() -> {
            Message message = deadlineCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("Task added: " + DEADLINE_UNDONE_STRING + "\n", message.toString());
        });
    }

    @Test
    public void isDone_deadlineCommand_returnsFalse() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(DEADLINE_COMMAND_CORRECT);
        assertFalse(deadlineCommand.isDone());
    }
}
