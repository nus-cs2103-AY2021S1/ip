package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
     * Tests the find command with 2 different query words.
     */
    @Test
    public void execute_FindMethod() {
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
            assertEquals(ui.showTaskList(newTaskList, "matching "), fc.execute(taskList, ui, storage));
            // Test 2
            fc = new FindCommand("TEST");
            assertEquals(ui.emptyFind("TEST"), fc.execute(taskList, ui, storage));
            assertEquals(3, taskList.size());
        } catch (EmptyFindException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests find command with empty query word.
     */
    @Test
    public void execute_EmptyFind() {
        FindCommand fc = new FindCommand("");
        assertThrows(EmptyFindException.class, () -> fc.execute(taskList, ui, storage));
    }
}
