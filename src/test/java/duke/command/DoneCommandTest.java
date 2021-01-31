package duke.command;

import static duke.TestUtils.TODO_DONE_STRING;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;
import duke.ui.Message;

public class DoneCommandTest {
    @Test
    public void executes_markAsDone_returnCorrectMessage() {
        String[] input = {"done", "1"};
        DoneCommand doneCommand = new DoneCommand(input);
        assertDoesNotThrow(() -> {
            Message message = doneCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("Task marked as done: " + TODO_DONE_STRING + "\n", message.toString());
        });
    }

    @Test
    public void executes_noTaskNumber_throwsException() {
        String[] input = {"done"};
        DoneCommand doneCommand = new DoneCommand(input);
        assertThrows(InvalidCommandFormatException.class, () -> {
            doneCommand.execute(new TaskListDouble(), new StorageDouble(""));
        });
    }

    @Test
    public void executes_notANumber_throwsException() {
        String[] input = {"done", "a"};
        DoneCommand doneCommand = new DoneCommand(input);
        assertThrows(InvalidCommandFormatException.class, () -> {
            doneCommand.execute(new TaskListDouble(), new StorageDouble(""));
        });
    }

    @Test
    public void isDone_doneCommand_returnsFalse() {
        String[] input = {"done", "1"};
        DoneCommand doneCommand = new DoneCommand(input);
        assertFalse(doneCommand.isDone());
    }
}
