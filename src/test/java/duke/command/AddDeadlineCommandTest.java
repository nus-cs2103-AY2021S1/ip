package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.parser.DateTimeParser;
import duke.task.Deadline;

public class AddDeadlineCommandTest {

    /**
     * Tests for various time formats.
     */
    @Test
    public void testTimeFormats() {
        try {
            Deadline ct1 = new Deadline("Test", DateTimeParser.getDate("yyyy-MM-dd"));
            Deadline ct2 = new Deadline("test2", DateTimeParser.getDate("Aug 23 2020 / 10.15 AM"));
            Deadline ct3 = new Deadline("test2", DateTimeParser.getDate("10.15 PM"));
            AddEventCommand cmd1 = new AddEventCommand("Test /at   2020-08-23   ");
            AddEventCommand cmd2 = new AddEventCommand("test2 /by 2020-08-23 10:15");
            AddEventCommand cmd3 = new AddEventCommand("test2 /by 2020-08-23T10:15");
            AddEventCommand cmd4 = new AddEventCommand("test2 /by 2020-08-23 10:15:33");
            AddEventCommand cmd5 = new AddEventCommand("test2 /by 22:15");
            // Tests
            /*
            assertEquals(ui.addTask(ct1, 1), cmd1.execute(taskList, ui, storage));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(ct2, 2), cmd2.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct2, 3), cmd3.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct2, 4), cmd4.execute(taskList, ui, storage));
            assertEquals(ui.addTask(ct3, 5), cmd5.execute(taskList, ui, storage));
            */
        } catch (DukeException e) {
            fail();
        }
    }
}
