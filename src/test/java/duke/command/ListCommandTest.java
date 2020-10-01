package duke.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Message;
import duke.exception.InvalidCommandFormatException;

public class ListCommandTest {
    @Test
    public void execute_listAllTasks_returnCorrectMessage() {
        String[] input = {"list"};
        ListCommand listCommand = new ListCommand(input);
        assertDoesNotThrow(() -> {
            Message result = listCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("No tasks found.\n", result.toString());
        });
    }

    @Test
    public void execute_incorrectListAllTasks_throwsException() {
        String[] input = {"list", "hello"};
        ListCommand listCommand = new ListCommand(input);
        assertThrows(InvalidCommandFormatException.class, () -> {
            listCommand.execute(new TaskListDouble(), new StorageDouble(""));
        });
    }

    @Test
    public void execute_listTasksOnDate_returnCorrectMessage() {
        String[] input = {"list", "2020-01-01"};
        ListCommand listCommand = new ListCommand(input);
        assertDoesNotThrow(() -> {
            Message result = listCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("No tasks found.\n", result.toString());
        });
    }

    @Test
    public void execute_incorrectListTasksOnDate_throwsException() {
        String[] input = {"list", "january"};
        ListCommand listCommand = new ListCommand(input);
        assertThrows(InvalidCommandFormatException.class, () -> {
            listCommand.execute(new TaskListDouble(), new StorageDouble(""));
        });
    }

    @Test
    public void isDone_listCommand_returnsFalse() {
        String[] input = {"list"};
        ListCommand listCommand = new ListCommand(input);
        assertFalse(listCommand.isDone());
    }
}
