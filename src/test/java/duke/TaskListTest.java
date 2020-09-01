package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;




public class TaskListTest {

    @Test
    public void listContents_emptyList_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.listContents();
        } catch (DukeException e) {
            assertEquals("Your list is empty.", e.getMessage());
        }
    }

    @Test
    public void listContents_nonEmptyList_success() {
        try {
            ArrayList<Task> list = new ArrayList<>();
            list.add(new ToDoStub("test"));
            TaskList tasks = new TaskList(list);
            String expected = "Here is your list:\n1.test";
            assertEquals(tasks.listContents(), expected);
        } catch (DukeException e) {
            fail();
        }
    }
}
