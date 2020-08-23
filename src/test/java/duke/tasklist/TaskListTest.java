package duke.tasklist;

import duke.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    
    TaskList task;
    
    @BeforeEach
    void init() {
        task = new TaskList();
        for (int i = 0; i < 3; i ++) {
            task.add(new Task("hello world "+ i));
        }
    }
    
    @Test
    public void testSize() {
        assertEquals(3, task.size());
    }

    @Test
    public void testGet() {
        assertEquals(new Task("hello world 2"), task.get(2));
    }

    @Test
    public void testAdd() {
        // add again to test
        task.add(new Task("hello world 3"));
        assertEquals(4, 4);
    }

    @Test
    public void testRemove() {
        task.remove(2);
        assertEquals(2,2);
    }
    
}
