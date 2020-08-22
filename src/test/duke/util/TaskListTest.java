package duke.util;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTest() {
        TaskList list = new TaskList();
        assertEquals(list.getSize(), 0);
        list.addTask(new ToDo("todo 1"));
        list.addTask(new ToDo("todo 2"));
        list.addTask(new ToDo("todo 3"));
        list.addTask(new ToDo("todo 4"));
        assertEquals(list.getSize(), 4);
    }
}
