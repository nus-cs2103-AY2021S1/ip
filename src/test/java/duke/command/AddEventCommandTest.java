package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import duke.exception.DukeException;
import duke.exception.DuplicateTaskException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidEventException;
import duke.task.Event;

/**
 * Tests for adding of events.
 */
public class AddEventCommandTest extends CommandTests {

    /**
     * Tests for adding of simple event tasks.
     */
    @Test
    public void execute_correctFormat_success() {
        try {
            Event event1 = new Event("test", "2-4pm");
            Event event2 = new Event("shop", "2-3pm");
            AddEventCommand cmd1 = new AddEventCommand("test /at 2-4pm");
            AddEventCommand cmd2 = new AddEventCommand("shop /at2-3pm");
            // Tests
            assertEquals(ui.addTask(event1, 1), executeTask(cmd1));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(event2, 2), executeTask(cmd2));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests for invalid complex task formats.
     */
    @Test
    public void execute_invalidFormat_throwsInvalidEventException() {
        AddEventCommand cmd1 = new AddEventCommand("read /by 2-4pm");
        AddEventCommand cmd2 = new AddEventCommand("read/at 2-4pm");
        AddEventCommand cmd3 = new AddEventCommand("read -/at 2-4pm");
        AddEventCommand cmd4 = new AddEventCommand("read/at2-4pm");
        // Tests
        assertThrows(InvalidEventException.class, () -> executeTask(cmd1));
        assertThrows(InvalidEventException.class, () -> executeTask(cmd2));
        assertThrows(InvalidEventException.class, () -> executeTask(cmd3));
        assertThrows(InvalidEventException.class, () -> executeTask(cmd4));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for blank descriptions.
     */
    @Test
    public void execute_blankDescription_throwsInvalidEventException() {
        AddEventCommand cmd1 = new AddEventCommand("");
        AddEventCommand cmd2 = new AddEventCommand("/at 2-4pm");
        // Tests
        assertThrows(InvalidEventException.class, () -> executeTask(cmd1));
        assertThrows(InvalidEventException.class, () -> executeTask(cmd2));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for empty time frame in description.
     */
    @Test
    public void execute_emptyTimeFrame_throwsEmptyTimeException() {
        AddEventCommand cmd1 = new AddEventCommand("meeting /at");
        String eventMessage = "OOPS!!! Time of event task is not specified!\n";
        // Tests
        EmptyTimeException e = assertThrows(EmptyTimeException.class, () -> executeTask(cmd1));
        assertEquals(eventMessage, e.getMessage());
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for duplicate tasks in event tasks.
     */
    @Test
    public void execute_duplicateTasks_throwsDuplicateTaskException() {
        try {
            AddEventCommand cmd1 = new AddEventCommand("meeting /at 2-4pm");
            AddEventCommand cmd2 = new AddEventCommand("meeting /at 3-5pm");

            Executable exe1 = () -> executeTask(cmd1);
            Executable exe2 = () -> executeTask(cmd2);

            // Tests
            executeTask(cmd1);
            assertThrows(DuplicateTaskException.class, exe1);
            assertThrows(DuplicateTaskException.class, exe2);
            assertEquals(1, taskList.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void isExit_false_success() {
        AddEventCommand cmd = new AddEventCommand("test");
        assertFalse(cmd.isExit());
    }

}
