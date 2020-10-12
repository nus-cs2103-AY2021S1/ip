package duke.command;

import static duke.TestUtils.EVENT_COMMAND_CORRECT;
import static duke.TestUtils.TODO_COMMAND_CORRECT;
import static duke.TestUtils.TODO_COMMAND_EMPTY;
import static duke.TestUtils.TODO_UNDONE_STRING;
import static duke.TestUtils.createUndoneToDo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;
import duke.ui.Message;

public class ToDoCommandTest {
    @Test
    public void execute_toDoCommand_returnsCorrectMessage() {
        ToDoCommand toDoCommand = new ToDoCommand(TODO_COMMAND_CORRECT);
        assertDoesNotThrow(() -> {
            Message message = toDoCommand.execute(new TaskListDouble(), new StorageDouble(""));
            assertEquals("Task added: " + TODO_UNDONE_STRING + "\n", message.toString());
        });
    }

    @Test
    public void isDone_toDoCommand_returnsFalse() {
        ToDoCommand toDoCommand = new ToDoCommand(EVENT_COMMAND_CORRECT);
        assertFalse(toDoCommand.isDone());
    }

    @Test
    public void parseCommand_correctCommand_returnsToDo() {
        assertDoesNotThrow(() -> assertEquals(createUndoneToDo(), new ToDoCommand(TODO_COMMAND_CORRECT).createToDo()));
    }

    @Test
    public void parseCommand_emptyCommand_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> new ToDoCommand(TODO_COMMAND_EMPTY).createToDo());
    }
}
