package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Encapsulates the main test system that the developer uses to test the
 * functionality of the Duke program.
 */
public class DukeTest {

    @Test
    public void datePrintTest() throws DukeException {
        Deadline deadline = new Deadline("Test the printDate() method", "2020-08-23");
        assertEquals("SUNDAY, AUGUST 23 2020", deadline.printDate());
    }

    @Test
    public void toStringTest() throws DukeException {
        Event event = new Event("Test the toString() method", "2020-08-23");
        assertEquals("E > N > Test the toString() method > at: SUNDAY, AUGUST 23 2020",
                event.toString());
    }

    @Test
    public void taskListTest() {
        TaskList taskList = new TaskList();
        ToDo todo = new ToDo("Test the TaskList class for the Duke project");

        taskList.addTodo(todo);
        assertEquals("T > N > Test the TaskList class for the Duke project",
                taskList.getTaskList().get(0).toString());
    }

    @Test
    public void taskListTest2() throws DukeException {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("Test the TaskList class for the Duke project",
                "2020-09-13");
        ToDo test = new ToDo("Delete this task");

        taskList.addTodo(test);
        taskList.addDeadline(deadline);
        taskList.deleteTask(0);

        assertEquals("D > N > Test the TaskList class for the Duke project > by: SUNDAY, "
                        + "SEPTEMBER 13 2020", taskList.getTaskList().get(0).toString());
    }

}
