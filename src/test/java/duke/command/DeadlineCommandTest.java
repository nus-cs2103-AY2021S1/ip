package duke.command;

import static duke.TestUtils.DEADLINE_COMMAND_CORRECT;
import static duke.TestUtils.DEADLINE_COMMAND_EMPTY;
import static duke.TestUtils.DEADLINE_COMMAND_INCORRECT_DATE;
import static duke.TestUtils.DEADLINE_COMMAND_INCORRECT_FORMAT;
import static duke.TestUtils.DEADLINE_UNDONE_STRING;
import static duke.TestUtils.createUndoneDeadline;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;
import duke.ui.Message;

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

    @Test
    public void parseCommand_correctCommand_returnsDeadline() {
        assertDoesNotThrow(() -> assertEquals(createUndoneDeadline(),
                new DeadlineCommand(DEADLINE_COMMAND_CORRECT).createDeadline()));
    }

    @Test
    public void parseCommand_emptyCommand_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                new DeadlineCommand(DEADLINE_COMMAND_EMPTY).createDeadline());
    }

    @Test
    public void parseCommand_incorrectFormat_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                new DeadlineCommand(DEADLINE_COMMAND_INCORRECT_FORMAT).createDeadline());
    }

    @Test
    public void parseCommand_incorrectDate_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                new DeadlineCommand(DEADLINE_COMMAND_INCORRECT_DATE).createDeadline());
    }
}
