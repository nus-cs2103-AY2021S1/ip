package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
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
    public void testDoneCommand() {
        try {
            ToDo toDo1 = new ToDo("test1");
            ToDo toDo2 = new ToDo("test2");
            ToDo toDo3 = new ToDo("test3");
            DoneCommand sc1 = new DoneCommand("2");
            taskList.add(toDo1);
            taskList.add(toDo2);
            taskList.add(toDo3);
            // Tests
            assertEquals("Nice! I've marked this task as done:\n    [T][\u2713] test2\n",
                sc1.execute(taskList, ui, storage));
            assertTrue(storage.getTasks().get(1).isDone());
            assertThrows(TaskAlreadyDoneException.class, () -> sc1.execute(taskList, ui, storage));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
