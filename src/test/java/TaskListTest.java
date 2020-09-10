import duke.DukeException;
import duke.TaskList;
import duke.tasks.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addThreeTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("taskOne"));
        taskList.addTask(new Todo("taskTwo"));
        taskList.addTask(new Todo("taskThree"));
        assertEquals(3, taskList.getTaskCount());
    }

    @Test
    void removeTask_FromNonEmptyList() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("taskOne"));
        taskList.addTask(new Todo("taskTwo"));
        taskList.addTask(new Todo("taskThree"));
        taskList.removeTask(1);
        assertEquals(2, taskList.getTaskCount());
        taskList.removeTask(1);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void removeOneTask_FromEmptyList() {
        TaskList taskList = new TaskList();
        try {
            taskList.removeTask(1);
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPSIE!! What are you deleting? Gotta specify a valid task number!", e.toString());
        }
    }

    @Test
    void removeInvalidTask_FromList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("taskOne"));
        try {
            taskList.removeTask(2);
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPSIE!! What are you deleting? Gotta specify a valid task number!", e.toString());
        }
    }

}