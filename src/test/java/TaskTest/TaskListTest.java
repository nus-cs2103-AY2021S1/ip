package TaskTest;

import Command.DeleteCommand;
import Exceptions.DukeException;
import ParserStorageUi.Parser;
import ParserStorageUi.Storage;
import Task.Deadline;
import Task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void taskListAddTest() throws DukeException {
        assertEquals(new TaskList(new Storage("data").load()).add("deadline cs2103 /by 2020-06-26 2359").getAddedOrDeletedTask(), new Deadline("cs2103", false, "2020-06-26 2359"));
    }

    @Test
    public void taskListDeleteTest() throws DukeException {
        assertEquals(new TaskList(new Storage("data").load()).delete("delete 1").getAddedOrDeletedTask(), new Deadline("cs2103 project", false, "2020-06-26 2359"));
    }

    @Test
    public void taskListDoneTest() throws DukeException {
        assertEquals(new TaskList(new Storage("data").load()).done("done 1").getAddedOrDeletedTask(), new Deadline("cs2103 project", true, "2020-06-26 2359"));
    }

}
