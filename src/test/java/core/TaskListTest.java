package core;

import exceptions.DataException;
import org.junit.jupiter.api.Test;
import tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAdd() throws DataException {
        TaskList.add(new Todo("foo"));
        assertEquals(TaskList.size(), 1);
    }

    @Test
    public void testEmpty() {
        assertEquals(TaskList.size(), 0);
    }

}
