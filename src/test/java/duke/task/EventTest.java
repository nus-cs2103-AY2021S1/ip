package duke.task;

import static duke.TestUtils.EVENT_DONE_PRINT;
import static duke.TestUtils.EVENT_DONE_STRING;
import static duke.TestUtils.EVENT_UNDONE_PRINT;
import static duke.TestUtils.EVENT_UNDONE_STRING;
import static duke.TestUtils.createDoneEvent;
import static duke.TestUtils.createUndoneEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}
