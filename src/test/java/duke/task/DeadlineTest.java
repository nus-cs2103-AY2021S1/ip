package duke.task;

import static duke.TestUtils.DEADLINE_COMMAND_CORRECT;
import static duke.TestUtils.DEADLINE_COMMAND_EMPTY;
import static duke.TestUtils.DEADLINE_COMMAND_INCORRECT_DATE;
import static duke.TestUtils.DEADLINE_COMMAND_INCORRECT_FORMAT;
import static duke.TestUtils.DEADLINE_DONE_PRINT;
import static duke.TestUtils.DEADLINE_DONE_STRING;
import static duke.TestUtils.DEADLINE_UNDONE_PRINT;
import static duke.TestUtils.DEADLINE_UNDONE_STRING;
import static duke.TestUtils.createDoneDeadline;
import static duke.TestUtils.createUndoneDeadline;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;

public class DeadlineTest {
    @Test
    public void displayDeadline_undoneDeadline_correctStringForUser() {
        assertEquals(DEADLINE_UNDONE_STRING, createUndoneDeadline().toString());
    }

    @Test
    public void displayDeadline_doneDeadline_correctStringForUser() {
        assertEquals(DEADLINE_DONE_STRING, createDoneDeadline().toString());
    }

    @Test
    public void displayDeadline_undoneDeadline_correctStringForStoring() {
        assertEquals(DEADLINE_UNDONE_PRINT, createUndoneDeadline().print());
    }

    @Test
    public void displayDeadline_doneDeadline_correctStringForStoring() {
        assertEquals(DEADLINE_DONE_PRINT, createDoneDeadline().print());
    }

    @Test
    public void parseCommand_correctCommand_returnsDeadline() {
        assertDoesNotThrow(() -> assertEquals(createUndoneDeadline(), Deadline.of(DEADLINE_COMMAND_CORRECT)));
    }

    @Test
    public void parseCommand_emptyCommand_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> Deadline.of(DEADLINE_COMMAND_EMPTY));
    }

    @Test
    public void parseCommand_incorrectFormat_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> Deadline.of(DEADLINE_COMMAND_INCORRECT_FORMAT));
    }

    @Test
    public void parseCommand_incorrectDate_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> Deadline.of(DEADLINE_COMMAND_INCORRECT_DATE));
    }
}
