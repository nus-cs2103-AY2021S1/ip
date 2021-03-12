package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.InvalidSimpleCommandException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.TaskAlreadyDoneException;
import duke.task.ToDo;

/**
 * Tests for the marking of task as done command.
 */
public class DoneCommandTest extends CommandTests {

    /**
     * Tests the marking of task done command.
     */
    @Test
    public void execute_correctFormat_success() {
        try {
            ToDo toDo1 = new ToDo("test1");
            ToDo toDo2 = new ToDo("test2");
            ToDo toDo3 = new ToDo("test3");
            DoneCommand sc1 = new DoneCommand("2");
            taskList.add(toDo1);
            taskList.add(toDo2);
            taskList.add(toDo3);
            // Tests
            assertEquals("Incredible! I've marked this task as done:\n    [T][\u2713] test2\n", executeTask(sc1));
            assertTrue(storage.getTasks().get(1).isDone());
            assertThrows(TaskAlreadyDoneException.class, () -> executeTask(sc1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Tests the throwing of task already done exception.
     */
    @Test
    public void execute_taskAlreadyDone_throwsTaskAlreadyDoneException() {
        try {
            ToDo toDo1 = new ToDo("test1");
            DoneCommand sc1 = new DoneCommand("1");
            taskList.add(toDo1);
            // Tests
            executeTask(sc1);
            assertThrows(TaskAlreadyDoneException.class, () -> executeTask(sc1));
            assertTrue(storage.getTasks().get(0).isDone());
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
        DoneCommand sc1 = new DoneCommand("xx");
        DoneCommand sc2 = new DoneCommand("");
        DoneCommand sc3 = new DoneCommand("    ");
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
        DoneCommand sc1 = new DoneCommand("-1");
        DoneCommand sc2 = new DoneCommand("4");
        // Tests
        assertThrows(InvalidTaskNumberException.class, () -> executeTask(sc1));
        assertThrows(InvalidTaskNumberException.class, () -> executeTask(sc2));
    }

    @Test
    public void isExit_false_success() {
        DeleteCommand cmd = new DeleteCommand("1");
        assertFalse(cmd.isExit());
    }

}
