import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class TaskListTest {
    @Test
    public void addRemoveGetTest() {
        TaskList list = new TaskList();
        list.add(new Todo("read book", true, 0));
        list.add(new Todo("wash clothes", false, 0));
        list.add(new Event("birthday", "2020-06-29", false, 0));
        list.add(new Deadline("submission", "2020-08-27", false, 0));
        list.remove(0);
        String task = list.get(1).toString();
        assertEquals("[E][\u2718] birthday (at: Jun 29 2020)", task);
    }
}
