package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.task.ComplexTask;
import duke.task.TaskType;

public class AddComplexTaskCommandTests extends CommandTests {
    /**
     * Test for adding of simple tasks.
     */
    @Test
    public void testAddingStandard() {
        try {
            ComplexTask complexTask1 = new ComplexTask("test", TaskType.EVENT, "2-4pm");
            ComplexTask complexTask2 = new ComplexTask("test2", TaskType.DEADLINE, "2pm");
            AddComplexTaskCommand cmd1 = new AddComplexTaskCommand("test /at 2-4pm", TaskType.EVENT);
            AddComplexTaskCommand cmd2 = new AddComplexTaskCommand("test2 /by 2pm", TaskType.DEADLINE);

            assertEquals(ui.addTask(complexTask1, 1), cmd1.execute(taskList, ui, storage));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(complexTask2, 2), cmd2.execute(taskList, ui, storage));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Test for invalid complex task formats.
     */
    @Test
    public void testInvalidFormat() {
        AddComplexTaskCommand cmd1 = new AddComplexTaskCommand("read /by 2-4pm", TaskType.EVENT);
        AddComplexTaskCommand cmd2 = new AddComplexTaskCommand("read /at 2-4pm", TaskType.DEADLINE);
        AddComplexTaskCommand cmd3 = new AddComplexTaskCommand("read/at 2-4pm", TaskType.EVENT);
        AddComplexTaskCommand cmd4 = new AddComplexTaskCommand("read/at2-4pm", TaskType.EVENT);

        assertThrows(InvalidEventException.class, () -> cmd1.execute(taskList, ui, storage));
        assertThrows(InvalidDeadlineException.class, () -> cmd2.execute(taskList, ui, storage));
        assertThrows(InvalidEventException.class, () -> cmd3.execute(taskList, ui, storage));
        assertThrows(InvalidEventException.class, () -> cmd4.execute(taskList, ui, storage));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Test for blank descriptions.
     */
    @Test
    public void testBlankDescription() {
        AddComplexTaskCommand cmd1 = new AddComplexTaskCommand("", TaskType.EVENT);
        AddComplexTaskCommand cmd2 = new AddComplexTaskCommand("/at 2-4pm", TaskType.EVENT);
        AddComplexTaskCommand cmd3 = new AddComplexTaskCommand("/by 2-4pm", TaskType.DEADLINE);

        assertThrows(InvalidEventException.class, () -> cmd1.execute(taskList, ui, storage));
        assertThrows(InvalidEventException.class, () -> cmd2.execute(taskList, ui, storage));
        assertThrows(InvalidDeadlineException.class, () -> cmd3.execute(taskList, ui, storage));
        assertTrue(taskList.isEmpty());
    }

    /**
     * Test for empty time in description.
     */
    @Test
    public void testEmptyTime() {
        AddComplexTaskCommand cmd1 = new AddComplexTaskCommand("meeting /at", TaskType.EVENT);
        AddComplexTaskCommand cmd2 = new AddComplexTaskCommand("meeting /at   ", TaskType.EVENT);
        AddComplexTaskCommand cmd3 = new AddComplexTaskCommand("meeting /by", TaskType.DEADLINE);
        AddComplexTaskCommand cmd4 = new AddComplexTaskCommand("meeting /by ", TaskType.DEADLINE);
        String eventMsg = "OOPS!!! Deadline / time of event is not specified";
        String deadlineMsg = "OOPS!!! Deadline / time of deadline is not specified";

        EmptyTimeException e = assertThrows(EmptyTimeException.class, () -> cmd1.execute(taskList, ui, storage));
        assertEquals(eventMsg, e.getMessage());
        EmptyTimeException e1 = assertThrows(EmptyTimeException.class, () -> cmd2.execute(taskList, ui, storage));
        assertEquals(eventMsg, e1.getMessage());
        EmptyTimeException e2 = assertThrows(EmptyTimeException.class, () -> cmd3.execute(taskList, ui, storage));
        assertEquals(deadlineMsg, e2.getMessage());
        EmptyTimeException e3 = assertThrows(EmptyTimeException.class, () -> cmd4.execute(taskList, ui, storage));
        assertEquals(deadlineMsg, e3.getMessage());
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for various time formats.
     */
    @Test
    public void testTimeFormats() {
        try {
            ComplexTask ct1 = new ComplexTask("Test", TaskType.EVENT, "Aug 23 2020");
            ComplexTask ct2 = new ComplexTask("test2", TaskType.DEADLINE, "Aug 23 2020 / 10.15 AM");
            ComplexTask ct3 = new ComplexTask("test2", TaskType.DEADLINE, "10.15 PM");
            AddComplexTaskCommand cmd1 = new AddComplexTaskCommand("Test /at   2020-08-23   ", TaskType.EVENT);
            AddComplexTaskCommand cmd2 = new AddComplexTaskCommand("test2 /by 2020-08-23 10:15", TaskType.DEADLINE);
            AddComplexTaskCommand cmd3 = new AddComplexTaskCommand("test2 /by 2020-08-23T10:15", TaskType.DEADLINE);
            AddComplexTaskCommand cmd4 = new AddComplexTaskCommand("test2 /by 2020-08-23 10:15:33", TaskType.DEADLINE);
            AddComplexTaskCommand cmd5 = new AddComplexTaskCommand("test2 /by 22:15", TaskType.DEADLINE);
            // Test
            assertEquals(ui.addTask(ct1, 1), cmd1.execute(taskList, ui, storage));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(ct2, 2), cmd2.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct2, 3), cmd3.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct2, 4), cmd4.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct3, 5), cmd5.execute(taskList, ui, storage));
        } catch (DukeException e) {
            fail();
        }
    }
}
