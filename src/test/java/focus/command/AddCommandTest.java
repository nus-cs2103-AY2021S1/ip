package focus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

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
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input an appropriate task description!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
    }

    @Test
    public void testExecuteDeadline() {
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline ", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input an appropriate task description!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input the appropriate command!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book /by ", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input the correct date format!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("deadline");
            addCommand.execute("deadline return book /by 2020-02-28", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input the correct date format!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
    }

    @Test
    public void testExecuteEvent() {
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input an appropriate task description!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party ", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input the appropriate command!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party /at", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input the correct date format!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
        try {
            AddCommand addCommand = new AddCommand("event");
            addCommand.execute("event party /at 2020-20-09 1200", taskList, storage);
        } catch (FocusException e) {
            assertEquals("\tERROR: Please input the correct date format!\n"
                    + "\tIf you need an example, type 'help'!", e.getMessage());
        }
    }
}
