package duke.task;

import static duke.TestUtils.TODO_COMMAND_CORRECT;
import static duke.TestUtils.TODO_COMMAND_EMPTY;
import static duke.TestUtils.TODO_DONE_PRINT;
import static duke.TestUtils.TODO_DONE_STRING;
import static duke.TestUtils.TODO_UNDONE_PRINT;
import static duke.TestUtils.TODO_UNDONE_STRING;
import static duke.TestUtils.createDoneToDo;
import static duke.TestUtils.createUndoneToDo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;

public class ToDoTest {
    @Test
    public void displayToDo_undoneToDo_correctStringForUser() {
        assertEquals(TODO_UNDONE_STRING, createUndoneToDo().toString());
    }

    @Test
    public void displayToDo_doneToDo_correctStringForUser() {
        assertEquals(TODO_DONE_STRING, createDoneToDo().toString());
    }

    @Test
    public void displayToDo_undoneToDo_correctStringForStoring() {
        assertEquals(TODO_UNDONE_PRINT, createUndoneToDo().print());
    }

    @Test
    public void displayToDo_doneToDo_correctStringForStoring() {
        assertEquals(TODO_DONE_PRINT, createDoneToDo().print());
    }

    @Test
    public void parseCommand_correctCommand_returnsToDo() {
        assertDoesNotThrow(() -> assertEquals(createUndoneToDo(), ToDo.of(TODO_COMMAND_CORRECT)));
    }

    @Test
    public void parseCommand_emptyCommand_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> ToDo.of(TODO_COMMAND_EMPTY));
    }
}
