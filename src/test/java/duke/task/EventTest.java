package duke.task;

import static duke.TestUtils.EVENT_COMMAND_CORRECT;
import static duke.TestUtils.EVENT_COMMAND_EMPTY;
import static duke.TestUtils.EVENT_COMMAND_INCORRECT_DATE;
import static duke.TestUtils.EVENT_COMMAND_INCORRECT_FORMAT;
import static duke.TestUtils.EVENT_DONE_PRINT;
import static duke.TestUtils.EVENT_DONE_STRING;
import static duke.TestUtils.EVENT_UNDONE_PRINT;
import static duke.TestUtils.EVENT_UNDONE_STRING;
import static duke.TestUtils.createDoneEvent;
import static duke.TestUtils.createUndoneEvent;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandFormatException;

public class EventTest {
    @Test
    public void displayEvent_undoneEvent_correctStringForUser() {
        assertEquals(EVENT_UNDONE_STRING, createUndoneEvent().toString());
    }

    @Test
    public void displayEvent_doneEvent_correctStringForUser() {
        assertEquals(EVENT_DONE_STRING, createDoneEvent().toString());
    }

    @Test
    public void displayEvent_undoneEvent_correctStringForStoring() {
        assertEquals(EVENT_UNDONE_PRINT, createUndoneEvent().print());
    }

    @Test
    public void displayEvent_doneEvent_correctStringForStoring() {
        assertEquals(EVENT_DONE_PRINT, createDoneEvent().print());
    }

    @Test
    public void parseCommand_correctCommand_returnsEvent() {
        assertDoesNotThrow(() -> assertEquals(createUndoneEvent(), Event.of(EVENT_COMMAND_CORRECT)));
    }

    @Test
    public void parseCommand_emptyCommand_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> Event.of(EVENT_COMMAND_EMPTY));
    }

    @Test
    public void parseCommand_incorrectFormat_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> Event.of(EVENT_COMMAND_INCORRECT_FORMAT));
    }

    @Test
    public void parseCommand_incorrectDate_throwsException() {
        assertThrows(InvalidCommandFormatException.class, () -> Event.of(EVENT_COMMAND_INCORRECT_DATE));
    }
}
