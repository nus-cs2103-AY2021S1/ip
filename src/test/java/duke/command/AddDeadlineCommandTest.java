package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import duke.exception.DukeException;
import duke.exception.DuplicateTaskException;
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
    public void execute_correctFormats_success() {
        try {
            Deadline ct1 = new Deadline("Test", DateTimeParser.getDateTime("23-8-20"));
            Deadline ct2 = new Deadline("test2", DateTimeParser.getDateTime("25-8-20 1015"));
            Deadline ct3 = new Deadline("test3", DateTimeParser.getDateTime("2215"));
            AddDeadlineCommand cmd1 = new AddDeadlineCommand("Test /by 23-8-20");
            AddDeadlineCommand cmd2 = new AddDeadlineCommand("test2 /by 25-8-20 1015");
            AddDeadlineCommand cmd5 = new AddDeadlineCommand("test3 /by2215");

            // DateTimeFormat
            assertEquals(ui.addTask(ct1, 1), executeTask(cmd1));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(ct2, 2), executeTask(cmd2));
            assertEquals(ui.addTask(ct3, 3), executeTask(cmd5));
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
    public void execute_invalidDeadlineFormats_throwsInvalidDeadlineException() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("read /by 2-4pm");
        AddDeadlineCommand cmd2 = new AddDeadlineCommand("read /at 2020-08-23");
        AddDeadlineCommand cmd3 = new AddDeadlineCommand("read -/by 2020-08-23");
        AddDeadlineCommand cmd4 = new AddDeadlineCommand("read/by2020-08-23");

        // Tests
        assertThrows(InvalidDateException.class, () -> executeTask(cmd1));
        assertThrows(InvalidDeadlineException.class, () -> executeTask(cmd2));
        assertThrows(InvalidDeadlineException.class, () -> executeTask(cmd3));
        assertThrows(InvalidDeadlineException.class, () -> executeTask(cmd4));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for blank descriptions.
     */
    @Test
    public void execute_blankDescription_throwsInvalidDeadlineException() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("");
        AddDeadlineCommand cmd2 = new AddDeadlineCommand("/by 2020-08-23");

        // Tests
        assertThrows(InvalidDeadlineException.class, () -> executeTask(cmd1));
        assertThrows(InvalidDeadlineException.class, () -> executeTask(cmd2));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for empty time description.
     */
    @Test
    public void execute_emptyTimeFrame_throwsEmptyTimeException() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("meeting /by");
        String deadlineMessage = "OOPS!!! Time of deadline task is not specified!\n";

        // Tests
        EmptyTimeException e = assertThrows(EmptyTimeException.class, () -> executeTask(cmd1));
        assertEquals(deadlineMessage, e.getMessage());
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for invalid date formats.
     */
    @Test
    public void execute_wrongTimeFormats_throwsInvalidDateException() {
        AddDeadlineCommand cmd1 = new AddDeadlineCommand("meeting /by 23-30-20");
        AddDeadlineCommand cmd2 = new AddDeadlineCommand("meeting /by 2020-13-20");

        // Tests
        assertThrows(InvalidDateException.class, () -> executeTask(cmd1));
        assertThrows(InvalidDateException.class, () -> executeTask(cmd2));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for duplicate tasks in deadline tasks.
     */
    @Test
    public void execute_duplicateTasks_throwsDuplicateTaskException() {
        try {
            AddDeadlineCommand cmd1 = new AddDeadlineCommand("meeting /by 20-12-20");
            AddDeadlineCommand cmd2 = new AddDeadlineCommand("meeting /by 20-12-20");
            AddDeadlineCommand cmd3 = new AddDeadlineCommand("meeting /by 20-12-20 1420");


            Executable exe1 = () -> executeTask(cmd1);
            Executable exe2 = () -> executeTask(cmd2);
            Executable exe3 = () -> executeTask(cmd3);

            // Tests
            executeTask(cmd1);
            assertThrows(DuplicateTaskException.class, exe1);
            assertThrows(DuplicateTaskException.class, exe2);
            assertThrows(DuplicateTaskException.class, exe3);
            assertEquals(1, taskList.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void isExit_false_success() {
        AddDeadlineCommand cmd = new AddDeadlineCommand("test");
        assertFalse(cmd.isExit());
    }

}
