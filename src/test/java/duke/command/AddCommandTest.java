package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
            assertEquals("\tThe description of a todo cannot be empty!\n"
                    + "\tAn example would be:\n"
                    + "\ttodo week 3 quiz", e.getMessage());
        }
    }

    @Test
    public void testExecuteDeadline() {
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline ", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input the appropriate command!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book /by ", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book /by 2020-02-28", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input the correct date format!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-01-30 08:00", e.getMessage());
        }
    }

    @Test
    public void testExecuteEvent() {
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party ", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input the appropriate command!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party /at", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party /at 2020-20-09 1200", taskList, storage);
        } catch (DukeException e) {
            assertEquals("\tPlease input the correct date format!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00", e.getMessage());
        }
    }
}
