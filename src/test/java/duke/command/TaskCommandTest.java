package duke.command;

import duke.exception.DukeException;
import duke.stub.task.TaskListStub;
import duke.task.Task;
import duke.task.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskCommandTest {
    @Test
    public void execute() throws DukeException {
        TaskList taskListStub = new TaskListStub();
        String actual = TaskCommand.execute("deadline this is a test /by 2017-11-29 13:00", taskListStub);
        String[] expected = new String[]{
                "Got it. I've added this task: ",
                "  [D][✘] this is a test (by: Nov 29 2017 01:00 PM)",
                "Now you have 10 tasks in the list."
        };
        assertEquals(String.join("\n", expected), actual);
    }

    @Test
    public void loadSavedTasks() throws DukeException {
        TaskListStub taskListStub = new TaskListStub();
        TaskCommand.loadSavedTasks("1todo This is a loadSavedTasks test", taskListStub);
        Task addedTask = taskListStub.getLastAddedTask();
        String expectedToString = "[T][✓] This is a loadSavedTasks test";
        assertEquals(expectedToString, addedTask.toString());
    }
}
