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
import duke.exception.EmptyToDoException;
import duke.task.ToDo;

/**
 * Tests for the adding of todo tasks.
 */
public class AddToDoCommandTest extends CommandTests {

    /**
     * Tests adding of tasks.
     */
    @Test
    public void execute_correctFormat_success() {
        try {
            String taskDetails1 = "test";
            String taskDetails2 = "test2";
            ToDo toDo1 = new ToDo(taskDetails1);
            ToDo toDo2 = new ToDo(taskDetails2);
            AddToDoCommand cmd1 = new AddToDoCommand(taskDetails1);
            AddToDoCommand cmd2 = new AddToDoCommand(taskDetails2);
            // Tests
            assertEquals(ui.addTask(toDo1, 1), executeTask(cmd1));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(toDo2, 2), executeTask(cmd2));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests empty task details.
     */
    @Test
    public void execute_emptyTaskDetails_success() {
        String emptyTaskDetails = "";
        String expectedMessage = "OOPS!!! The description of a todo cannot be empty.\n";
        AddToDoCommand cmd = new AddToDoCommand(emptyTaskDetails);
        // Tests
        EmptyToDoException e = assertThrows(EmptyToDoException.class, () -> executeTask(cmd));
        assertEquals(expectedMessage, e.getMessage());
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests for duplicate tasks in todo tasks.
     */
    @Test
    public void execute_duplicateTasks_throwsDuplicateTaskException() {
        try {
            AddToDoCommand cmd1 = new AddToDoCommand("meeting");
            AddToDoCommand cmd2 = new AddToDoCommand("meeting");

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
        AddToDoCommand cmd = new AddToDoCommand("test");
        assertFalse(cmd.isExit());
    }
}
