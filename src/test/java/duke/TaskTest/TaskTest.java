package duke.TaskTest;

import duke.Task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void invalidIndex_deleteItem() {
        TaskList list = new TaskList();
        assertEquals("Please choose a valid task to delete", list.deleteItem(0));
    }
    @Test
    public void null_addItem() {
        TaskList list = new TaskList();
        assertEquals("Task is null! Nothing was added.", list.addItem(null));
    }

    @Test
    public void invalidIndex_markDone() {
        TaskList list = new TaskList();
        assertEquals("Please choose a valid task to mark as done", list.markDone(0));
    }

    @Test
    public void invalidIndex_revertDone() {
        TaskList list = new TaskList();
        assertEquals("Please choose a valid task to mark as not done", list.revertDone(0));
    }
}
