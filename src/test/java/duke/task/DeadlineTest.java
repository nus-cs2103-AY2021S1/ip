package duke.task;

import static duke.TestUtils.DEADLINE_DONE_PRINT;
import static duke.TestUtils.DEADLINE_DONE_STRING;
import static duke.TestUtils.DEADLINE_UNDONE_PRINT;
import static duke.TestUtils.DEADLINE_UNDONE_STRING;
import static duke.TestUtils.createDoneDeadline;
import static duke.TestUtils.createUndoneDeadline;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}
