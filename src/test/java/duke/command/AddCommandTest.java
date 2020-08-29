package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class AddCommandTest {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    @Test
    public void testIsExit() {
        assertFalse(new AddCommand("todo").isExit());
        assertFalse(new AddCommand("deadline").isExit());
        assertFalse(new AddCommand("event").isExit());
    }

    @Test
    public void testExecuteTodo() {
        try {
            AddCommand addCommand = new AddCommand("todo");
            addCommand.execute("todo", taskList, storage);
        } catch (DukeException e) {
            assertEquals("The description of a todo cannot be empty!\n"
                    + "An example would be:\n"
                    + "todo week 3 quiz", e.getMessage());
        }
    }

    @Test
    public void testExecuteDeadline() {
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline ", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input an appropriate description!\n"
                    + "An example would be:\n"
                    + "deadline return book /by 2020-01-30 08:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input the appropriate command!\n"
                    + "An example would be:\n"
                    + "deadline return book /by 2020-01-30 08:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book /by ", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input the date!\n"
                    + "An example would be:\n"
                    + "deadline return book /by 2020-01-30 08:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book /by 2020-02-28", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input the correct date format!\n"
                    + "An example would be:\n"
                    + "deadline return book /by 2020-01-30 08:00", e.getMessage());
        }
    }

    @Test
    public void testExecuteEvent() {
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input an appropriate description!\n"
                    + "An example would be:\n"
                    + "event Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party ", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input the appropriate command!\n"
                    + "An example would be:\n"
                    + "event Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party /at", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input the date!\n"
                    + "An example would be:\n"
                    + "event Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party /at 2020-20-09 1200", taskList, storage);
        } catch (DukeException e) {
            assertEquals("Please input the correct date format!\n"
                    + "An example would be:\n"
                    + "event Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
    }
}
