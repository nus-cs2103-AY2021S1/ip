package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.InvalidSimpleCommandException;
import duke.exception.InvalidTaskNumberException;
import duke.task.ToDo;

/**
 * Tests for the deletion of commands.
 */
public class DeleteCommandTest extends CommandTests {

    /**
     * Tests deletion of tasks command.
     */
    @Test
    public void execute_correctFormat_success() {
        try {
            ToDo toDo1 = new ToDo("test1");
            ToDo toDo2 = new ToDo("test2");
            ToDo toDo3 = new ToDo("test3");
            taskList.add(toDo1);
            taskList.add(toDo2);
            taskList.add(toDo3);
            SimpleCommand sc1 = new DeleteCommand("2");

            // Tests
            assertEquals(ui.deleteTask(toDo2, 2), executeTask(sc1));
            assertEquals(2, storage.getTasks().size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Tests for wrong format.
     */
    @Test
    public void execute_invalidFormat_throwsInvalidSimpleCommandException() {
        DeleteCommand sc1 = new DeleteCommand("xx");
        DeleteCommand sc2 = new DeleteCommand("");
        DeleteCommand sc3 = new DeleteCommand("    ");
        // Tests
        assertThrows(InvalidSimpleCommandException.class, () -> executeTask(sc1));
        assertThrows(InvalidSimpleCommandException.class, () -> executeTask(sc2));
        assertThrows(InvalidSimpleCommandException.class, () -> executeTask(sc3));
    }

    /**
     * Tests for invalid task number.
     */
    @Test
    public void execute_invalidTaskNumber_throwsInvalidTaskNumberException() {
        DeleteCommand sc1 = new DeleteCommand("-1");
        DeleteCommand sc2 = new DeleteCommand("4");
        // Tests
        assertThrows(InvalidTaskNumberException.class, () -> executeTask(sc1));
        assertThrows(InvalidTaskNumberException.class, () -> executeTask(sc2));
    }

}
