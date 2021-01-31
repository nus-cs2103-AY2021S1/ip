package duke.command;

import static duke.TestUtils.TODO_DONE_STRING;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;
import duke.ui.Message;

public class DeleteCommandTest {
    @Test
    public void executes_delete_returnCorrectMessage() {
        String[] input = {"delete", "1"};
        DeleteCommand deleteCommand = new DeleteCommand(input);
        assertDoesNotThrow(() -> {
            Message message = deleteCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("Task deleted: " + TODO_DONE_STRING + "\n", message.toString());
        });
    }

    @Test
    public void executes_noTaskNumber_throwsException() {
        String[] input = {"delete"};
        DeleteCommand deleteCommand = new DeleteCommand(input);
        assertThrows(InvalidCommandFormatException.class, () -> {
            deleteCommand.execute(new TaskListDouble(), new StorageDouble(""));
        });
    }

    @Test
    public void executes_notANumber_throwsException() {
        String[] input = {"delete", "a"};
        DeleteCommand deleteCommand = new DeleteCommand(input);
        assertThrows(InvalidCommandFormatException.class, () -> {
            deleteCommand.execute(new TaskListDouble(), new StorageDouble(""));
        });
    }

    @Test
    public void isDone_doneCommand_returnsFalse() {
        String[] input = {"delete", "1"};
        DeleteCommand deleteCommand = new DeleteCommand(input);
        assertFalse(deleteCommand.isDone());
    }
}
