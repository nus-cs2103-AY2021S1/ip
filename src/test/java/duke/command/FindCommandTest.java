package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.EmptyFindException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Tests the find command.
 */
public class FindCommandTest extends CommandTests {

    /**
     * Tests the find command with matching query word.
     */
    @Test
    public void execute_hasMatchingTask_success() {
        try {
            FindCommand fc = new FindCommand("test");
            ToDo toDo = new ToDo("te");
            Event event = new Event("test", "2pm");
            Deadline deadline = new Deadline("123test12312", LocalDateTime.now());
            taskList.add(toDo);
            taskList.add(event);
            taskList.add(deadline);

            // Test 1
            TaskList newTaskList = taskList.matchAll("test");
            assertEquals(ui.showTaskList(newTaskList, "matching "), executeTask(fc));

        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Tests the find command with no matching tasks.
     */
    @Test
    public void execute_noMatchingTask_success() {
        try {
            FindCommand fc = new FindCommand("TEST");
            ToDo toDo = new ToDo("te");
            Event event = new Event("test", "2pm");
            taskList.add(toDo);
            taskList.add(event);

            // Test
            assertEquals(ui.emptyFind("TEST"), executeTask(fc));
            assertEquals(2, taskList.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * Tests find command with empty query word.
     */
    @Test
    public void execute_emptyFind_throwsEmptyFindException() {
        FindCommand fc = new FindCommand("");
        assertThrows(EmptyFindException.class, () -> executeTask(fc));
    }
}
