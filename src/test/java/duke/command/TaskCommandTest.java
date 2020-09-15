package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.response.Response;
import duke.stub.task.TaskListStub;
import duke.task.Task;
import duke.task.TaskList;

public class TaskCommandTest {
    @Test
    public void execute() throws DukeException {
        TaskList taskListStub = new TaskListStub();
        Response response = TaskCommand.execute("deadline this is a test /by 2017-11-29 13:00", taskListStub);
        String[] expected = new String[]{
            "Got it. I've added this task: ",
            "  [D][X] this is a test (by: Nov 29 2017 01:00 PM)",
            "Now you have 10 tasks in the list."
        };
        assertEquals(String.join("\n", expected), response.getMessage());
    }

    @Test
    public void loadSavedTasks() throws DukeException {
        TaskListStub taskListStub = new TaskListStub();
        TaskCommand.loadSavedTasks("1todo This is a loadSavedTasks test", taskListStub);
        Task addedTask = taskListStub.getLastAddedTask();
        String expectedToString = "[T][O] This is a loadSavedTasks test";
        assertEquals(expectedToString, addedTask.toString());
    }
}
