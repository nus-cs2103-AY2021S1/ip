package TaskTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import exceptions.DukeException;
import parserstorageui.Storage;
import task.Deadline;
import task.TaskList;

public class TaskListTest {

    @Test
    public void taskListAddTest() throws DukeException {
        assertEquals(
            new TaskList(new Storage("data").load()).add("deadline cs2103 /by 2020-06-26 2359").getAddedOrDeletedTask(),
            new Deadline("cs2103", false, "2020-06-26 2359"));
    }

    @Test
    public void taskListDeleteTest() throws DukeException {
        assertEquals(new TaskList(new Storage("data").load()).delete("delete 1").getAddedOrDeletedTask(),
            new Deadline("cs2103 project", false, "2020-06-26 2359"));
    }

    @Test
    public void taskListDoneTest() throws DukeException {
        assertEquals(new TaskList(new Storage("data").load()).done("done 1").getAddedOrDeletedTask(),
            new Deadline("cs2103 project", true, "2020-06-26 2359"));
    }

}
