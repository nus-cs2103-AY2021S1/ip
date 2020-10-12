package duke.storage;

import static duke.TestUtils.DEADLINE_UNDONE_PRINT;
import static duke.TestUtils.EVENT_UNDONE_PRINT;
import static duke.TestUtils.TODO_UNDONE_PRINT;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;


public class StorageParserTest {
    @Test
    public void readTask_addToDo_doesNotThrowException() {
        assertDoesNotThrow(() -> StorageParser.parseTask(TODO_UNDONE_PRINT));
    }

    @Test
    public void readTask_addDeadline_doesNotThrowException() {
        assertDoesNotThrow(() -> StorageParser.parseTask(DEADLINE_UNDONE_PRINT));
    }

    @Test
    public void readTask_addEvent_doesNotThrowException() {
        assertDoesNotThrow(() -> StorageParser.parseTask(EVENT_UNDONE_PRINT));
    }
}
