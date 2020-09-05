package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyFindException;
import duke.task.ComplexTask;
import duke.task.TaskType;
import duke.task.ToDo;
import duke.tasklist.TaskList;

public class FindCommandTest extends CommandTests {

    /**
     * Tests the find command with 2 different query words.
     */
    @Test
    public void testFindMethod() {
        try {
            FindCommand fc = new FindCommand("test");
            ToDo toDo = new ToDo("te");
            ComplexTask event = new ComplexTask("test", TaskType.EVENT, "2pm");
            ComplexTask deadline = new ComplexTask("123test12312", TaskType.DEADLINE, "2pm");
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
    public void testEmptyFind() {
        FindCommand fc = new FindCommand("");
        assertThrows(EmptyFindException.class, () -> fc.execute(taskList, ui, storage));
    }
}
