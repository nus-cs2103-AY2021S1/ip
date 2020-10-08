package duke.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;
import duke.ui.Message;

public class FindCommandTest {
    @Test
    public void execute_findTasks_returnCorrectMessage() {
        FindCommand findCommand = new FindCommand("find hello");
        assertDoesNotThrow(() -> {
            Message result = findCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("No tasks found.\n", result.toString());
        });
    }

    @Test
    public void execute_emptyKeyword_throwsException() {
        FindCommand findCommand = new FindCommand("find");
        assertThrows(InvalidCommandFormatException.class, () -> {
            findCommand.execute(new TaskListDouble(), new StorageDouble(""));
        });
    }

    @Test
    public void isDone_findCommand_returnsFalse() {
        FindCommand findCommand = new FindCommand("find hello");
        assertFalse(findCommand.isDone());
    }
}
