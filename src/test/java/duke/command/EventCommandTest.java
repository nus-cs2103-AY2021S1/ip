package duke.command;

import static duke.TestUtils.EVENT_COMMAND_CORRECT;
import static duke.TestUtils.EVENT_COMMAND_EMPTY;
import static duke.TestUtils.EVENT_COMMAND_INCORRECT_DATE;
import static duke.TestUtils.EVENT_COMMAND_INCORRECT_FORMAT;
import static duke.TestUtils.EVENT_UNDONE_STRING;
import static duke.TestUtils.createUndoneEvent;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;
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

    @Test
    public void parseCommand_correctCommand_returnsEvent() {
        assertDoesNotThrow(() -> assertEquals(createUndoneEvent(),
                new EventCommand(EVENT_COMMAND_CORRECT).createEvent()));
    }

    @Test
    public void parseCommand_emptyCommand_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                new EventCommand(EVENT_COMMAND_EMPTY).createEvent());
    }

    @Test
    public void parseCommand_incorrectFormat_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                new EventCommand(EVENT_COMMAND_INCORRECT_FORMAT).createEvent());
    }

    @Test
    public void parseCommand_incorrectDate_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                new EventCommand(EVENT_COMMAND_INCORRECT_DATE).createEvent());
    }
}
