package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.InvalidDeleteException;
import duke.exception.InvalidDoneException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.TaskAlreadyDoneException;
import duke.task.ToDo;

public class SimpleCommandTest extends CommandTests {
    @Test
    public void testNotNumber() {
        SimpleCommand sc1 = new SimpleCommand("", SimpleCommandType.DONE);
        SimpleCommand sc2 = new SimpleCommand("awsdsa", SimpleCommandType.DELETE);
        assertThrows(InvalidDoneException.class, () -> sc1.execute(taskList, ui, storage));
        assertThrows(InvalidDeleteException.class, () -> sc2.execute(taskList, ui, storage));
    }
    @Test
    public void testInvalidTaskNumber() {
        SimpleCommand sc1 = new SimpleCommand("5", SimpleCommandType.DONE);
        SimpleCommand sc2 = new SimpleCommand("-2", SimpleCommandType.DELETE);
        String expectedMessage = String.format("OOPS!!! Task number does not exist in the list.\n"
            + "Your current list only has %d tasks!", 0);
        // Test
        InvalidTaskNumberException e = assertThrows(
            InvalidTaskNumberException.class, () -> sc1.execute(taskList, ui, storage));
        assertEquals(expectedMessage, e.getMessage());
        InvalidTaskNumberException e2 = assertThrows(
            InvalidTaskNumberException.class, () -> sc2.execute(taskList, ui, storage));
        assertEquals(expectedMessage, e2.getMessage());
    }
    @Test
    public void testDoneCommand() {
        try {
            ToDo toDo1 = new ToDo("test1");
            ToDo toDo2 = new ToDo("test2");
            ToDo toDo3 = new ToDo("test3");
            taskList.add(toDo1);
            taskList.add(toDo2);
            taskList.add(toDo3);
            SimpleCommand sc1 = new SimpleCommand("2", SimpleCommandType.DONE);
            assertEquals("Nice! I've marked this task as done:\n    [T][\u2713] test2",
                sc1.execute(taskList, ui, storage));
            assertTrue(storage.getTasks().get(1).isDone());
            assertThrows(TaskAlreadyDoneException.class, () -> sc1.execute(taskList, ui, storage));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    @Test
    public void testDeleteCommand() {
        try {
            ToDo toDo1 = new ToDo("test1");
            ToDo toDo2 = new ToDo("test2");
            ToDo toDo3 = new ToDo("test3");
            taskList.add(toDo1);
            taskList.add(toDo2);
            taskList.add(toDo3);
            SimpleCommand sc1 = new SimpleCommand("2", SimpleCommandType.DELETE);
            assertEquals(ui.deleteTask(toDo2, 2), sc1.execute(taskList, ui, storage));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
