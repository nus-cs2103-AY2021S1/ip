package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
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
    public void execute_AddingStandard() {
        try {
            Event event1 = new Event("test", "2-4pm");
            Event event2 = new Event("shop", "2-3pm");
            AddEventCommand cmd1 = new AddEventCommand("test /at 2-4pm");
            AddEventCommand cmd2 = new AddEventCommand("shop /at2-3pm");
            // Tests
            assertEquals(ui.addTask(event1, 1), cmd1.execute(taskList, ui, storage));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(event2, 2), cmd2.execute(taskList, ui, storage));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests for invalid complex task formats.
     */
    @Test
    public void execute_InvalidFormat() {
        AddEventCommand cmd1 = new AddEventCommand("read /by 2-4pm");
        AddEventCommand cmd2 = new AddEventCommand("read/at 2-4pm");
        AddEventCommand cmd3 = new AddEventCommand("read -/at 2-4pm");
        AddEventCommand cmd4 = new AddEventCommand("read/at2-4pm");
        // Tests
        assertThrows(InvalidEventException.class, () -> cmd1.execute(taskList, ui, storage));
        assertThrows(InvalidEventException.class, () -> cmd2.execute(taskList, ui, storage));
        assertThrows(InvalidEventException.class, () -> cmd3.execute(taskList, ui, storage));
        assertThrows(InvalidEventException.class, () -> cmd4.execute(taskList, ui, storage));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for blank descriptions.
     */
    @Test
    public void execute_BlankDescription() {
        AddEventCommand cmd1 = new AddEventCommand("");
        AddEventCommand cmd2 = new AddEventCommand("/at 2-4pm");
        // Tests
        assertThrows(InvalidEventException.class, () -> cmd1.execute(taskList, ui, storage));
        assertThrows(InvalidEventException.class, () -> cmd2.execute(taskList, ui, storage));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for empty time frame in description.
     */
    @Test
    public void execute_EmptyTimeFrame() {
        AddEventCommand cmd1 = new AddEventCommand("meeting /at");
        String eventMessage = "OOPS!!! Time of event task is not specified!\n";
        // Tests
        EmptyTimeException e = assertThrows(EmptyTimeException.class, () -> cmd1.execute(taskList, ui, storage));
        assertEquals(eventMessage, e.getMessage());
        assertTrue(taskList.isEmpty());
    }
}
