package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
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
    public void execute_AddingOfTasks() {
        try {
            String taskDetails1 = "test";
            String taskDetails2 = "test2";
            ToDo toDo1 = new ToDo(taskDetails1);
            ToDo toDo2 = new ToDo(taskDetails2);
            AddToDoCommand cmd1 = new AddToDoCommand(taskDetails1);
            AddToDoCommand cmd2 = new AddToDoCommand(taskDetails2);
            // Tests
            assertEquals(ui.addTask(toDo1, 1), cmd1.execute(taskList, ui, storage));
            assertEquals(1, storage.getTasks().size());
            assertEquals(ui.addTask(toDo2, 2), cmd2.execute(taskList, ui, storage));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests empty task details.
     */
    @Test
    public void execute_EmptyTaskDetails() {
        String emptyTaskDetails = "";
        String expectedMessage = "OOPS!!! The description of a todo cannot be empty.\n";
        AddToDoCommand cmd = new AddToDoCommand(emptyTaskDetails);
        // Tests
        EmptyToDoException e = assertThrows(EmptyToDoException.class, () -> cmd.execute(taskList, ui, storage));
        assertEquals(expectedMessage, e.getMessage());
        assertTrue(taskList.isEmpty());
    }
}
