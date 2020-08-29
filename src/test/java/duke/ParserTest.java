package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    private final String TEST_TODO = "todo call mom";
    private final String TEST_DEADLINE = "deadline homework /by 2020-08-29";
    private final String TEST_DEADLINE_NO_NAME = "deadline";
    private final String TEST_EVENT = "event seminar /at 2020-10-10";
    private final String TEST_EVENT_NO_DATE = "event seminar";
    private final String TEST_DONE = "done 1";
    private final String TEST_DELETE_NO_INDEX = "delete";
    private final String TEST_DELETE_INVALID_INDEX = "delete x";

    private Parser parser = new Parser();


    @Test
    public void getCommandWord_toDoItem_toDoReturned() throws Exception {
        parser.setCommandLine(TEST_TODO);
        assertEquals("todo", parser.getCommandWord());
    }

    @Test
    public void getTaskName_toDoItem_taskNameReturned() throws Exception {
        parser.setCommandLine(TEST_TODO);
        assertEquals("call mom", parser.getTaskName());
    }

    @Test
    public void getTaskDate_toDoItem_blankReturned() throws Exception {
        parser.setCommandLine(TEST_TODO);
        assertEquals("", parser.getTaskDate());
    }

    @Test
    public void getTaskDate_deadlineItem_taskDateReturned() throws Exception {
        parser.setCommandLine(TEST_DEADLINE);
        assertEquals("2020-08-29", parser.getTaskDate());
    }

    @Test
    public void getTaskName_deadlineItemNoName_exceptionThrown() {
        try {
            parser.setCommandLine(TEST_DEADLINE_NO_NAME);
        } catch (Exception e) {
            assertEquals("Error caused by input: deadline", e.getMessage());
            assertEquals("Please name the deadline item!", e.toString());
        }

    }

    @Test
    public void getTaskDate_eventItem_taskDateReturned() throws Exception {
        parser.setCommandLine(TEST_EVENT);
        assertEquals("2020-10-10", parser.getTaskDate());
    }

    @Test
    public void getTaskDate_eventItemNoDate_blankReturned() throws Exception {
        parser.setCommandLine(TEST_EVENT_NO_DATE);
        assertEquals("", parser.getTaskDate());
    }

    @Test
    public void getTaskNumber_doneItem_taskDateReturned() throws Exception {
        parser.setCommandLine(TEST_DONE);
        assertEquals(1, parser.getTaskNumber());
    }

    @Test
    public void getTaskNumber_deleteItemNoIndex_zeroReturned() {
        try {
            parser.setCommandLine(TEST_DELETE_NO_INDEX);
        } catch (Exception e) {
            assertEquals("Error caused by input: delete", e.getMessage());
            assertEquals("Please provide an integer index for delete!", e.toString());
        }
    }

    @Test
    public void getTaskNumber_deleteItemInvalidIndex_exceptionThrown() {
        try {
            parser.setCommandLine(TEST_DELETE_INVALID_INDEX);
            fail();
        } catch (Exception e) {
            assertEquals("Error caused by input: delete", e.getMessage());
            assertEquals("Please provide an integer index for delete!", e.toString());
        }

    }
}
