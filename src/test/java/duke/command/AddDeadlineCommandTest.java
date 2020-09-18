package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidDeadlineException;
import duke.parser.DateTimeParser;
import duke.task.Deadline;

/**
 * Tests the AddDeadlineCommand.
 */
public class AddDeadlineCommandTest extends CommandTests {

    /**
     * Tests for various time / date formats. Correct formats are yyyy-MM-dd, yyyy-MM-dd hh:mm, and hh:mm.
     */
    @Test
    public void execute_CorrectFormats() {
        try {
            Deadline ct1 = new Deadline("Test", DateTimeParser.getDateTime("23-8-20"));
            Deadline ct2 = new Deadline("test2", DateTimeParser.getDateTime("25-8-20 1015"));
            Deadline ct3 = new Deadline("test3", DateTimeParser.getDateTime("2215"));
            AddDeadlineCommand cmd1 = new AddDeadlineCommand("Test /by 23-8-20");
            AddDeadlineCommand cmd2 = new AddDeadlineCommand("test2 /by 25-8-20 1015");
            AddDeadlineCommand cmd5 = new AddDeadlineCommand("test3 /by2215");
            // DateTimeFormat
            assertEquals(ui.addTask(ct1, 1), cmd1.execute(taskList, ui, storage));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(ct2, 2), cmd2.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct3, 3), cmd5.execute(taskList, ui, storage));
            assertEquals(3, storage.getTasks().size());
        } catch (DukeException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            fail();
        }
    }

    /**
     * Tests for invalid deadline commands.
     */
    @Test
    public void execute_InvalidDeadlineFormats() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("read /by 2-4pm");
        AddDeadlineCommand cmd2 = new AddDeadlineCommand("read /at 2020-08-23");
        AddDeadlineCommand cmd3 = new AddDeadlineCommand("read -/by 2020-08-23");
        AddDeadlineCommand cmd4 = new AddDeadlineCommand("read/by2020-08-23");
        // Tests
        assertThrows(InvalidDateException.class, () -> cmd1.execute(taskList, ui, storage));
        assertThrows(InvalidDeadlineException.class, () -> cmd2.execute(taskList, ui, storage));
        assertThrows(InvalidDeadlineException.class, () -> cmd3.execute(taskList, ui, storage));
        assertThrows(InvalidDeadlineException.class, () -> cmd4.execute(taskList, ui, storage));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for blank descriptions.
     */
    @Test
    public void execute_BlankDescription() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("");
        AddDeadlineCommand cmd2 = new AddDeadlineCommand("/by 2020-08-23");
        // Tests
        assertThrows(InvalidDeadlineException.class, () -> cmd1.execute(taskList, ui, storage));
        assertThrows(InvalidDeadlineException.class, () -> cmd2.execute(taskList, ui, storage));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for empty time description.
     */
    @Test
    public void execute_EmptyTimeFrame_throwsEmptyTimeException() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("meeting /by");
        String deadlineMessage = "OOPS!!! Time of deadline task is not specified!\n";
        // Tests
        EmptyTimeException e = assertThrows(EmptyTimeException.class, () -> cmd1.execute(taskList, ui, storage));
        assertEquals(deadlineMessage, e.getMessage());
        assertTrue(taskList.isEmpty());
    }
}
