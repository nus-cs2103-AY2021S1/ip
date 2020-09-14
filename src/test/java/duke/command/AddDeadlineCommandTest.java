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
    public void testCorrectFormats() {
        try {
            Deadline ct1 = new Deadline("Test", DateTimeParser.getDateTime("2020-08-23"));
            Deadline ct2 = new Deadline("test2", DateTimeParser.getDateTime("2020-08-23T10:15"));
            Deadline ct3 = new Deadline("test2", DateTimeParser.getDateTime("22:15"));
            AddDeadlineCommand cmd1 = new AddDeadlineCommand("Test /by 2020-08-23");
            AddDeadlineCommand cmd2 = new AddDeadlineCommand("test2 /by 2020-08-23 10:15");
            AddDeadlineCommand cmd3 = new AddDeadlineCommand("test2 /by 2020-08-23T10:15");
            AddDeadlineCommand cmd4 = new AddDeadlineCommand("test2 /by 2020-08-23 10:15:33");
            AddDeadlineCommand cmd5 = new AddDeadlineCommand("test2 /by22:15");
            // Tests
            assertEquals(ui.addTask(ct1, 1), cmd1.execute(taskList, ui, storage));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(ct2, 2), cmd2.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct2, 3), cmd3.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct2, 4), cmd4.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct3, 5), cmd5.execute(taskList, ui, storage));
            assertEquals(5, storage.getTasks().size());
        } catch (DukeException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            fail();
        }
    }

    /**
     * Tests for invalid deadline commands.
     */
    @Test
    public void testInvalidDeadlineFormats() {
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
    public void testBlankDescription() {
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
    public void testEmptyTimeFrame() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("meeting /by");
        String deadlineMessage = "OOPS!!! Time of deadline task is not specified!\n";
        // Tests
        EmptyTimeException e = assertThrows(EmptyTimeException.class, () -> cmd1.execute(taskList, ui, storage));
        assertEquals(deadlineMessage, e.getMessage());
        assertTrue(taskList.isEmpty());
    }
}
