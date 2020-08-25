package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
